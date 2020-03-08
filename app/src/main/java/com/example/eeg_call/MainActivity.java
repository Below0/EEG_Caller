package com.example.eeg_call;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences sf;
    private boolean isRegistered;
    private LinearLayout frame1;
    private ImageView btn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sf = getSharedPreferences("config",MODE_PRIVATE);
        initFCM();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame1 = (LinearLayout)findViewById(R.id.layout1);
        btn = (ImageView) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"클릭",Toast.LENGTH_SHORT).show();
            }
        });
        textView = (TextView)findViewById(R.id.token);
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
                        textView.setText(token);
                        Log.d("FCM", "Token : " + token);
                        checkRegister();
                    }
                });
    }

    public void checkRegister(){

        isRegistered = sf.getBoolean("check",false);
        if(isRegistered == false){
            frame1.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);
            Log.d("debug","register false");
        }
        else{
            frame1.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
            Log.d("debug","register true");
        }
    }
}
