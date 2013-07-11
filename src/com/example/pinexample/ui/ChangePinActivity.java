package com.example.pinexample.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pinexample.R;
import com.example.pinexample.utilities.Utilities;
import com.example.pinexample.utilities.NumpadKeyboard;

/**
 * ChangePinActivity class that creates a new PIN
 * 
 * @author drakuwa
 *
 */
public class ChangePinActivity extends Activity {
	
	private View numpad;
	private EditText pin_main, pin_copy;
    private Button save,skip;
    private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_pin);
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		initViews();
	}
	
	/**
	 * Initialize the views and set the click listeners
	 */
	private void initViews(){
		numpad = findViewById(R.id.numpad);
		pin_main = (EditText) findViewById(R.id.pinOriginal);
		pin_copy = (EditText) findViewById(R.id.pinCopy);
		pin_main.setCursorVisible(false);
		pin_copy.setCursorVisible(false);
		pin_main.setFocusable(false);
		pin_copy.setFocusable(false);
		
		// Initialize the NumpadKeyboard on the clicked EditText
		pin_main.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new NumpadKeyboard(ChangePinActivity.this, numpad, pin_main);
			}
		});
		pin_copy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new NumpadKeyboard(ChangePinActivity.this, numpad, pin_copy);
			}
		});
		
		skip = (Button) findViewById(R.id.pinSkip);
		skip.setOnClickListener(new OnClickListener() {
			@Override public void onClick(View arg0) {finish();}});
		
		// save the PIN, set the preference variable, or go back to the StartActivity
		save = (Button) findViewById(R.id.pinSave);
        save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String pin1 = pin_main.getText().toString().trim();
				String pin2 = pin_copy.getText().toString().trim();
				// check if the pins are 4 digits
				if(pin1.length()==4 && pin2.length()==4){
					// check if the pins are equal
					if(pin1.equalsIgnoreCase(pin2)){
						// pins are equal, start the app, and set the init. pref to true
						// set the firstRun to true
						Editor e = prefs.edit();
				        e.putString("pin", Utilities.sha1Hash(pin1));
				        e.commit();	finish();
					} else {Toast.makeText(ChangePinActivity.this, 
								R.string.pins_do_not_match, 
									Toast.LENGTH_SHORT).show();}
				} else {Toast.makeText(ChangePinActivity.this, 
							R.string.pin_length_error, 
								Toast.LENGTH_SHORT).show();}
			}
		});
	}
}
