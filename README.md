# HTTP-POST-using-retrofit2-Android-Demo

<p align="center">
  <img src="https://github.com/Mobeyosoft/HTTP-POST-using-retrofit2-Android-Demo/blob/master/scr.png" width="350"/>
</p>

Http POST request to your REST API using retofit2 library

Here we assume 

url : http://example.com/MyProject/v1/

API Endpoint : createstudent

@Field("name") String name,
@Field("username") String username,
@Field("password") String password

Change values on APIService.jave class to your respective values

To test your API use your server url on APIUrl.jave class

For example 
    public static final String BASE_URL = "http://example.com/MyProject/v1/";
