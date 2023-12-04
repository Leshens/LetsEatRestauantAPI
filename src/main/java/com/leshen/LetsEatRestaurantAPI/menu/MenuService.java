package com.leshen.LetsEatRestaurantAPI.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> getMenu() {
        return menuRepository.findAll();
    }

    public void addNewDish(Menu menu) {
//       Optional<Menu> dishOptional = menuRepository.findMenuByName(menu.getName());
//        if (dishOptional.isPresent()) {
//            throw new IllegalStateException("name taken");
//        }
        menuRepository.save(menu);
    }

    public void deleteDish(Integer menuId) {
         boolean exists =  menuRepository.existsById(menuId);
         if (!exists) {
             throw new IllegalStateException("dish with id " + menuId + "does not exists");
         }
         menuRepository.deleteById(menuId);
    }

    @Transactional
    public void updateDish(Integer menuId, String name, String price, String photo){
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalStateException(
                        "dish with id " + menuId + "does not exists"));
        if (name != null &&
                !name.isEmpty() &&
                !Objects.equals(menu.getName(),name)) {
            menu.setName(name);
        }

        if (price != null &&
                !price.isEmpty() &&
                !Objects.equals(menu.getPrice(),price)) {
            menu.setPrice(price);
        }

        if (photo != null &&
                !photo.isEmpty() &&
                !Objects.equals(menu.getPhoto(),photo)) {
            menu.setPhoto(photo);
        }
    }
}
