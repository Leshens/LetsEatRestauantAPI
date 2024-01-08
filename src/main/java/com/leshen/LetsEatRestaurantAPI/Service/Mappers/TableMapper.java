package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import com.leshen.LetsEatRestaurantAPI.Model.Tables;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableMapper {
    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    @Mapping(target = "tableId", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "token", source = "tableDto.token")
    @Mapping(target = "twoOs", source = "tableDto.twoOs")
    @Mapping(target = "fourOs", source = "tableDto.fourOs")
    @Mapping(target = "sixOs", source = "tableDto.sixOs")
    @Mapping(target = "eightOs", source = "tableDto.eightOs")
    Tables toEntity(TableDto tableDto);

    TableDto toDto(Tables table);

    @Mapping(target = "tableId", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "token", source = "tableDto.token")
    @Mapping(target = "twoOs", source = "tableDto.twoOs")
    @Mapping(target = "fourOs", source = "tableDto.fourOs")
    @Mapping(target = "sixOs", source = "tableDto.sixOs")
    @Mapping(target = "eightOs", source = "tableDto.eightOs")
    Tables updateTableFromDto(TableDto tableDto, Tables table);
}
