package com.example.sonu.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.AdListener;
// [SNIPPET load_banner_ad]
// Load an ad into the AdView.
// [START load_banner_ad]
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
// [START_EXCLUDE]
import com.google.android.gms.ads.InterstitialAd;
// [END_EXCLUDE]

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private AdView mAdView;
    // [START_EXCLUDE]
    private InterstitialAd mInterstitialAd;
    private Button mLoadInterstitialButton;
    // [END_EXCLUDE]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // [END load_banner_ad]

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginSecondActivity();
            }

            @Override
            public void onAdLoaded() {

                if (mLoadInterstitialButton != null) {
                    mLoadInterstitialButton.setEnabled(true);
                }

            }

            @Override
            public void onAdFailedToLoad(int i) {
                Log.w(TAG, "onAdFailedToLoad:" + i);
            }
        });
        mLoadInterstitialButton = (Button) findViewById(R.id.load_interstitial_button);
        mLoadInterstitialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    beginSecondActivity();
                }
            }
        });
        mLoadInterstitialButton.setEnabled(mInterstitialAd.isLoaded());
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    // [END request_new_interstitial]

    private void beginSecondActivity() {
        Intent intent = new Intent(this, sources.class);
        startActivity(intent);
    }


    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        if (!mInterstitialAd.isLoaded()) {
            requestNewInterstitial();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
    // [END add_lifecycle_methods]

    @VisibleForTesting
    AdView getAdView() {
        return mAdView;
    }
}