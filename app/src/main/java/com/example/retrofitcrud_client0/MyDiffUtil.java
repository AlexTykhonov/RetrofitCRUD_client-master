package com.example.retrofitcrud_client0;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

public class MyDiffUtil extends DiffUtil.Callback{

        ArrayList<Book> oldBook;
        ArrayList<Book> newBook;

        public MyDiffUtil(ArrayList<Book> newBook, ArrayList<Book> oldBook) {
            this.newBook = newBook;
            this.oldBook = oldBook;
        }

        @Override
        public int getOldListSize() {
            return oldBook.size();
        }

        @Override
        public int getNewListSize() {
            return newBook.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldBook.get(oldItemPosition).getId() == newBook.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldBook.get(oldItemPosition).equals(newBook.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            //you can return particular field for changed item.
            return super.getChangePayload(oldItemPosition, newItemPosition);
        }
    }