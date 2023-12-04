package com.leshen.LetsEatRestaurantAPI.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TablesService {

    private static TablesRepository tablesRepository = null;

    @Autowired
    public TablesService(TablesRepository tablesRepository) {
        TablesService.tablesRepository = tablesRepository;
    }

    public List<Tables> getTables() {
        return tablesRepository.findAll();
    }

//    public static void updateTables(Integer tablesId, Integer twoOs, Integer fourOs, Integer sixOs, Integer eightOs) {
//        Tables tables = tablesRepository.findById(tablesId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "dish with id " + tablesId + "does not exists"));
//        if (twoOs != null &&
//                !twoOs.isEmpty() &&
//                !Objects.equals(twoOs.getTwo0s(),twoOs)) {
//            twoOs.setTwo0s(twoOs);
//        }
//
//        if (price != null &&
//                !price.isEmpty() &&
//                !Objects.equals(menu.getPrice(),price)) {
//            menu.setPrice(price);
//        }
//
//        if (photo != null &&
//                !photo.isEmpty() &&
//                !Objects.equals(menu.getPhoto(),photo)) {
//            menu.setPhoto(photo);
//        }
//   }
}