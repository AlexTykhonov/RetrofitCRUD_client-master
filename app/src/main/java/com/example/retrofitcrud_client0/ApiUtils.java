package com.example.retrofitcrud_client0;

public class ApiUtils {
    //    public static final String API_URL = "https://booksreact.herokuapp.com";
//    public static final String API_URL = "https://spring-text-analizer.herokuapp.com";
    public static final String API_URL = "https://spring-boot-mysql-server-part0.herokuapp.com/";

    public ApiUtils() {
    }

    public static BookInterface getBookInterface(){
        return RetrofitClient.getBook(API_URL).create(BookInterface.class);
    }

}