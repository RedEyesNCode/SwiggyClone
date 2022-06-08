package com.swiggy.swiggyClone.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.swiggy.swiggyClone.amazonbucket.AWSConstant;
import com.swiggy.swiggyClone.dataModel.*;
import com.swiggy.swiggyClone.dataModel.address.AddressBody;
import com.swiggy.swiggyClone.dataModel.address.AddressTable;
import com.swiggy.swiggyClone.dataModel.cart.*;
import com.swiggy.swiggyClone.dataModel.oneToOneRelation.ChildTable;
import com.swiggy.swiggyClone.dataModel.oneToOneRelation.ParentTable;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;
import com.swiggy.swiggyClone.repository.*;
import com.swiggy.swiggyClone.repository.oneToOneRepository.ChildRepository;
import com.swiggy.swiggyClone.repository.oneToOneRepository.ParentRepository;
import com.swiggy.swiggyClone.repository.orderRepository.OrderDetailRepository;
import com.swiggy.swiggyClone.repository.orderRepository.OrderRepository;
import com.swiggy.swiggyClone.repository.product.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
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


    private AddressRepository addressRepository;
    private ParentRepository parentRepository;
    private ChildRepository childRepository;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private AllProductsRepository allProductsRepository;

    private PaymentDetailRepository paymentDetailRepository;
    private GenieRepository genieRepository;
    private ProductTypeRepository productTypeRepository;


    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;








    @Autowired
    public ApiService(UserDataRepository userDataRepository, WishListRepository wishListRepository, RestaurantRepository restaurantRepository,
                      RestaurantDetailRepository restaurantDetailRepository, PopularBrandsRepository popularBrandsRepository,
                      PopularCurationsRespository popularCurationsRespository, OffersRespository offersRespository,
                      PastOrdersRepository pastOrdersRepository,
                      AllergensRepo allergensRepo,
                      PaymentDetailRepository paymentDetailRepository,
                      AllProductsRepository allProductsRepository,
                      OrderDetailRepository orderDetailRepository,
                      OrderRepository orderRepository,
                      AddressRepository addressRepository,
                      ParentRepository parentRepository,
                      GenieRepository genieRepository,
                      ChildRepository childRepository,
                      DessertMenuRepository dessertMenuRepository, MenuItemRepository menuItemRepository,ProductTypeRepository productTypeRepository,
                      PizzaMenuItemRepository pizzaMenuItemRepository, SnacksMenuRepository snacksMenuRepository) {
        this.userDataRepository = userDataRepository;
        this.wishListRepository = wishListRepository;
        this.genieRepository = genieRepository;
        this.restaurantRepository = restaurantRepository;
        this.childRepository = childRepository;
        this.pastOrdersRepository = pastOrdersRepository;
        this.restaurantDetailRepository = restaurantDetailRepository;
        this.popularBrandsRepository = popularBrandsRepository;
        this.popularCurationsRespository = popularCurationsRespository;
        this.offersRespository = offersRespository;
        this.dessertMenuRepository = dessertMenuRepository;
        this.menuItemRepository = menuItemRepository;
        this.pizzaMenuItemRepository = pizzaMenuItemRepository;
        this.allergensRepo = allergensRepo;
        this.addressRepository = addressRepository;
        this.snacksMenuRepository = snacksMenuRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentDetailRepository = paymentDetailRepository;
        this.allProductsRepository = allProductsRepository;
        this.parentRepository = parentRepository;
        this.productTypeRepository = productTypeRepository;



    }



    //NEVER THROW EXCEPTIONS FROM THE SERVICE CLASS IN SPRING BOOT.



    //Get User Details by ID
    public SignupModel getUserDetails(int id){
        return userDataRepository.getById(Long.parseLong(String.valueOf(id)));
    }


    //Store the user data.
    public SignupResponse signupUser(SignupModel signupModel){

        Optional<SignupModel> signupModelOptional = userDataRepository.findbyEmail(signupModel.getUserEmail());
        Optional<SignupModel> signupModelOptionalPassword = userDataRepository.findbyNumber(signupModel.getNumber());


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
    public List<WishListModel> getUserWishList(int id){

        return wishListRepository.findByID(Long.valueOf(String.valueOf(id)));

    }



    //Fetching all the stored users.
    public List<SignupModel> getAllUsers(){

        return userDataRepository.findAll();
    }
    //Fetching the user by ID // TAKING LONG IN THE USER REPOSITORY ALWAYS.
    public SignupModel getUser(int id){
        Optional<SignupModel> signupModelOptional= userDataRepository.findByID(Long.valueOf(String.valueOf(id)));
        if(signupModelOptional.isPresent()){

            return userDataRepository.getById(Long.valueOf(String.valueOf(id)));

        }else {
            return new SignupModel("","","","");
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
    public StatusCodeModel addRestaurantToWishList(int restaurantID, int userID){


        //1. Fetch the restaurant data from the r-table. and insert it into w-table.
        RestaurantsTable restaurantsTable = restaurantRepository.getById(Long.valueOf(String.valueOf(userID)));

        WishListModel wishListModel = new WishListModel(Long.valueOf(String.valueOf(userID)),Long.valueOf(String.valueOf(restaurantID)),true,restaurantsTable.getRestaurantName(),restaurantsTable.getRating(),restaurantsTable.getDeliveryTime(),restaurantsTable.getDiscount(),restaurantsTable.isSwiggyOne());

        wishListRepository.save(wishListModel);
        return new StatusCodeModel("success",200,"Successfully added to Wishlist !");
    }

    //Getting the restaurant detail by id.
    public RestaurantDetailTable getRestaurantDetails(int id){

        return restaurantDetailRepository.getById(Long.valueOf(String.valueOf(id)));

    }

    //Login the user
    public SignupModel loginUser(String number, String password){

        Optional<SignupModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if(signupModelOptional.isPresent()){

            Optional<SignupModel> optionalSignupModel = userDataRepository.loginUserData(number,password);
            return optionalSignupModel.get();
        }else {
            return new SignupModel("","","","");
        }
    }

    //Login User JWT
    public ResponseEntity<?> loginUserJWT(String number, String password){

        Optional<SignupModel> signupModelOptional = userDataRepository.loginUser(number, password);
        if(signupModelOptional.isPresent()){

            Optional<SignupModel> optionalSignupModel = userDataRepository.loginUserData(number,password);
            return ResponseEntity.ok(optionalSignupModel.get());
        }else {
            return ResponseEntity.badRequest().body(new StatusCodeModel("fail",400,"User not found"));
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
    public Allergens getPastOrderDetail(int id){

        Optional<Allergens> allergens = allergensRepo.findById(Long.valueOf(String.valueOf(id)));
        if(allergens.isPresent()){
            return allergensRepo.getById(Long.valueOf(String.valueOf(id)));
        }else {
            throw new IllegalArgumentException();
        }

    }




    //get All Cart items



    //Get user Address by id
    public List<AddressTable> getUserAddressByUserID(int id){
        return addressRepository.findByUserId(Long.valueOf(String.valueOf(id)));

    }

    //Save User Address By Id
    public StatusCodeModel saveUserAddress(AddressBody addressBody){

        addressRepository.save(new AddressTable(addressBody.getUserId(),
                addressBody.getFirstName(),
                addressBody.getLastName(), addressBody.getPhoneNumber(), addressBody.getApartMent(),
                addressBody.getAddress(), addressBody.getPostalCode(), addressBody.getCity()));
        return new StatusCodeModel("success",200,"Address Saved Successfully !");
    }

    //Get all the ParentTable Entries which also gets the data from the
    //Child Table as it has one to one relation
    public List<ParentTable> getParentTables(){

       return parentRepository.findAll();

    }

    public List<ChildTable> getChildTables(){

        return childRepository.findAll();

    }


    public StatusCodeModel addtoCart(OrderInsertBody orderInsertBody){
        String localTime = LocalTime.now().toString();
        Long userId = orderInsertBody.getUserId();
        Long  restaurantId = orderInsertBody.getRestaurantId();
        Long  productId = orderInsertBody.getProductId();
        OrderTable orderTable = orderRepository.save(new OrderTable(userId));
        PaymentDetailTable paymentDetailTable = new PaymentDetailTable();
        paymentDetailTable.setUserId(orderTable.getUserId());
        paymentDetailTable.setOrderId(orderTable.getOrderId());
        paymentDetailTable.setAmount(allProductsRepository.getById(productId).getPrice());
        paymentDetailTable.setCreatedAt(localTime);
        paymentDetailTable.setOrderStatus("Placed");
        paymentDetailTable.setProvider("Cash");
        paymentDetailRepository.save(paymentDetailTable);






        orderDetailRepository.save(new OrderDetailTable(orderTable.getOrderId(),restaurantId,productId,localTime));

        return new StatusCodeModel("success",200,"Item Added to Cart SuccessFully");



    }

    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(new AllProductsResponse("success",200,"Record Found",allProductsRepository.findAll()));
    }


    public ResponseEntity<?> getUserCart(Long userId){
        List<OrderTable> userOrders = orderRepository.findAllOrderForUserId(userId);
        List<Long> userOrderIds = new ArrayList<>();
        for (int i = 0; i < userOrders.size(); i++) {
            userOrderIds.add(userOrders.get(i).getOrderId());
        }
        List<OrderDetailTable> orderDetailTables = new ArrayList<>();
        for (int i = 0; i < userOrderIds.size(); i++) {
            orderDetailTables.add(orderDetailRepository.findOrderDetailsByOrderId(userOrderIds.get(i)));
        }

        List<CartListData> cartLists = new ArrayList<>();

        if (orderDetailTables.size()!=0){
            for (int i = 0; i < orderDetailTables.size(); i++) {
                CartListData cartListData = new CartListData();
                Long restaurantId = orderDetailTables.get(i).getRestaurantId();
                Long productId = orderDetailTables.get(i).getProductId();
                Long paymentDetailId = orderDetailTables.get(i).getOrderDetailId();
                cartListData.setData(new CartData(restaurantId,restaurantDetailRepository.getById(restaurantId)));
                cartListData.setOrderId(orderDetailTables.get(i).getOrderId());
                cartListData.setProduct(new ProductData(productId,allProductsRepository.getById(productId)));
                cartListData.setPaymentDetail(paymentDetailRepository.findByUserId(userId,orderDetailTables.get(i).getOrderId()).get());



                cartLists.add(cartListData);
            }
            CartResponse cartResponse = new CartResponse("success" ,200,"Record Found",cartLists);
            return ResponseEntity.ok(cartResponse);
        }else {

            return ResponseEntity.ok(new StatusCodeModel("fail",200,"Cart is empty."));
        }



    }

    //Fetching the home screen data of the Swiggy Data;

    public ResponseEntity<?> getBasicGenieResponse(){

        List<SwiggiGenieTable> swiggiGenieTables = new ArrayList<>();
        swiggiGenieTables = genieRepository.findAll();
        if(swiggiGenieTables.size()==0){
            return ResponseEntity.ok(new StatusCodeModel("fail", 200, "Record not found"));

        }else {
            return ResponseEntity.ok(new GenieResponse("success",200,"Data found", swiggiGenieTables));

        }
    }

    // UPLOAD THE USER PROFILE PICTURE TO S3 BUCKET.
    public ResponseEntity<StatusCodeModel> uploadFile(MultipartFile file, Long userId) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // WE NEED TO WRITE THE ACL COMMANDS.
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
        fileObj.delete();
        // Need to update the profile column in DB with the base url + file name
        userDataRepository.updateUserProfileImage(fileName,userId);
        return new ResponseEntity<>(new StatusCodeModel("success",200,"Profile pic uploaded successfully + "+fileName),HttpStatus.OK );
    }
    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.print("IO EXCEPTION..");
        }
        return convertedFile;
    }


    // For deleting the item from the cart table of the user.
    public StatusCodeModel deleteCartItem(Long orderId, Long userId) {

        // Delete from the order table , order detail table
        orderDetailRepository.deleteByOrderID(orderId);
        orderRepository.deleteByUserIdandOrderId(orderId);

        return new StatusCodeModel("success",200,"Item deleted successfully");

    }

    public StatusCodeModel deleteWishlist(Long wishlistId) {

        wishListRepository.deleteWishlistById(wishlistId);

        return new StatusCodeModel("success",200,"Removed from wishlist !");

    }

    public ResponseEntity<?> getProductTypes() {


        return new ResponseEntity(productTypeRepository.findAll(),HttpStatus.OK);


    }
}
