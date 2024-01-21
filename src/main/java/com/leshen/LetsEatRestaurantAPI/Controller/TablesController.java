package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Service.TableService;
import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/tables")
public class TablesController {

    @Autowired
    private TableService tableService;

    @PostMapping
    @Operation(summary = "Add a new table")
    @ApiResponse(responseCode = "200", description = "Table created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<Long> newTable(@RequestBody TableDto newTableDto) {
        Long id = tableService.createTable(newTableDto);
        return ResponseEntity.created(URI.create("/table/" + id)).build();
    }

    @GetMapping
    @Operation(summary = "Get all tables")
    @ApiResponse(responseCode = "200", description = "Tables found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<TableDto>> getAllTables() {
        List<TableDto> tableDtos = tableService.getAllTables();
        return new ResponseEntity<>(tableDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a table by ID")
    @ApiResponse(responseCode = "200", description = "Table found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Table not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<TableDto> getById(
            @Parameter(description="Identifier of the table")
            @PathVariable long id) {
        Optional<TableDto> tableDto = tableService.getTableById(id);
        return tableDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found"));
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Get tables for a specific restaurant")
    @ApiResponse(responseCode = "200", description = "Tables found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Tables not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<TableDto>> getTablesForRestaurant(
            @Parameter(description="Identifier of the restaurant")
            @PathVariable Long restaurantId) {
        List<TableDto> tables = tableService.getTablesForRestaurant(restaurantId);
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a table")
    @ApiResponse(responseCode = "200", description = "Table updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TableDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Table not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<TableDto> patchTable(
            @Parameter(description="Identifier of the table")
            @PathVariable Long id,
            @RequestBody TableDto tableDto,
            @Parameter(description="Authorization token")
            @RequestHeader("Authorization") String requestToken) {
        try {
            if (!tableService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            TableDto patchedTable = tableService.patchTable(id, tableDto);
            return ResponseEntity.ok(patchedTable);

        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a table")
    @ApiResponse(responseCode = "200", description = "Table deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Table not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<Long> deleteTable(
            @Parameter(description="Identifier of the table")
            @PathVariable Long id,
            @Parameter(description="Authorization token")
            @RequestHeader("Authorization") String requestToken) {
        try {
            if (!tableService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            tableService.deleteTable(id);
            return new ResponseEntity<>(id, HttpStatus.OK);

        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
