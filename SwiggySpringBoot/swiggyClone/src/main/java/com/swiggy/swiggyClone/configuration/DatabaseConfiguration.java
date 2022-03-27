package com.swiggy.swiggyClone.configuration;

import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfiguration {


    @Bean
    CommandLineRunner commandLineRunner(RestaurantRepository restaurantRepository,
                                        RestaurantDetailRepository restaurantDetailRepository,
                                        PopularBrandsRepository popularBrandsRepository,
                                        PopularCurationsRespository popularCurationsRespository,
                                        OffersRespository offersRespository, UserDataRepository userDataRepository,
                                        PastOrdersRepository pastOrdersRepository){


        return args -> {


            List<RestaurantsTable> restaurantsTables = new ArrayList<>();
            RestaurantsTable restaurantsTable = new RestaurantsTable("Gurukripa Restaurant",4.0,"35-40 mins",60.0,true);
            RestaurantsTable r2 = new RestaurantsTable("Sagar Gaire",4.0,"40-45 mins",42.0,true);
            RestaurantsTable r3 = new RestaurantsTable("Apna Sweets",4.2,"30-35 mins",56.0,false);
            RestaurantsTable r4 = new RestaurantsTable("Ranjeet's Kitchen",3.8,"45-50 mins",60.0,true);
            RestaurantsTable r5 = new RestaurantsTable("Nafees Restaurant",4.1,"35-40 mins",60.0,false);
            RestaurantsTable r6 = new RestaurantsTable("Oven Story Pizza",4.1,"50-69 mins",60.0,false);

            RestaurantsTable r7 = new RestaurantsTable("Mc Donald's",4.2,"50-60 mins",60.0,false);
            RestaurantsTable r8 = new RestaurantsTable("Burger King",3.9,"60-70 mins",45.0,false);
            RestaurantsTable r9 = new RestaurantsTable("Sharma & Vishnu",3.8,"40-45 mins",60.0,false);
            RestaurantsTable r10 = new RestaurantsTable("Biryani by Kilo",4.0,"55-60 mins",60.0,false);
            RestaurantsTable r11 = new RestaurantsTable("KFC ",3.8,"35-40 mins",60.0,false);

            restaurantsTables.add(restaurantsTable);
            restaurantsTables.add(r2);
            restaurantsTables.add(r3);
            restaurantsTables.add(r4);
            restaurantsTables.add(r5);
            restaurantsTables.add(r6);

            restaurantsTables.add(r7);
            restaurantsTables.add(r8);
            restaurantsTables.add(r9);
            restaurantsTables.add(r10);
            restaurantsTables.add(r11);
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

            List<PopularCurations> popularCurations = new ArrayList<>();
            PopularCurations popularCurations1 = new PopularCurations("Biryani");
            PopularCurations popularCurations2 = new PopularCurations("Pizzas");
            PopularCurations popularCurations3 = new PopularCurations("Pure Veg");
            PopularCurations popularCurations4 = new PopularCurations("Burger");
            PopularCurations popularCurations5 = new PopularCurations("South Indian");
            PopularCurations popularCurations6 = new PopularCurations("Chinese");


            popularCurations.add(popularCurations1);
            popularCurations.add(popularCurations2);
            popularCurations.add(popularCurations3);
            popularCurations.add(popularCurations4);
            popularCurations.add(popularCurations5);
            popularCurations.add(popularCurations6);

            popularCurationsRespository.saveAll(popularCurations);


            List<PopularBrands> popularBrands = new ArrayList<>();

            PopularBrands popularBrands1 = new PopularBrands(2L,"Sagar Gaire",3.8,"35-40 mins");
            PopularBrands popularBrands2 = new PopularBrands(7L,"Mc Donald's",4.2,"50-60 mins");
            PopularBrands popularBrands3 = new PopularBrands(8L,"Burger King",3.9,"60-70 mins");
            PopularBrands popularBrands4 = new PopularBrands(9L,"Sharma & Vishnu",3.8,"40-45 mins");
            PopularBrands popularBrands5 = new PopularBrands(10L,"Biryani by Kilo",4.0,"55-60 mins");
            PopularBrands popularBrands6 = new PopularBrands(11L,"KFC ",3.8,"35-40 mins");

            popularBrands.add(popularBrands1);
            popularBrands.add(popularBrands2);
            popularBrands.add(popularBrands3);
            popularBrands.add(popularBrands4);
            popularBrands.add(popularBrands5);
            popularBrands.add(popularBrands6);
            popularBrandsRepository.saveAll(popularBrands);

            List<GeneralOffers> offers = new ArrayList<>();
            GeneralOffers offers1 = new GeneralOffers(20.0,"THIS ");
            GeneralOffers offers2 = new GeneralOffers(20.0,"IS");
            GeneralOffers offers3 = new GeneralOffers(20.0,"FOR");
            GeneralOffers offers4 = new GeneralOffers(20.0,"4");
            GeneralOffers offers5 = new GeneralOffers(20.0,"HEARTS");
            GeneralOffers offers6 = new GeneralOffers(20.0,"4EVER");
            offers.add(offers1);
            offers.add(offers2);
            offers.add(offers3);
            offers.add(offers4);
            offers.add(offers5);
            offers.add(offers6);
            offersRespository.saveAll(offers);


            List<SignupModel> signupModels = new ArrayList<>();
            signupModels.add(new SignupModel("springbootmvc@gmail.com","1234567","123456","VancherRR"));
            signupModels.add(new SignupModel("kotlin@gmail.com","12345678","123456","VancherRRR"));
            signupModels.add(new SignupModel("androiddeveloper@gmail.com","123456789","123456","RVancherRR"));
            signupModels.add(new SignupModel("swiigyclone@gmail.com","1234567890","123456","VancherRRR"));
            userDataRepository.saveAll(signupModels);


            List<PastOrders> pastOrders = new ArrayList<>();
            pastOrders.add(new PastOrders(1,"Al-beik","Indrapuri",457.00,"Delivered"));
            pastOrders.add(new PastOrders(1,"Al-beik","Indrapuri",457.00,"Delivered"));
            pastOrders.add(new PastOrders(1,"Al-beik","Indrapuri",457.00,"Delivered"));
            pastOrders.add(new PastOrders(2,"Sagar Gaire","Indrapuri",457.00,"Delivered"));
            pastOrders.add(new PastOrders(3,"KFC","Indrapuri",1077.00,"Delivered"));
            pastOrders.add(new PastOrders(4,"Ranjeet Kitchen","Indrapuri",457.00,"Delivered"));

            pastOrdersRepository.saveAll(pastOrders);





        };
    }


}
