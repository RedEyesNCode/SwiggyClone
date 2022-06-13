package com.swiggy.swiggyClone.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.swiggy.swiggyClone.amazonbucket.AWSConstant;
import com.swiggy.swiggyClone.dataModel.RestaurantDetailTable;
import com.swiggy.swiggyClone.dataModel.RestaurantsTable;
import com.swiggy.swiggyClone.dataModel.StatusCodeModel;
import com.swiggy.swiggyClone.dataModel.adminModels.FoodItemBody;
import com.swiggy.swiggyClone.dataModel.adminModels.RestaurantBody;
import com.swiggy.swiggyClone.dataModel.commonProduct.AllProductTable;
import com.swiggy.swiggyClone.dataModel.placeOrder.PaymentDetailTable;
import com.swiggy.swiggyClone.repository.AllProductsRepository;
import com.swiggy.swiggyClone.repository.PaymentDetailRepository;
import com.swiggy.swiggyClone.repository.RestaurantDetailRepository;
import com.swiggy.swiggyClone.repository.RestaurantRepository;
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
import java.util.List;

@Component
@Service
public class AdminService {
    private AllProductsRepository allProductsRepository;
    private RestaurantRepository restaurantRepository;

    private RestaurantDetailRepository restaurantDetailRepository;
    private PaymentDetailRepository paymentDetailRepository;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public AdminService(AllProductsRepository allProductsRepository,PaymentDetailRepository paymentDetailRepository,RestaurantRepository restaurantRepository,RestaurantDetailRepository restaurantDetailRepository) {
        this.allProductsRepository = allProductsRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantDetailRepository =restaurantDetailRepository;
        this.paymentDetailRepository = paymentDetailRepository;

    }

    public ResponseEntity<?> insertMenuItem(FoodItemBody foodItemBody) {
//        File fileObj = convertMultiPartFileToFile(file);
//        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // WE NEED TO WRITE THE ACL COMMANDS.
//        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
//        fileObj.delete();
        // Need to update the profile column in DB with the base url + file name
        /* this.menuId = menuId;
        this.dishName = dishName;
        this.isVeg = isVeg;
        this.price = price;
        this.description = description;
        this.productType = productType;*/

        // the product image in the input body
        foodItemBody.setProductImage(AWSConstant.BUCKET_BASE_URL);

       Long menuId =  allProductsRepository.save(new AllProductTable(
                foodItemBody.getDishName(), foodItemBody.isVeg(), foodItemBody.getPrice(),  foodItemBody.getDescription(), foodItemBody.getProductType(),foodItemBody.getProductImage())).getMenuId();

        return new ResponseEntity<>(allProductsRepository.getById(menuId),HttpStatus.OK );
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

    public ResponseEntity<?> uploadFoodImage(MultipartFile file,Long menuId) {

                File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
        fileObj.delete();
        allProductsRepository.updateFoodImage(AWSConstant.BUCKET_BASE_URL+fileName,menuId);


        return new ResponseEntity<>(new StatusCodeModel("success",200,"Image uploaded successfully"),HttpStatus.OK);

    }

    public void addRestaurantWeb(RestaurantsTable restaurantsTable){
        RestaurantsTable restaurantsTableInput = new RestaurantsTable();

        restaurantsTableInput.setRestaurantName(restaurantsTable.getRestaurantName());
        restaurantsTableInput.setDiscount(restaurantsTable.getDiscount());
        restaurantsTableInput.setRating(restaurantsTable.getRating());
        restaurantsTableInput.setDeliveryTime(restaurantsTable.getDeliveryTime());
        restaurantsTableInput.setSwiggyOne(false);
        restaurantsTableInput.setRestaurantImage(AWSConstant.BUCKET_BASE_URL+restaurantsTable.getRestaurantImage());

       Long restaurantId =  restaurantRepository.save(restaurantsTableInput).getRestaurantId();
//        uploadRestaurantImage(file,restaurantId);

    }

    public ResponseEntity<?> addRestaurant(RestaurantBody restaurantBody) {
        RestaurantsTable restaurantsTable = new RestaurantsTable();
        restaurantsTable.setRestaurantImage(AWSConstant.BUCKET_BASE_URL);
        restaurantsTable.setRestaurantName(restaurantBody.getRestaurantName());
        restaurantsTable.setDiscount(restaurantBody.getDiscount());
        restaurantsTable.setRating(restaurantBody.getRating());
        restaurantsTable.setSwiggyOne(restaurantBody.isSwiggyOne());
        restaurantsTable.setDeliveryTime(restaurantBody.getDeliveryTime());


        Long restaurantTableId = restaurantRepository.save(restaurantsTable).getRestaurantId();

        // Saving now in the restaurant detail table.

        RestaurantDetailTable restaurantDetailTable = new RestaurantDetailTable();
        restaurantDetailTable.setRestaurantDetailId(restaurantTableId);
        restaurantDetailTable.setRestaurantName(restaurantBody.getRestaurantName());
        restaurantDetailTable.setCuisines(restaurantBody.getCuisines());
        restaurantDetailTable.setLat(restaurantBody.getLat());
        restaurantDetailTable.setLng(restaurantBody.getLng());
        restaurantDetailTable.setRating(restaurantBody.getRating());
        restaurantDetailTable.setLocation(restaurantBody.getLocation());
        restaurantDetailTable.setPrice(restaurantDetailTable.getPrice());
        restaurantDetailTable.setTime(restaurantBody.getTime());

        restaurantDetailRepository.save(restaurantDetailTable);

        return new ResponseEntity<>(restaurantRepository.getById(restaurantTableId),HttpStatus.OK);
    }

    public ResponseEntity<?> uploadRestaurantImage(MultipartFile file,Long restaurantId){

        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj).withCannedAcl(CannedAccessControlList.PublicRead));
        fileObj.delete();
        restaurantRepository.updateFoodImage(AWSConstant.BUCKET_BASE_URL+fileName,restaurantId);


        return new ResponseEntity<>(new StatusCodeModel("success",200,"Image uploaded successfully"),HttpStatus.OK);


    }
    public List<RestaurantsTable> getAllRestaurants(){
        return restaurantRepository.findAll();

    }


    public List<PaymentDetailTable> getAllOrders(){

        return paymentDetailRepository.findAll();

    }

}
