package com.example.twins.listofpictures;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.example.twins.listofpictures.adapters.RecyclerViewAdapter;
import com.example.twins.listofpictures.data.ApiManager;
import com.example.twins.listofpictures.helpers.BroadcastReceiverHelper;
import com.example.twins.listofpictures.helpers.ImageTouchHelper;
import com.example.twins.listofpictures.model.ImageModel;
import com.example.twins.listofpictures.model.ListImageModel;
import com.example.twins.listofpictures.service.TimeService;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    public static final String BROADCAST_ACTION = "com.example.twins.listofpictures.receiver.TimeBroadcastReceiver;";
    private ArrayList<ImageModel> imageModelList;
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private BroadcastReceiverHelper mBroadcastReceiverHelper;
    private boolean mIsReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null || !savedInstanceState.containsKey("key")) {
            loadDate();
            imageModelList = new ArrayList<>();
        } else
            imageModelList = savedInstanceState.getParcelableArrayList("key");


        // Setup RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        // Setup Adapter
        mRecyclerViewAdapter = new RecyclerViewAdapter(this, imageModelList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        // Setup ItemTouchHelper
        ItemTouchHelper.Callback callback = new ImageTouchHelper(mRecyclerViewAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {
        if (!mIsReceiverRegistered) {
            mBroadcastReceiverHelper = new BroadcastReceiverHelper();
            mBroadcastReceiverHelper.start(this);
            mIsReceiverRegistered = true;
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mIsReceiverRegistered) {
            mBroadcastReceiverHelper.stop();
            mIsReceiverRegistered = false;
        }
        super.onPause();
    }

    private void loadDate() {
//      need to be access internet

        Subscription subscription = ApiManager.getListImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataSuccess, this::onDataError);
        mSubscriptions.add(subscription);
    }

    private void onDataSuccess(ListImageModel listImageModel) {
        imageModelList = listImageModel.getImageModelListL();
        mRecyclerViewAdapter.swapAll(listImageModel.getImageModelListL());
    }

    private void onDataError(Throwable t) {
        //TODO say to user that there is no Internet
        Log.i("MyLog", "onDataError " + t.toString());
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(MainActivity.this, TimeService.class));
        mSubscriptions.clear();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("key", imageModelList);
        super.onSaveInstanceState(outState);
    }
}
