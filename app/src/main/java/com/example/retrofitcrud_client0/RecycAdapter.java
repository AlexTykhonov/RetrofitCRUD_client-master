package com.example.retrofitcrud_client0;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class RecycAdapter extends  RecyclerView.Adapter<RecycAdapter.ViewHolder> {
    Context context;
    ArrayList<Book> bookList;
    LayoutInflater layoutInflater;

    public RecycAdapter (Context context, ArrayList<Book> booklist) {
    this.context = context;
    this.bookList = booklist;
    this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = layoutInflater.inflate(R.layout.listbook, viewGroup,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);

                intent.putExtra("id", bookList.get(i).getId());
                intent.putExtra("title", bookList.get(i).getTitle());
                intent.putExtra("author", bookList.get(i).getAuthor());
                intent.putExtra("description", bookList.get(i).getDescription());
                intent.putExtra("published", bookList.get(i).getPublished());

                context.startActivity(intent);

            }
        });
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    Book book = bookList.get(i);
    viewHolder.bookId.setText(book.getId().toString());
    viewHolder.bookTitle.setText(book.getTitle());
    viewHolder.bookAuthor.setText(book.getAuthor());
    viewHolder.bookDescription.setText(book.getDescription());
    viewHolder.bookPublished.setText(book.getPublished().toString());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

// ViewHolder содержит ссылки на все элементы разметки
    public class ViewHolder extends RecyclerView.ViewHolder {
    final TextView bookId;
    final TextView bookTitle;
    final TextView bookAuthor;
    final TextView bookDescription;
    final TextView bookPublished;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookId = itemView.findViewById(R.id.bookId);
            this.bookTitle = itemView.findViewById(R.id.bookTitle);
            this.bookAuthor = itemView.findViewById(R.id.bookAuthor);
            this.bookDescription = itemView.findViewById(R.id.bookDescription);
            this.bookPublished = itemView.findViewById(R.id.bookPublished);
        }
    }

    public void  setData (ArrayList<Book> setBookList) {
        // bookList.addAll(setBookList);
        //notifyDataSetChanged();
        //}

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtil(setBookList, bookList));
        diffResult.dispatchUpdatesTo(this); // dispatchUpdateTo отправляем обновление в текущий this recycle view -
        // - то есть в onBindViewHolder (который способен принять не 2 а 3 параметра - обратить внимание что есть 2 метода
        // онБиндВьюХолдер)
        bookList.clear();
        this.bookList.addAll(setBookList);
    }
}
