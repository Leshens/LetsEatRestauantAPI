package com.leshen.LetsEatRestaurantAPI.Controller;

import com.leshen.LetsEatRestaurantAPI.Contract.MenuDto;
import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import com.leshen.LetsEatRestaurantAPI.Service.MenuService;
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

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    @Operation(summary = "Create a new menu")
    @ApiResponse(responseCode = "200", description = "Menu created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<MenuDto> newMenu(@RequestBody MenuDto newMenuDto) {
        MenuDto createdMenu = menuService.createMenu(newMenuDto);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "Get all menus")
    @ApiResponse(responseCode = "200", description = "Menus found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<MenuDto>> getAllMenus(){
        List<MenuDto> menus = menuService.getAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a menu by ID")
    @ApiResponse(responseCode = "200", description = "Menu found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Menu not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<MenuDto> getById(@Parameter(description="Identifier of the desired menu") @PathVariable long id) {
        MenuDto menu = menuService.getMenuById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "Get menu for a specific restaurant")
    @ApiResponse(responseCode = "200", description = "Menu found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "404", description = "Menu not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<List<MenuDto>> getMenuForRestaurant(@Parameter(description="Identifier of the restaurant") @PathVariable Long restaurantId) {
        List<MenuDto> menus = menuService.getMenuForRestaurant(restaurantId);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a menu")
    @ApiResponse(responseCode = "200", description = "Menu updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Menu not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<MenuDto> patchMenu(
            @Parameter(description="Identifier of the menu")
            @PathVariable Long id,
            @RequestBody MenuDto menuDto,
            @Parameter(description="Authorization token")
            @RequestHeader("Authorization") String requestToken) {
        try {
            if (!menuService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            MenuDto patchedMenu = menuService.patchMenu(id, menuDto);
            return ResponseEntity.ok(patchedMenu);
        } catch (RuntimeException e) {
            if (e instanceof NoSuchElementException) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a menu")
    @ApiResponse(responseCode = "200", description = "Menu deleted", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized, bad token", content = @Content)
    @ApiResponse(responseCode = "404", description = "Menu not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    public ResponseEntity<Long> deleteMenu(
            @Parameter(description="Identifier of the menu")
            @PathVariable Long id,
            @Parameter(description="Authorization token")
            @RequestHeader("Authorization") String requestToken) {
        try {
            if (!menuService.verifyToken(id, requestToken)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            menuService.deleteMenu(id);
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
