package com.example.twins.listofpictures.helpers;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.twins.listofpictures.adapters.RecyclerViewAdapter;

/**
 * Created by adammcneilly on 9/8/15.
 */
public class ImageTouchHelper extends ItemTouchHelper.SimpleCallback {
    private RecyclerViewAdapter mMovieAdapter;

    public ImageTouchHelper(RecyclerViewAdapter movieAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mMovieAdapter.swap(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mMovieAdapter.remove(viewHolder.getAdapterPosition());
    }
}
