package com.example.twins.listofpictures;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.twins.listofpictures.databinding.ActivityShowBinding;
import com.example.twins.listofpictures.helpers.BroadcastReceiverHelper;
import com.example.twins.listofpictures.model.ImageModel;

public class ShowActivity extends AppCompatActivity {
    public final static String URL = "url";
    public final static String TEXT = "text";
    private BroadcastReceiverHelper mBroadcastReceiverHelper = null;
    private boolean mIsReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityShowBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_show);

        Intent intent = getIntent();

        binding.setImageModel(new ImageModel(intent.getStringExtra(URL), intent.getStringExtra(TEXT)));

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(v -> this.onBackPressed());
        mToolbar.setTitle("");
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


}
