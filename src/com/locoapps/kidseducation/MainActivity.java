package com.locoapps.kidseducation;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Build;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.locoapps.kidseducation.R;

/*
	Main activity
* 	Activity2: Alphabet Vietnamese Board
*	Activity 3: Letter and images
*	Activity 4: Learn write letter
*	Activity 5: List story.
* 	Activity 6: Play audio story
*/
public class MainActivity extends Activity implements OnClickListener {

	ImageButton AlphabetActivity, NumberActivity, ShapesActivity, ColorActivity;
	int id;
	private MediaPlayer mediaPlayer;
	private InterstitialAd interstitial;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AlphabetActivity = (ImageButton)findViewById(R.id.AlphabetActivity);//Board alphabet vietnamese
		NumberActivity = (ImageButton)findViewById(R.id.NumberActivity);
		ShapesActivity = (ImageButton)findViewById(R.id.ShapesActivity);
		ColorActivity = (ImageButton)findViewById(R.id.ColorActivity);
		
		
		AlphabetActivity.setOnClickListener(this);
		NumberActivity.setOnClickListener(this);
		ShapesActivity.setOnClickListener(this);
		ColorActivity.setOnClickListener(this);
		
		interstitial = new InterstitialAd(this);
	    interstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

	    // Create ad request.
	    AdRequest adRequest = new AdRequest.Builder().build();
	    
	    AdView mAdView = (AdView) findViewById(R.id.adView);
	    mAdView.loadAd(adRequest);

	    // Begin loading your interstitial.
	    interstitial.loadAd(adRequest);
		
	}

	public void displayInterstitial() {
	    if (interstitial.isLoaded()) {
	      interstitial.show();
	    }
	  }
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		 displayInterstitial();
		 finish();
		
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.AlphabetActivity){
			//View Activity2
			Intent intent = new Intent(MainActivity.this,AlphabetActivity.class);
			intent.putExtra("id", 1);
			startActivity(intent);
			stopAudio();
		}
		if(v.getId()==R.id.NumberActivity){
			//View Activity2
			Intent intent = new Intent(MainActivity.this,NumberActivity.class);
			intent.putExtra("id", 2);
			startActivity(intent);
			stopAudio();
		}
		if(v.getId()==R.id.ShapesActivity){
			//View Activity2
			Intent intent = new Intent(MainActivity.this,ShapesActivity.class);
			intent.putExtra("id", 2);
			startActivity(intent);
			stopAudio();
		}
		if(v.getId()==R.id.ColorActivity){
			//View Activity2
			Intent intent = new Intent(MainActivity.this,ColorActivity.class);
			intent.putExtra("id", 2);
			startActivity(intent);
			stopAudio();
		}
	}
	
	//Play Audio
	private void playAudio(int paramInt) {
		this.mediaPlayer = MediaPlayer.create(this, paramInt);
		this.mediaPlayer.setScreenOnWhilePlaying(true);
		this.mediaPlayer.start();
	}
	//Stop Audio
	private void stopAudio() {
		try {
			if (this.mediaPlayer != null) {
				if (this.mediaPlayer.isPlaying())
					this.mediaPlayer.stop();
				this.mediaPlayer.release();
				this.mediaPlayer = null;
			}
			return;
		} catch (Exception localException) {
			while (true)
				System.out.println("XML Pasing Excpetion = " + localException);
		}
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		stopAudio();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopAudio();
	}
}
