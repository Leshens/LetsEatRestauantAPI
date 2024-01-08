package com.leshen.LetsEatRestaurantAPI.Service;

import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import com.leshen.LetsEatRestaurantAPI.Repository.MenuRepository;
import com.leshen.LetsEatRestaurantAPI.Service.Mappers.MenuMapper;
import com.leshen.LetsEatRestaurantAPI.Contract.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper = MenuMapper.INSTANCE;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public MenuDto createMenu(MenuDto menuDto) {
        Menu newMenu = menuMapper.toEntity(menuDto);
        return menuMapper.toDto(menuRepository.save(newMenu));
    }

    public List<MenuDto> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        return menus.stream().map(menuMapper::toDto).collect(Collectors.toList());
    }

    public Optional<MenuDto> getMenuById(long id) {
        return menuRepository.findById(id).map(menuMapper::toDto);
    }

    public MenuDto updateMenu(long id, MenuDto menuDto) {
        Menu existingMenu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menuMapper.updateMenuFromDto(menuDto, existingMenu);

        return menuMapper.toDto(menuRepository.save(existingMenu));
    }

    public void deleteMenu(long id) {
        if (!menuRepository.existsById(id)) {
            throw new RuntimeException("Menu not found");
        }
        menuRepository.deleteById(id);
    }
}
