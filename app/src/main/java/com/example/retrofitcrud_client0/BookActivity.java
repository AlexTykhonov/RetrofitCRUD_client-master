package com.example.retrofitcrud_client0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {
    BookInterface bookInterface;
    EditText editFormId;
    EditText editFormTitle;
    EditText editFormAuthor;
    EditText editFormDescription;
    EditText editFormPublishDate;
    Button buttonSave;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        editFormId = findViewById(R.id.editBookId);
        editFormTitle = findViewById(R.id.editTitle);
        editFormAuthor = findViewById(R.id.editAuthor);
        editFormDescription = findViewById(R.id.editDescription);
        editFormPublishDate = findViewById(R.id.editDatePublish);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSave = findViewById(R.id.buttonSave);

        bookInterface = ApiUtils.getBookInterface();
        final Bundle extras = getIntent().getExtras();

        final String bookId = String.valueOf(extras.getInt("id"));
        String title = extras.getString("title");
        String author = extras.getString("author");
        String description = extras.getString("description");
        int published = extras.getInt("published");

        editFormId.setText(bookId);
        editFormTitle.setText(title);
        editFormAuthor.setText(author);
        editFormDescription.setText(description);
        editFormPublishDate.setText(published + "");

        if(bookId!=null && bookId.trim().length()>0){
            editFormId.setFocusable(false);
        } else{
            buttonDelete.setVisibility(View.INVISIBLE);
            editFormId.setVisibility(View.INVISIBLE);
        }

//        buttonSave = findViewById(R.id.buttonSave);
//        buttonDelete = findViewById(R.id.buttonDelete);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId(editFormId.getId());
                book.setTitle(editFormTitle.getText().toString());
                book.setAuthor(editFormAuthor.getText().toString());
                book.setDescription(editFormDescription.getText().toString());
                book.setPublished(Integer.parseInt(editFormPublishDate.getText().toString()));

                if(bookId!=null && bookId.trim().length()>0&&(Integer.parseInt(bookId)!=0)){
                    updateBook(Integer.parseInt(bookId), book);
                    System.out.println("UPDATE !!!!!!!!!!!!!!!!!!!!!!");
                } else{
                    addBook(book);
                    System.out.println("ADD !!!!!!!!!!!!!!!!*********!!!!!!");
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String userId = String.valueOf(extras.getInt("id"));
                deleteBook(Integer.parseInt(userId));
            }
        });
    }

    public void addBook(Book book){
        Call<Book> callBook = bookInterface.addBook(book);
        System.out.println(book+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        callBook.enqueue(new Callback<Book>(){
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()){
                    Toast.makeText(BookActivity.this, "Book created successfully!", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Book has not save!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateBook (final int id, final Book book){
        Call<Book> callBook = bookInterface.updateBook(id, book);
        System.out.println(book+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!UPDATE BOOK!!!!!!!!!!!!!!!!!!");
        callBook.enqueue(new Callback<Book>(){
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()){
                    Toast.makeText(BookActivity.this, "Book updated succesful! " + book.getId(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Toast.makeText(BookActivity.this, "Book update was unsuccesful!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteBook(final int id){
        Call<Book> callBook = bookInterface.deleteBook(id);
        System.out.println(id);
        System.out.println(id+"!!!!!!!!!!!!DELETE BOOK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        callBook.enqueue(new Callback<Book>(){
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(BookActivity.this, "Deletion was successful! " + id, Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(BookActivity.this, "Unsuccessful!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(BookActivity.this, "Deletion was not executed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
