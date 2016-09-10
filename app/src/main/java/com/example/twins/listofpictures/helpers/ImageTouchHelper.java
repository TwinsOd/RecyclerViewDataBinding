package com.example.twins.listofpictures.helpers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.twins.listofpictures.adapters.RecyclerViewAdapter;


public class ImageTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerViewAdapter mRecyclerViewAdapter;

    public ImageTouchHelper(RecyclerViewAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mRecyclerViewAdapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mRecyclerViewAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mRecyclerViewAdapter.remove(viewHolder.getAdapterPosition());
    }
}
