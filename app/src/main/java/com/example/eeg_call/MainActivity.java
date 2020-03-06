package com.example.eeg_call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences sf;
    private boolean isRegistered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initFCM();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initFCM(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()){
                            Log.w("FCM","getInstanceID failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("FCM", "Token : " + token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT);
                        checkRegister();
                    }
                });
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
