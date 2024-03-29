package com.leshen.LetsEatRestaurantAPI.Service.Mappers;

import com.leshen.LetsEatRestaurantAPI.Contract.MenuDto;
import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = RestaurantMapper.class)
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @Mapping(target = "menuId", ignore = true)
    @Mapping(target = "restaurant", source = "menuDto.restaurantId")
    @Mapping(target = "token", source = "menuDto.token")
    @Mapping(target = "name", source = "menuDto.name")
    @Mapping(target = "price", source = "menuDto.price")
    Menu toEntity(MenuDto menuDto);

    MenuDto toDto(Menu menu);

    List<MenuDto> toDtoList(List<Menu> menus);

    @Mapping(target = "menuId", ignore = true)
    @Mapping(target = "restaurant", source = "menuDto.restaurantId")
    @Mapping(target = "token", source = "menuDto.token")
    @Mapping(target = "name", source = "menuDto.name")
    @Mapping(target = "price", source = "menuDto.price")
    Menu updateMenuFromDto(MenuDto menuDto, Menu menu);

    @Mapping(target = "menuId", ignore = true)
    @Mapping(target = "restaurant", source = "menuDto.restaurantId")
    @Mapping(target = "token", source = "menuDto.token")
    @Mapping(target = "name", source = "menuDto.name")
    @Mapping(target = "price", source = "menuDto.price")
    Menu patchMenuFromDto(MenuDto menuDto, @MappingTarget Menu menu);
}
