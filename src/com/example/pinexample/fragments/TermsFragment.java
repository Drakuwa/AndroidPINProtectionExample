package com.example.pinexample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pinexample.R;

/**
 * A test fragment
 * @author drakuwa
 *
 */
public final class TermsFragment extends Fragment {
	private static final String KEY_CONTENT = "TermsFragment:Content";
	private int mContent = -1;
	
	protected ViewGroup mapContainer;
	public static TermsFragment newInstance(int content) {
		TermsFragment fragment = new TermsFragment();

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
		return inflater.inflate(R.layout.activity_terms, null);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_CONTENT, mContent);
	}
}
