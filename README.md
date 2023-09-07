
# Experiment #9 (Bridging The Gap)

## Overview
This is an Android app that generates a dummy QR Code through the Daraja API that in a real world situation can be used to conduct MPESA Transactions using the MPESA app...

## Table of Contents

1. [BackGround](#Background)
2. [Requirements](#Requirements)
   1. [Daraja Account](#Daraja-Account)
   2. [Access Token](#Access-Token)
   3. [API Endpoint](#API-Endpoint)
   4. [Postman](#Postman)
3. [Procedure](#Procedure)
   1. [Setting Up The Dependencies](#Setting-Up-The-Dependencies)
   2. [Creating An App](#Creating-An-App)
   3. [Authorisation](#Authorisation)
   4. [Dynamic QR Code Generation](#Dynamic-QR-Code-Generation)
   5. [Testing](#Testing)
4. [Screenshot](#Screenshot)
5. [Trivia](#Trivia)

## Background
- This documentation seeks to provide a detailed, clear and resourceful guide on how to consume the Daraja API in Android...
- It is based on the Dynamic QR endpoint but can be applied to the others...
- This app also uses Jetpack Compose to generate the QR Code...

## Requirements
Here's a list of what you need to run the app:
### Daraja Account
The Daraja API requires that you have an account set up. If you do not have one, feel free to create it [here](https://developer.safaricom.co.ke/)

### Access Token
This is a String value that will be appended to our API Request for authentication purposes and usually expires after 1 hour. This documentation will walk you through on hopw to access it...

### API Endpoint
The Daraja API actually consists of different endpoints that offer various functionalities related to MPESA. To generate a dynamic MPESA QR Code, we will use the following endpoint: [https://sandbox.safaricom.co.ke/mpesa/qrcode/v1/generate](https://sandbox.safaricom.co.ke/mpesa/qrcode/v1/generate)

### Postman
This tool will be used to generate the Access Token as well as test if the resulting API Request works as expected...

## Procedure
After setting up your Daraja 2.0 Account, here's the procedure to generating the Dynamic MPESA QR Code:

### Setting Up The Dependencies
- Dependencies may vary depending on your app setup but ensure you have Google's [Secrets Gradle Plugin](https://github.com/google/secrets-gradle-plugin) installed as we will use it to hide our Authorisation Credentials...
- Make sure to include KSP as your Annotation processor as it results in faster builds due to it being Kotlin First unlike KAPT which has to convert the Annotations to Java stubs...
- Use either Retrofit2 and OkHTTP3 or KTor Client for making the HTTP Requests...

### Creating An App
- In your Daraja Console, navigate to the "My Apps" tab and create a new app. Enable the first option as the Dynamic QR Code is under the Payments category...
- Do not share the Consumer Key and Consumer Secret as they will be used to generate the other authorisation codes...
- Feel free to check the other options if you wish to explore other endpoints...

### Authorisation
- Navigate to the "APIs" tab and select "Authorization". Copy the endpoint provided and paste it into Postman as a GET Request...
- Navigate to the "Authorization" tab in Postman and select the Basic Authorization under "Type"...
- In your Daraja Console still under "Authorization", copy the Consumer Key and Consumer Secret and paste them inside Username and Password in Postman respectively...
- Click the "Send" button. If successful, the result should return a JSON Object with the Access Token and Valid Duration...

### Dynamic QR Code Generation
- Navigate to the "APIs" tab in your Daraja Console and select the "Dynamic QR" option...
- Assuming you are using the Kotlin Serialization Library, create a Data Class that matches the JSON Object defined as the Request Body as well as another Data Class that matches the expected response i.e The DTO...
- Create a POST Request with a Body parameter for example:

```kotlin
fun interface DarajaApiService {

   @POST("mpesa/qrcode/v1/generate")
   suspend fun postDarajaData(@Body darajaRequestBody: DarajaRequestBody): DarajaDTO

}
```
- Using the Secrets Gradle Plugin, store your Access Token value in the app-level local.properties file and ensure that it has been added in the app-level gitignore file...
- Create an Interceptor class and add a header which contains two parameters: Name, which is "Authorization" and the value which is "BuildConfig.<whateverYouNamedYourAccessTokenVariable>"...
- Add the Interceptor to your Retrofit definition and ensure that you ahve the correct endpoint and base url defined...
- Go to Postman and create a new Request, this time a POST Request.
- Under the "Authorization" tab, choose "Bearer Token" as the type and paste the Access Token you got when you made the previous GET Request in Postman. Ensure it has not expired...
- Under the "Headers" tab, you will see that the Access Token has been defined as "Bearer <yourAccessToken>". This is the standard convention and you should ensure that you define your Access Token variable like that in your code inside local.properties...
- Go to the "Body" tab, select 'Raw', then select 'JSON' under the dropdown and add the JSON value of the sample Request Body defined in the Darja Console. You can modify the values except for the "TrxCode"...
- Click "Send" to test if the Request works as expected...
- In your RepositoryImpl, go to the function that makes the POST Request and define the following variable to instantiate the Request Body:

```kotlin
val darajaRequestBody = DarajaRequestBody(
   merchantName = "Add Your Name...",
   refNo = "Add Your Ref No...",
   amount = "Add your Amount in form of an Integer",
   trxCode = "BG",
   cpi = "Add your CPI...",
   size = "Define the size of your QR Code..."
)
```
- Call the function you defined in the ApiService interface and pass the requestBody variable as the argument...

### Testing
- Since the result returns the QR Code in form of an image encoded in the Base64 format, define a function to decode it:

``` kotlin
fun decodeBase64ToBitmap(base64: String): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(base64, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (ex: Exception) {
        null
    }
}
```

- In your screen composable, create a parameter of your DTO's type and create a rememberSaveable variable inside which you will pass the decoder function and then call the DTO object. Pass the variable as a Bitmap inside an Image Composable:

``` kotlin
@Composable
fun YourScreen(yourDTO: YourDTO) {

    val qrCodeBitmap by rememberSaveable {
        mutableStateOf(decodeBase64ToBitmap(yourDTO.qrCode))
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = BitmapPainter(qrCodeBitmap?.asImageBitmap() ?: ImageBitmap(1, 1)),
            contentDescription = "Daraja",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(size = 210.dp)
        )
    }

}
```
- Run the app and test if it works...

### Sample Screenshot
![image](https://github.com/emmanuelmuturia/BridgingTheGap/assets/55001497/a84dd726-ef19-4840-8a6e-7c0dd5de065d)
![image](https://github.com/emmanuelmuturia/BridgingTheGap/assets/55001497/550f6069-8917-411a-8314-672a381cfe38)


### Trivia
- To store the Access Token using the Secrets Gradle Plugin, refer to [this video](https://youtu.be/X8lYNW_Or2o?si=dB0nLrMPy19eDNKz)...
- Do not forget to renew your Access Token if the Expiry Duration elapses...
- In the DTO/Response Body, the value of RequestID is sometimes null so set it to be of type Nullable...
