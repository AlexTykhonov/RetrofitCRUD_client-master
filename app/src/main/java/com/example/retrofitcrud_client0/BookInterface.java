package com.example.retrofitcrud_client0;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookInterface {

    @GET("/api/books/")
    Call<List<Book>> getBooks();

    @GET("/api/books/{id}")
    Call<Book> getBook(@Path("id") int id);

    @POST("/api/books/create")
    Call<Book> addBook(@Body Book book);

    @PUT("/api/books/{id}")
    Call<Book> updateBook(@Path("id") int id, @Body Book book);

    @DELETE("/api/books/{id}")
    Call<Book> deleteBook(@Path("id") int id);
}
