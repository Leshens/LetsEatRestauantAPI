package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Tables;
import com.leshen.LetsEatRestaurantAPI.Repository.TablesRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableService {
    private final TablesRepository tablesRepository;
    private final TableMapper tableMapper = TableMapper.INSTANCE;

    @Autowired
    public TableService(TablesRepository tablesRepository) {
        this.tablesRepository = tablesRepository;
    }

    public Long createTable(TableDto tableDto) {
        Tables newTable = tableMapper.toEntity(tableDto);
        return tablesRepository.save(newTable).getTableId();
    }

    public Optional<TableDto> getTableById(Long id) {
        return tablesRepository.findById(id).map(tableMapper::toDto);
    }

    public void updateTable(Long id, TableDto tableDto) {
        Tables existingTable = tablesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Table not found"
                ));

        Tables updatedTable = tableMapper.updateTableFromDto(tableDto, existingTable);
        tablesRepository.save(updatedTable);
    }

    public void deleteTable(Long id) {
        tablesRepository.deleteById(id);
    }

    public List<TableDto> getAllTables() {
        return tablesRepository.findAll().stream()
                .map(tableMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<TableDto> getTablesForRestaurant(Long restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(restaurantId);
        List<Tables> tablesEntities = tablesRepository.findByRestaurant(restaurant);
        return tablesEntities.stream()
                .map(tableMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean verifyToken(Long tableId, String requestToken) {
        try {
            Tables tables = tablesRepository.findById(tableId).get();
            return tables.getToken().equals(requestToken);
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public TableDto patchTable(long id, TableDto tableDto) {
        Tables existingTables = tablesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        Tables patchedTables = tableMapper.patchTableFromDto(tableDto, existingTables);

        return tableMapper.toDto(tablesRepository.save(patchedTables));
    }
}
