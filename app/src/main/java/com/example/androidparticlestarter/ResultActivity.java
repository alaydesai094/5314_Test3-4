package com.example.androidparticlestarter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import io.particle.android.sdk.cloud.ParticleCloud;
import io.particle.android.sdk.cloud.ParticleCloudSDK;
import io.particle.android.sdk.cloud.ParticleDevice;
import io.particle.android.sdk.cloud.ParticleEvent;
import io.particle.android.sdk.cloud.ParticleEventHandler;
import io.particle.android.sdk.cloud.exceptions.ParticleCloudException;
import io.particle.android.sdk.utils.Async;


public class ResultActivity extends AppCompatActivity {


    private final String TAG="ALAY";

    // MARK: Particle Account Info
    private final String PARTICLE_USERNAME = "alaydesai094@gmail.com";
    private final String PARTICLE_PASSWORD = "Alaydesai009";

    // MARK: Particle device-specific info
    private final String DEVICE_ID = "2a002e001447363333343437";

    // MARK: Particle Publish / Subscribe variables
    private long subscriptionId;

    // MARK: Particle device
    private ParticleDevice mDevice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // create the outlet for the label
        TextView t = (TextView) findViewById(R.id.txt4);

        SharedPreferences prefs = getSharedPreferences("userName", MODE_PRIVATE);
        String restoredText = prefs.getString("name", null);
        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            t.setText("Hello " + name + "!!");
        }

        TextView result = (TextView) findViewById(R.id.resultTxt);


        // 1. Initialize your connection to the Particle API
        ParticleCloudSDK.init(this.getApplicationContext());

        // 2. Setup your device variable
        getDeviceFromCloud();

    }


    /**
     * Custom function to connect to the Particle Cloud and get the device
     */
    public void getDeviceFromCloud() {
        // This function runs in the background
        // It tries to connect to the Particle Cloud and get your device
        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {

            @Override
            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
                particleCloud.logIn(PARTICLE_USERNAME, PARTICLE_PASSWORD);
                mDevice = particleCloud.getDevice(DEVICE_ID);
                return -1;
            }

            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "Successfully got device from Cloud");
            }

            @Override
            public void onFailure(ParticleCloudException exception) {
                Log.d(TAG, exception.getBestMessage());
            }
        });
    }


    public void resultButtonPressed(View view)
    {
        Log.d("ALAY", "Button pressed result;");

//        // check if device is null
//        // if null, then show error
//        if (mDevice == null) {
//            Log.d(TAG, "Cannot find device");
//            return;
//        }
//
//
//        Async.executeAsync(ParticleCloudSDK.getCloud(), new Async.ApiWork<ParticleCloud, Object>() {
//
//            @Override
//            public Object callApi(@NonNull ParticleCloud particleCloud) throws ParticleCloudException, IOException {
//                subscriptionId = ParticleCloudSDK.getCloud().subscribeToAllEvents(
//                        "broadcastMessage",  // the first argument, "eventNamePrefix", is optional
//                        new ParticleEventHandler() {
//                            public void onEvent(String eventName, ParticleEvent event) {
//                                Log.i(TAG, "Received event with payload: " + event.dataPayload);
//                            }
//
//                            public void onEventError(Exception e) {
//                                Log.e(TAG, "Event error: ", e);
//                            }
//                        });
//
//
//                return -1;
//            }
//
//            @Override
//            public void onSuccess(Object o) {
//                Log.d(TAG, "Successfully got device from Cloud");
//            }
//
//            @Override
//            public void onFailure(ParticleCloudException exception) {
//                Log.d(TAG, exception.getBestMessage());
//            }
//        });




    }



}

