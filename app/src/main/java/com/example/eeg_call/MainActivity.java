package com.example.eeg_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences sf;
    private boolean isRegistered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkRegister();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkRegister(){
        sf = getSharedPreferences("config",MODE_PRIVATE);
        isRegistered = sf.getBoolean("check",false);
        if(isRegistered == false){
            Log.d("debug","register false");
        }
        else{
            Log.d("debug","register true");
        }
    }
}
