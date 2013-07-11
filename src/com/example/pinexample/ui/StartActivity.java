package com.example.pinexample.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.pinexample.R;

/**
 * A simple Activity class with only one button that calls ChangePin, 
 * or Pin activity, depending on the PIN status.
 *  
 * @author drakuwa
 *
 */
public class StartActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Button settings = (Button)findViewById(R.id.pinSettings);
		settings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// check if there is an existing pin.
				if(prefs.getString("pin", "").length()>0){
					Intent pin = new Intent(StartActivity.this, PinActivity.class);
					pin.putExtra("whereTo", "settings");
					startActivity(pin);
				} else startActivity(new Intent(StartActivity.this, ChangePinActivity.class));
			}});
	}
}
