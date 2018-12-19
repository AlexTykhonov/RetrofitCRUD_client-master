package com.example.retrofitcrud_client0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnAddBook;
    Button btnGetBookList;
    ListView listView;
    BookInterface bookInterface;
    List<Book> listOfBooks = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        btnAddBook = findViewById(R.id.btnAddBook);
        btnGetBookList = findViewById(R.id.btnGetBookList);

        btnGetBookList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getBookList();
            }
        });

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("bookName", "");
                startActivity(intent);
            }
        });

        bookInterface = ApiUtils.getBookInterface();
    }

    public void getBookList(){
        Call<List<Book>> call = bookInterface.getBooks();
        call.enqueue(new Callback<List<Book>>(){
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    listOfBooks = response.body();
                }
                listView.setAdapter(new BookAdapter(MainActivity.this, R.layout.listbook, listOfBooks));
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });
    }
}
