package com.example.twins.listofpictures;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class TimeDialogFragment extends DialogFragment {
    private static final String ARG_HOUR = "hour";
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
        if (getArguments() != null) {
            hour = getArguments().getString(ARG_HOUR);
        }

        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_notifications_active_24dp)
                .setTitle("now it is  " + hour)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                        }
                )
                .create();
    }
}
