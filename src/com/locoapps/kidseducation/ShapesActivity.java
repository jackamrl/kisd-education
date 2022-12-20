package com.locoapps.kidseducation;

import com.locoapps.kidseducation.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.locoapps.kidseducation.GF.GF;
import com.locoapps.kidseducation.GF.GF.SimpleGestureListener;

import android.app.Activity;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ShapesActivity extends Activity implements SimpleGestureListener{

	private ImageView imgShape;
	private MediaPlayer mediaPlayer;
	private GF detector;
	private int position;
	private long lastUpdate = -1;
	private float x, y, z;
	private float last_x, last_y, last_z;
	private static final int SHAKE_THRESHOLD = 800;
	private Button btnPrevious, btnNext , play;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shapesactivity);
		playAudio(Constants.SHAPES_SOUND[position]);
		AdView mAdView = (AdView) findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    mAdView.loadAd(adRequest);

		position = getIntent().getExtras().getInt("position");
		imgShape = (ImageView) findViewById(R.id.imgShape);
		detector = new GF(this, this);
		
		imgShape.setImageResource(Constants.SHAPES_IMAGES[position]);

		imgShape.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Play word audio when tap in Word Images
				playAudio(Constants.SHAPES_SOUND[position]);
			}
		});
		
		btnPrevious = (Button)findViewById(R.id.btnPrev3);
		btnNext = (Button)findViewById(R.id.btnNex3);
		play = (Button)findViewById(R.id.play);
		
		btnPrevious.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (position > 0) {
					updateActivity(position - 1);
					position = position - 1;
				} else {
					updateActivity((Constants.SHAPES_IMAGES.length - 1));
					position = Constants.SHAPES_IMAGES.length - 1;
				}
			}
		});
		
		btnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (position < (Constants.SHAPES_IMAGES.length - 1)) {
					updateActivity(position + 1);
					position = position + 1;
				} else {
					updateActivity(0);
					position = 0;
				}
			}
		});
	
	
	play.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			playAudio(Constants.SHAPES_SOUND[position]);
		}
	});
}

	private void updateActivity(final int position) {
		imgShape.setImageResource(Constants.SHAPES_IMAGES[position]);
		playAudio(Constants.SHAPES_SOUND[position]);

		imgShape.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Play word audio when tap in Word Images
				playAudio(Constants.SHAPES_SOUND[position]);
			}
		});
	}

	// Play audio
	private void playAudio(int paramInt) {
		stopAudio();
		if (this.mediaPlayer != null)
			this.mediaPlayer.reset();
		this.mediaPlayer = MediaPlayer.create(this, paramInt);
		this.mediaPlayer.setScreenOnWhilePlaying(true);
		this.mediaPlayer.start();
	}

	// Stop audio
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
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) {
		// TODO Auto-generated method stub
		switch (direction) {

		case GF.SWIPE_RIGHT:
			if (this.position > 0) {
				updateActivity(position - 1);
				position = position - 1;
			} else {
				updateActivity(0);
				position = 0;
			}
			break;
		case GF.SWIPE_LEFT:
			if (this.position < (Constants.SHAPES_IMAGES.length - 1)) {
				updateActivity(position + 1);
				position = position + 1;
			} else {
				updateActivity((Constants.SHAPES_IMAGES.length - 1));
				position = Constants.SHAPES_IMAGES.length - 1;
			}
			break;
		}

	}

	protected void onPause() {
		super.onPause();
	}

}