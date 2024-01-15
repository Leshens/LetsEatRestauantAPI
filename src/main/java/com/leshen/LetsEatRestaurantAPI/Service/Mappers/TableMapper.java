package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantListDto;
import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Tables;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = RestaurantMapper.class)
public interface TableMapper {
    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    @Mapping(target = "tableId", source = "tableDto.tableId")
    @Mapping(target = "restaurant", source = "tableDto.restaurantId")
    @Mapping(target = "token", source = "tableDto.token")
    @Mapping(target = "size", source = "tableDto.size")
    Tables toEntity(TableDto tableDto);

    TableDto toDto(Tables table);

    @Mapping(target = "tableId", source = "tableDto.tableId")
    @Mapping(target = "restaurant", source = "tableDto.restaurantId")
    @Mapping(target = "token", source = "tableDto.token")
    @Mapping(target = "size", source = "tableDto.size")
    Tables updateTableFromDto(TableDto tableDto, Tables table);

    List<TableDto> toDtoList(List<Tables> tables);
}
