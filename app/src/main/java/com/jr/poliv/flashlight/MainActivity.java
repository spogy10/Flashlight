package com.jr.poliv.flashlight;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends Activity {

    ImageButton flashButton;
    public static Camera cam;
    public boolean isFlashOn = false;
    public DialogFragment dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        flashButton = (ImageButton) findViewById(R.id.imageButton);
        dialog = new okDialog();


        if (flashlight_check()) {
            yesFlashlight();
            flashButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    yesFlashlight();
                }
            });
        } else {
            noFlashlight();
            flashButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    noFlashlight();
                }
            });
        }


    }

    private boolean flashlight_check() {
        return this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    private void noFlashlight() {
        changeButtonColour("warning");

        dialog.show(getFragmentManager(),"dialog");


    }

    private void yesFlashlight() {

        if (isFlashOn)
            turnFlashlightOff();
        else
            turnFlashlightOn();


    }

    private void turnFlashlightOn() {
        try {
            cam = Camera.open();
            Camera.Parameters p = cam.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(p);
            cam.startPreview();

            isFlashOn = true;
            changeButtonColour("on");

        }
        catch(Exception e){
            Toast.makeText(MainActivity.this, "Error turning flash light on", Toast.LENGTH_LONG).show();
        }

    }

    private void turnFlashlightOff()
    {
        try{
            cam.stopPreview();
            cam.release();

            isFlashOn = false;
            changeButtonColour("off");
        }
        catch(Exception e){
            Toast.makeText(MainActivity.this, "Error turning flash light off", Toast.LENGTH_LONG).show();
        }


    }

    private void changeButtonColour(String mode)
    {
        switch (mode) {
            case "warning": flashButton.setImageResource(R.drawable.yellow_button);
                break;

            case "on": flashButton.setImageResource(R.drawable.green_button);
                break;

            case "off": flashButton.setImageResource(R.drawable.red_button);
                break;
        }
    }



    @Override
    protected void onStop() {
        super.onStop();

        if (isFlashOn){
            turnFlashlightOff();
            isFlashOn = true;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(isFlashOn)
            turnFlashlightOn();

    }
}

