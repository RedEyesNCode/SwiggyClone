## KwiggBoot

***This Respository is Currently Updating on the Go.***

#####  The Complete Swiggy Clone made with Kotlin using MVVM and Retrofit and Backend made with SpringBoot using Java


#### DESIGN OF THE ENTIRE APP


<table>
    <tr>
    <td>App Screens</td>
    <td>Dashboard Design</td>
    </tr>
    <tr>
    <td><img src="media/cart_screen.png"></td>
    <td><img src="media/dashboard_home.png"></td>
    </tr>
</table>
<table>
    <tr>
    <td>Web App</td>
    <td>Restaurant List</td>
    <td>Save Restaurant</td>
    </tr>
    <tr>
    <td><img src="media/admin_home.png"></td>
    <td><img src="media/admin_rlist.png"></td>
    <td><img src="media/admin_rsave.png"></td>
    </tr>
</table>
<table>
    <tr>
    <td>Payment Screen</td>
    <td>Profile Screen</td>
    <td>Navigation Side bar Screen</td>
    </tr>
    <tr>
    <td><img src="media/payment_screen.png"></td>
    <td><img src="media/profile_screen.png"></td>
    <td><img src="media/navigation_drawer.png"></td>

    </tr>

</table>
<table>
    <tr>
    <td>Application Design</td>
    </tr>
    <tr>
    <td><img src="media/flow_chart.png"></td>
    </tr>
</table>



***Topics Covered***
##### Front End
- Kotlin
-  MVVM Artitecture in Android (ViewModel, LiveData, Listener, BaseActivity and More)
-  Retrofit Integeration in Android Using Kotlin
-  Recycler View & it's Data Passing in android using Kotlin Interfaces.
-  More to Covering Soon.

##### Back End
The Back-End System is Hosted on the Amazon EC2 instance or you can also use it while hosting it on your local Machine
(Described Later)
- Spring boot
- AWS S3 BUCKET 
- JWT TOKEN (ALONG WITH DB VALIDATION)
- QUERY THE DATABASE WITH THE ID
- CHECK IF THE DATABASE HAS A DATA WITH PARTICULAR ID
- COMMON EXCEPTION HANDLING FOR THE CONTROLLER
- VALIDATE DATA FROM DB THEN GIVE TOKEN TO USER
- INSERT INTO ANOTHER TABLE UPON INSERT IN ONE TABLE >> HibernateLazy Initialzer >> WISHLIST API
- QUERY THE DATABASE AND MATCH MULTIPLE PARAMETER
- GOOD PRACTICE IS TO USE RESPONSE ENTITY EVERYTIME

#### Installation
- On Local Machine 
Run the Spring Boot Application in Intellij or any Java IDE.
Check if the Api's are running or not you can Check the Api Documentation for That which is Given Below.
Run ifconfig (Ubuntu) in the terminal or Get the IP address of your machine.
Paste that particular IP address in the Retrofit Service or where is Api Calls are made.

- On Amazon EC2
ssh -i "KeyPair.pem" ec2-user@ec2-your-aws-ec2-instance-ip.ap-south-1.compute.amazonaws.com
(Will Update Soon)



#### Rest Api's Documentation

**POSTMAN COLLECTION** : `https://www.getpostman.com/collections/072514c410f61ef4c597`

- Api Name : ***authJWT***
- Api End Point : `http://localhost:8080/swiggy/authJWT`
This Api Takes the following input and returns the User Details Along With the token.
```json
{
    "username":"1234567",
    "password": "123456"
}
```
##### Output
```json
{
    "status": "success",
    "code": 200,
    "message": "Login SuccessFullly",
    "_token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3IiwiZXhwIjoxNjQ5ODE1ODg3LCJpYXQiOjE2NDk3Nzk4ODd9.M5CwspVCLAnFyhJO5klgGVAQeREl4mC0n0NPcbr-SJs",
    "data": {
        "id": 1,
        "userEmail": "springbootmvc@gmail.com",
        "number": "1234567",
        "password": "123456",
        "userName": "VancherRR"
    }
}
```
**NOTE :** Every Api end point will require a header under the Key Authorization along with the value of _token from the authJWT API.

- Api Name : signup
- Api End Point : `http://localhost:8080/swiggy/signup`

Input : 
```json
{
    "userEmail" : "ashutosh singh",
    "number" : "12345678",
    "password": "123456",
    "userName" : "RedEyesNCode"
}
```
Output :
```json
{
    "code": 200,
    "status": "success",
    "message": "Registered Successfully",
    "id": 5
}
```
- Api Name : getAllUsers
- Api End Point : `http://localhost:8080/swiggy/getAllUsers`

Input : Access Token in the Header.

Output :
```json
{
    "code": 200,
    "status": "success",
    "users": [
        {
            "id": 1,
            "userEmail": "springbootmvc@gmail.com",
            "number": "1234567",
            "password": "123456",
            "userName": "VancherRR"
        },
        {
            "id": 2,
            "userEmail": "kotlin@gmail.com",
            "number": "12345678",
            "password": "123456",
            "userName": "VancherRRR"
        },
        {
            "id": 3,
            "userEmail": "androiddeveloper@gmail.com",
            "number": "123456789",
            "password": "123456",
            "userName": "RVancherRR"
        },
        {
            "id": 4,
            "userEmail": "swiigyclone@gmail.com",
            "number": "1234567890",
            "password": "123456",
            "userName": "VancherRRR"
        },
        {
            "id": 5,
            "userEmail": "ashutosh singh",
            "number": "12345678",
            "password": "123456",
            "userName": "Vancher"
        }
    ]
}
```
- Api Name : getUser?id=x where x is the UserId you want to query about

- Api End Point : `http://localhost:8080/swiggy/getUser?id=1`
=======
- Api End Point : `http://localhost:8080/swiggy/getUser?id=1`

