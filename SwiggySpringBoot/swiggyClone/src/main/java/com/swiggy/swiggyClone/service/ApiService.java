package com.swiggy.swiggyClone.service;


import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class ApiService {

    private UserDataRepository userDataRepository;
    private WishListRepository wishListRepository;
    private RestaurantRepository restaurantRepository;
    private RestaurantDetailRepository restaurantDetailRepository;
    private PopularCurationsRespository popularCurationsRespository;
    private PopularBrandsRepository popularBrandsRepository;
    private OffersRespository offersRespository;
    private PastOrdersRepository pastOrdersRepository;

    private DessertMenuRepository dessertMenuRepository;
    private MenuItemRepository menuItemRepository;
    private PizzaMenuItemRepository pizzaMenuItemRepository;
    private SnacksMenuRepository snacksMenuRepository;
    private AllergensRepo allergensRepo;








    @Autowired
    public ApiService(UserDataRepository userDataRepository, WishListRepository wishListRepository, RestaurantRepository restaurantRepository,
                      RestaurantDetailRepository restaurantDetailRepository, PopularBrandsRepository popularBrandsRepository,
                      PopularCurationsRespository popularCurationsRespository, OffersRespository offersRespository,
                      PastOrdersRepository pastOrdersRepository,
                      AllergensRepo allergensRepo,
                      DessertMenuRepository dessertMenuRepository, MenuItemRepository menuItemRepository,
                      PizzaMenuItemRepository pizzaMenuItemRepository, SnacksMenuRepository snacksMenuRepository) {
        this.userDataRepository = userDataRepository;
        this.wishListRepository = wishListRepository;
        this.restaurantRepository = restaurantRepository;

        this.pastOrdersRepository = pastOrdersRepository;
        this.restaurantDetailRepository = restaurantDetailRepository;
        this.popularBrandsRepository = popularBrandsRepository;
        this.popularCurationsRespository = popularCurationsRespository;
        this.offersRespository = offersRespository;
        this.dessertMenuRepository = dessertMenuRepository;
        this.menuItemRepository = menuItemRepository;
        this.pizzaMenuItemRepository = pizzaMenuItemRepository;
        this.allergensRepo = allergensRepo;

        this.snacksMenuRepository = snacksMenuRepository;



    }





    //Get User Details by ID
    public SignupModel getUserDetails(int id){
        return userDataRepository.getById(Long.parseLong(String.valueOf(id)));
    }


    //Store the user data.
    public SignupResponse signupUser(SignupModel signupModel){

        Optional<SignupModel> signupModelOptional = userDataRepository.findbyEmail(signupModel.getUserEmail());
        Optional<SignupModel> signupModelOptionalPassword = userDataRepository.findbyPassword(signupModel.getNumber());


        if(signupModelOptional.isPresent() || signupModelOptionalPassword.isPresent()){
            return new SignupResponse(400,"fail","Already Registered user",0);
        }else {
            userDataRepository.save(signupModel);
            wishListRepository.save(new WishListModel(false));
            List<SignupModel> users = userDataRepository.findAll();
            Long id = 0L;
            for (int i=0;i<users.size();i++){
                if(users.get(i).getUserEmail().contains(signupModel.getUserEmail()) && users.get(i).getNumber().contains(signupModel.getNumber())){
                    id = users.get(i).getId();
                }
            }
            return new SignupResponse(200,"success","Registered Successfully",Integer.parseInt(String.valueOf(id)));

        }
    }

    //Get the user Wishlist.
    public List<WishListModel> getUserWishList(Long id){

        return wishListRepository.findByID(id);

    }



    //Fetching all the stored users.
    public List<SignupModel> getAllUsers(){

        return userDataRepository.findAll();
    }
    //Fetching the user by ID
    public SignupModel getUser(Long id){
        Optional<SignupModel> signupModelOptional= userDataRepository.findByID(id);
        if(signupModelOptional.isPresent()){

            return userDataRepository.getById(id);

        }else {
            throw new IllegalArgumentException();
        }

    }
    //Update the user according to id
    public StatusCodeModel updateUser(Long id,String userName){
        boolean isFound = userDataRepository.existsById(id);

        if(isFound){
            userDataRepository.updateUserNamebyId(userName,id);
            return new StatusCodeModel("success",200,"Updated the username successfully");
        }else {
            return new StatusCodeModel("fail",400,"No user exists by such id");
        }
    }

    //Fetching all the restaurant list from the database.
    public List<RestaurantsTable> getRestaurants()
    {
        return restaurantRepository.findAll();

    }
    //Add a restuarant to wishlist by id of restaurant
    public StatusCodeModel addRestaurantToWishList(Long restaurantID, Long userID){


        //1. Fetch the restaurant data from the r-table. and insert it into w-table.
        RestaurantsTable restaurantsTable = restaurantRepository.getById(restaurantID);

        WishListModel wishListModel = new WishListModel(userID,restaurantID,true,restaurantsTable.getRestaurantName(),restaurantsTable.getRating(),restaurantsTable.getDeliveryTime(),restaurantsTable.getDiscount(),restaurantsTable.isSwiggyOne());

        wishListRepository.save(wishListModel);
        return new StatusCodeModel("success",200,"Successfully added to Wishlist !");
    }

    //Getting the restaurant detail by id.
    public RestaurantDetailTable getRestaurantDetails(Long id){

        return restaurantDetailRepository.getById(id);

    }

    //Login the user
    public SignupModel loginUser(String number, String password){

        Optional<SignupModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if(signupModelOptional.isPresent()){

            Optional<SignupModel> optionalSignupModel = userDataRepository.loginUserData(number,password);
            return optionalSignupModel.get();
        }else {
            throw new IllegalArgumentException();
        }
    }

    //Home Feed Response from the api
    public HomeFeedResponse getHomeFeed(){



        return new HomeFeedResponse("success",200,"Home Feed Response",restaurantRepository.findAll(),offersRespository.findAll(),popularBrandsRepository.findAll(),popularCurationsRespository.findAll());



    }


    //Get the user past Order by id

    public List<PastOrders> getUserPastOrder(int userID){return pastOrdersRepository.getUserPastOrders(userID);}

    //Get Restaurant Meny by Restaurant Id;

    public RestaurantMenuResponse getRestaurantMenuByid(){


        return new RestaurantMenuResponse("success",200,"All this FOr 4 Hearts !",pizzaMenuItemRepository.findAll(),dessertMenuRepository.findAll(),menuItemRepository.findAll(),snacksMenuRepository.findAll());
    }


    // Get PastOrder Detail by Id
    public Allergens getPastOrderDetail(Long id){

        Optional<Allergens> allergens = allergensRepo.findById(id);
        if(allergens.isPresent()){
            return allergensRepo.getById(id);
        }else {
            throw new IllegalArgumentException();
        }

    }











}
