package com.leshen.LetsEatRestaurantAPI.service;

import com.leshen.LetsEatRestaurantAPI.Contract.MenuDto;
import com.leshen.LetsEatRestaurantAPI.Contract.RestaurantDto;
import com.leshen.LetsEatRestaurantAPI.Contract.ReviewDto;
import com.leshen.LetsEatRestaurantAPI.Contract.TableDto;
import com.leshen.LetsEatRestaurantAPI.Model.Menu;
import com.leshen.LetsEatRestaurantAPI.Model.Restaurant;
import com.leshen.LetsEatRestaurantAPI.Model.Review;
import com.leshen.LetsEatRestaurantAPI.Model.Tables;
import com.leshen.LetsEatRestaurantAPI.Repository.MenuRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.RestaurantRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.ReviewRepository;
import com.leshen.LetsEatRestaurantAPI.Repository.TablesRepository;
import com.leshen.LetsEatRestaurantAPI.Service.MenuService;
import com.leshen.LetsEatRestaurantAPI.Service.RestaurantService;
import com.leshen.LetsEatRestaurantAPI.Service.ReviewService;
import com.leshen.LetsEatRestaurantAPI.Service.TableService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceTests {

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private TablesRepository tablesRepository;

    @InjectMocks
    private MenuService menuService;

    @InjectMocks
    private RestaurantService restaurantService;

    @InjectMocks
    private ReviewService reviewService;

    @InjectMocks
    private TableService tableService;

    @Test
    void testCreateAndGetMenu() {
        MenuDto menuDto = new MenuDto();
        menuDto.setName("Test Dish");
        menuDto.setPrice(10.0f);

        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setPrice(menuDto.getPrice());

        when(menuRepository.save(Mockito.any(Menu.class))).thenReturn(menu);

        MenuDto createdMenu = menuService.createMenu(menuDto);
        Optional<MenuDto> retrievedMenu = menuService.getMenuById(1L);

        assertEquals(menuDto.getName(), createdMenu.getName());
        assertEquals(menuDto.getPrice(), createdMenu.getPrice());

        assertEquals(menuDto.getName(), retrievedMenu.orElseThrow().getName());
        assertEquals(menuDto.getPrice(), retrievedMenu.orElseThrow().getPrice());
    }
}
