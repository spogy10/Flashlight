package com.jr.poliv.flashlight;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;

/**
 * Created by poliv on 4/10/2016.
 */
public class okDialog extends DialogFragment {
    @Override
    public AlertDialog onCreateDialog(Bundle arg0) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Error: No flashlight detected")
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Do nothing in MainActity
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
