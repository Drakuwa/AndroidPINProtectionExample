package com.example.pinexample.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pinexample.R;
import com.example.pinexample.utilities.Utilities;
import com.example.pinexample.utilities.NumpadKeyboard;

/**
 * This is the activity screen that represents the PIN protection.
 * @author drakuwa
 *
 */
public class PinActivity extends Activity {
	
	private View numpad;
	private EditText pin;
	private SharedPreferences prefs;
	private String whereTo;
	private int errorCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pin);
		if(getIntent().hasExtra("whereTo")) whereTo = getIntent().getStringExtra("whereTo");
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		initViews();
	}
	
	/**
	 * Initialize the views, the error counter, set the TextChanged listener
	 */
	private void initViews(){
		numpad = findViewById(R.id.numpad);
		pin = (EditText) findViewById(R.id.pin);
		new NumpadKeyboard(this, numpad, pin);
		
		pin.setFocusable(false);
		pin.setCursorVisible(false);
		pin.setClickable(false);
		pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start==3){
                    if(Utilities.sha1Hash(s.toString()).equalsIgnoreCase(prefs.getString("pin", ""))){
                    	if(whereTo.equalsIgnoreCase("main")){
                    	startActivity(new Intent(PinActivity.this, StartActivity.class));
                    	finish();}
                    	else if(whereTo.equalsIgnoreCase("settings")){
                    	startActivity(new Intent(PinActivity.this, ChangePinActivity.class));
                    	finish();}
                    } else {
                    	errorCount++;
                    	if(errorCount >= 5) finish();
                    	else {
                    		// show an error Toast message, and clear the field
                    		Toast.makeText(PinActivity.this, "Wrong PIN.", Toast.LENGTH_SHORT).show();
                    		pin.setText("");
                    	}
                    	//String h = Utilities.sha1Hash(pin.getText().toString().trim());
                    	//Log.d(Log.TAG, "NOT OK, SHA-1: "+h);pin.setText("");
                    }
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

	}
}
