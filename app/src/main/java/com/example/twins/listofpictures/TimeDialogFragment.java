package com.example.twins.listofpictures;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class TimeDialogFragment extends DialogFragment {
    private static final String ARG_HOUR = "hour";
    private static final String URL = "https://static-s.aa-cdn.net/img/ios/928952797/fd4ca6f3830886a6d91716d625c2a9bd?v=1";
    private String hour;


    public TimeDialogFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TimeDialogFragment newInstance(String time) {
        TimeDialogFragment fragment = new TimeDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HOUR, time);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        ImageView imageView = new ImageView(context);

        if (getArguments() != null) {
            hour = getArguments().getString(ARG_HOUR);
        }

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_notifications_active_24dp)
                .setView(imageView)
                .setTitle("now it is  " + hour)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                )
                .create();

        Picasso.with(context)
                .load(URL)
                .placeholder(R.drawable.ic_crop_original_100dp)
                .into(imageView);

        return dialog;
    }
}
