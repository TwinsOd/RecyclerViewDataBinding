package com.example.twins.listofpictures.adapters;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.twins.listofpictures.MainActivity;
import com.example.twins.listofpictures.R;
import com.example.twins.listofpictures.ShowActivity;
import com.example.twins.listofpictures.databinding.ListItemImageBinding;
import com.example.twins.listofpictures.handler.ImageClickHandler;
import com.example.twins.listofpictures.model.ImageModel;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<ImageModel> mList;

    public RecyclerViewAdapter(Context context, List<ImageModel> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.list_item_image, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageModel imageModel = mList.get(position);
        holder.binding.setImageModel(imageModel);
        holder.binding.setClick(new ImageClickHandler() {

            @Override
            public void onColorClick(View view) {
                if (imageModel.getColor()) {
                    imageModel.setColor(false);
                } else {
                    imageModel.setColor(true);
                }
                notifyItemChanged(position);
            }

            @Override
            public void onShowClick(View view) {
                Intent intent = new Intent(mContext, ShowActivity.class);
                intent.putExtra(ShowActivity.URL, imageModel.getUrl());
                intent.putExtra(ShowActivity.TEXT, imageModel.getText());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((MainActivity) mContext,
                            view, mContext.getString(R.string.transition_image));
                    mContext.startActivity(intent, options.toBundle());
                } else mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mList.size());
    }

    public void swap(int firstPosition, int secondPosition) {
        Collections.swap(mList, firstPosition, secondPosition);
        notifyItemMoved(firstPosition, secondPosition);
    }

    public void swapAll(List<ImageModel> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListItemImageBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
    }

    @BindingAdapter("bind:imageUrlList")
    public static void loadImageList(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_crop_original_100dp)
                .resize(200, 250)
                .into(imageView);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_crop_original_100dp)
                .resize(400, 500)
                .into(imageView);
    }
}
