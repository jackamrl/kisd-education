package com.locoapps.kidseducation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.locoapps.kidseducation.R;

public class SplashActivity extends Activity {
	/** Called when the activity is first created. */

	protected boolean _active = true;
	protected int _splashTime = 3000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_activity);

		/*if (isOnline() == true) {*/
			Thread splashTread = new Thread() {
				@Override
				public void run() {
					try {
						int waited = 0;
						while (_active && (waited < _splashTime)) {
							sleep(100);
							if (_active) {
								waited += 100;
							}
						}
					} catch (InterruptedException e) {
						e.toString();
					} finally {

						finish();
						Intent intent = new Intent(getApplicationContext(),MainActivity.class);
						startActivity(intent);
//						overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
					}
				}
			};
			splashTread.start();
		

	}


	@Override
	protected void onPause() {
		super.onPause();

	}
}