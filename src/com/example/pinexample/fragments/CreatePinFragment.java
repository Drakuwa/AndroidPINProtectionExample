package com.example.pinexample.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pinexample.R;
import com.example.pinexample.ui.StartActivity;
import com.example.pinexample.utilities.Log;
import com.example.pinexample.utilities.Utilities;
import com.example.pinexample.utilities.NumpadKeyboard;

/**
 * A fragment for creating the PIN number
 * @author bojan.i
 *
 */
public final class CreatePinFragment extends Fragment {
	
	private View main, numpad;
	private EditText pin_main, pin_copy;
    private Button save,skip;
    private SharedPreferences prefs;
	
	private static final String KEY_CONTENT = "CreatePinFragment:Content";
	private int mContent = -1;
	
	protected ViewGroup mapContainer;
	
	// Singleton
	public static volatile CreatePinFragment fragment = null;
	// Initialize the MainFragment instance
	public static CreatePinFragment newInstance(int content) {
		Log.d(Log.TAG, "newInstance() is called...");
		if (fragment == null) {
			synchronized (CreatePinFragment.class) {
				if (fragment == null) {
					fragment = new CreatePinFragment();
					fragment.mContent = content;
				}
			}
		}
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// for debuging
		Log.d(Log.TAG, "onCreate() is called...");
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getInt(KEY_CONTENT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// for debuging
		Log.d(Log.TAG, "onCreateView() is called...");
		
	    main =  inflater.inflate(R.layout.activity_create_pin, null);
	    initViews();
		return main;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		// for debuging
		Log.d(Log.TAG, "onSaveInstanceState() is called...");
		outState.putInt(KEY_CONTENT, mContent);
	}
	
	/**
	 * Initialize the views and set the click listeners
	 */
	private void initViews(){
		numpad = main.findViewById(R.id.numpad);
		pin_main = (EditText) main.findViewById(R.id.pinOriginal);
		pin_copy = (EditText) main.findViewById(R.id.pinCopy);
		pin_main.setCursorVisible(false);
		pin_copy.setCursorVisible(false);
		pin_main.setFocusable(false);
		pin_copy.setFocusable(false);
		
		// Initialize the NumpadKeyboard on the clicked EditText
		pin_main.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new NumpadKeyboard(getActivity(), numpad, pin_main);
			}
		});
		pin_copy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new NumpadKeyboard(getActivity(), numpad, pin_copy);
			}
		});
		
		skip = (Button)main.findViewById(R.id.pinSkip);
		skip.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Editor e = prefs.edit();
		        e.putBoolean("initialized", true);
		        e.commit();
				startActivity(new Intent(getActivity(), StartActivity.class));
				getActivity().finish();
			}});
		
		// save the PIN, set the preference variable, or skip to the StartActivity
		save = (Button)main.findViewById(R.id.pinSave);
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
				        e.putBoolean("initialized", true);
				        e.putString("pin", Utilities.sha1Hash(pin1));
				        e.commit();
						startActivity(new Intent(getActivity(), StartActivity.class));
						getActivity().finish();
					} else {Toast.makeText(getActivity(), 
								R.string.pins_do_not_match, 
									Toast.LENGTH_SHORT).show();}
				} else {Toast.makeText(getActivity(), 
							R.string.pin_length_error, 
								Toast.LENGTH_SHORT).show();}
			}
		});
	}
}
