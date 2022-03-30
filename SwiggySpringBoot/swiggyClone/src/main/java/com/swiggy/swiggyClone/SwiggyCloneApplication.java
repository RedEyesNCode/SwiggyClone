package com.swiggy.swiggyClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwiggyCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiggyCloneApplication.class, args);
	}


	//TOTAL CONCEPT COVERED IN THIS :
	/*1. JWT TOKEN (ALONG WITH DB VALIDATION)
	* 2. QUERY THE DATABASE WITH THE ID
	* 3. CHECK IF THE DATABASE HAS A DATA WITH PARTICULAR ID
	* 4. COMMON EXCEPTION HANDLING FOR THE CONTROLLER
	* 5. VALIDATE DATA FROM DB THEN GIVE TOKEN TO USER
	* 6. INSERT INTO ANOTHER TABLE UPON INSERT IN ONE TABLE >> HibernateLazy Initialzer >> WISHLIST API
	* 7. QUERY THE DATABASE AND MATCH MULTIPLE PARAMETER
	* 8. GOOD PRACTICE IS TO USE RESPONSE ENTITY EVERYTIME*/

}
