package com.example.pinexample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pinexample.R;

/**
 * A test welcome fragment
 * @author bojan.i
 *
 */
public final class WelcomeFragment extends Fragment {
	private static final String KEY_CONTENT = "WelcomeFragment:Content";
	private int mContent = -1;
	
	protected ViewGroup mapContainer;
	public static WelcomeFragment newInstance(int content) {
		WelcomeFragment fragment = new WelcomeFragment();

		fragment.mContent = content;
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getInt(KEY_CONTENT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_welcome, null);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_CONTENT, mContent);
	}
}
