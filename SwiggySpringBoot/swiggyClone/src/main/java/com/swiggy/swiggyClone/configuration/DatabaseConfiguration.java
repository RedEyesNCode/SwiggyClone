package com.swiggy.swiggyClone.configuration;

import com.swiggy.swiggyClone.dataModel.RestaurantDetailTable;
import com.swiggy.swiggyClone.dataModel.RestaurantsTable;
import com.swiggy.swiggyClone.repository.RestaurantDetailRepository;
import com.swiggy.swiggyClone.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfiguration {


    @Bean
    CommandLineRunner commandLineRunner(RestaurantRepository restaurantRepository,RestaurantDetailRepository restaurantDetailRepository){


        return args -> {


            List<RestaurantsTable> restaurantsTables = new ArrayList<>();
            RestaurantsTable restaurantsTable = new RestaurantsTable("Gurukripa Restaurant",4.0,"35-40 mins",60.0,true);
            RestaurantsTable r2 = new RestaurantsTable("Sagar Gaire",4.0,"40-45 mins",42.0,true);
            RestaurantsTable r3 = new RestaurantsTable("Apna Sweets",4.2,"30-35 mins",56.0,false);
            RestaurantsTable r4 = new RestaurantsTable("Ranjeet's Kitchen",3.8,"45-50 mins",60.0,true);
            RestaurantsTable r5 = new RestaurantsTable("Nafees Restaurant",4.1,"35-40 mins",60.0,false);
            RestaurantsTable r6 = new RestaurantsTable("Oven Story Pizza",4.1,"50-69 mins",60.0,false);

            restaurantsTables.add(restaurantsTable);
            restaurantsTables.add(r2);
            restaurantsTables.add(r3);
            restaurantsTables.add(r4);
            restaurantsTables.add(r5);
            restaurantsTables.add(r6);
            restaurantRepository.saveAll(restaurantsTables);



            List<RestaurantDetailTable> restaurantDetailTables = new ArrayList<>();
            RestaurantDetailTable r1 = new RestaurantDetailTable("Gurukripa Restaurant","North Indian, South Indian","South Tukoganj",37.4267861,37.4267861,4.0,"35-40 mins","200");
            RestaurantDetailTable r21 = new RestaurantDetailTable("Sagar Gaire","North Indian, South Indian, Chinese, Thai","Near Haveli",37.4267861,37.4267861,3.0,"35-40 mins","450");
            RestaurantDetailTable r31 = new RestaurantDetailTable("Apna Sweets","Desserts","56 Dukan",37.4267861,37.4267861,4.0,"35-40 mins","120");
            RestaurantDetailTable r41 = new RestaurantDetailTable("Ranjeet's Kitchen","North Indian, Non Veg","Vijay Nagar",37.4267861,37.4267861,4.5,"35-40 mins","430");
            RestaurantDetailTable r51 = new RestaurantDetailTable("Nafees Restaurant","North Indian, South Indian, Snacks","Indore , MP",37.4267861,37.4267861,3.8,"35-40 mins","600");
            RestaurantDetailTable r61 = new RestaurantDetailTable("Oven Story Pizza","Italian, Mexican","Awadhpuri",37.4267861,37.4267861,4.0,"35-40 mins","500");
            restaurantDetailTables.add(r1);
            restaurantDetailTables.add(r21);
            restaurantDetailTables.add(r31);
            restaurantDetailTables.add(r41);
            restaurantDetailTables.add(r51);
            restaurantDetailTables.add(r61);

            restaurantDetailRepository.saveAll(restaurantDetailTables);



        };
    }


}
