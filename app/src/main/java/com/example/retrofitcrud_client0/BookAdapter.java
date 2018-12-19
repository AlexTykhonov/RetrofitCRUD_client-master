package com.example.retrofitcrud_client0;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private Context context;
    private List<Book> listBooks;

    public BookAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        listBooks = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listbook, parent, false);

        final TextView bookId = (TextView) rowView.findViewById(R.id.bookId);
        final TextView bookTitle = (TextView)rowView.findViewById(R.id.bookTitle);
        final TextView bookAuthor = (TextView)rowView.findViewById(R.id.bookAuthor);
        final TextView bookDescription = (TextView)rowView.findViewById(R.id.bookDescription);
        final TextView bookPublished = (TextView)rowView.findViewById(R.id.bookPublished);

        bookId.setText(String.format("Это ID %d",listBooks.get(position).getId()));
        bookTitle.setText(String.format("Это заголовок %s" ,listBooks.get(position).getTitle()));
        bookAuthor.setText((String.format("Это автор %s", listBooks.get(position).getAuthor())));
        bookDescription.setText(String.format("Здесь описание %s", listBooks.get(position).getDescription()));
        bookPublished.setText(String.format("Дата публикации %d", listBooks.get(position).getPublished()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookActivity.class);

                intent.putExtra("id", listBooks.get(position).getId());
                intent.putExtra("title", listBooks.get(position).getTitle());
                intent.putExtra("author", listBooks.get(position).getAuthor());
                intent.putExtra("description", listBooks.get(position).getDescription());
                intent.putExtra("published", listBooks.get(position).getPublished());

                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
