package com.example.pinexample.utilities;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pinexample.R;

/**
 * My try to map a custom numpad keyboard. The class initializes the custom
 * numpad keyboard buttons, and binds them with the EditText field. 
 * 
 * @author drakuwa
 *
 */
public class NumpadKeyboard implements OnClickListener {

	Context context;
	View holder;
	EditText content;
	TextView one, two, three, four, five, six, seven, eight, nine, zero, dot,
			back;

	/**
	 * Class constructor which initializes the context c, the numpad view 
	 * holder, and the edit text field - content.
	 * @param c
	 * @param holder
	 * @param content
	 */
	public NumpadKeyboard(Context c, View holder, EditText content) {
		this.context = c;
		this.holder = holder;
		this.content = content;
		initView();
	}

	/**
	 * Initialize the views and set the click listeners
	 */
	private void initView() {
		one = (TextView) holder.findViewById(R.id.numpad1);
		two = (TextView) holder.findViewById(R.id.numpad2);
		three = (TextView) holder.findViewById(R.id.numpad3);
		four = (TextView) holder.findViewById(R.id.numpad4);
		five = (TextView) holder.findViewById(R.id.numpad5);
		six = (TextView) holder.findViewById(R.id.numpad6);
		seven = (TextView) holder.findViewById(R.id.numpad7);
		eight = (TextView) holder.findViewById(R.id.numpad8);
		nine = (TextView) holder.findViewById(R.id.numpad9);
		dot = (TextView) holder.findViewById(R.id.numpadDot);
		zero = (TextView) holder.findViewById(R.id.numpad0);
		back = (TextView) holder.findViewById(R.id.numpadBack);

		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		four.setOnClickListener(this);
		five.setOnClickListener(this);
		six.setOnClickListener(this);
		seven.setOnClickListener(this);
		eight.setOnClickListener(this);
		nine.setOnClickListener(this);
		dot.setOnClickListener(this);
		zero.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	/**
	 * Add the click actions to the buttons
	 */
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.numpad1:
			content.append("1");
			break;

		case R.id.numpad2:
			content.append("2");
			break;

		case R.id.numpad3:
			content.append("3");
			break;

		case R.id.numpad4:
			content.append("4");
			break;

		case R.id.numpad5:
			content.append("5");
			break;

		case R.id.numpad6:
			content.append("6");
			break;

		case R.id.numpad7:
			content.append("7");
			break;

		case R.id.numpad8:
			content.append("8");
			break;

		case R.id.numpad9:
			content.append("9");
			break;

		case R.id.numpadDot:
			content.append(".");
			break;

		case R.id.numpad0:
			content.append("0");
			break;

		case R.id.numpadBack:
			String str = content.getText().toString().trim();
			if (str.length() != 0) {
				str = str.substring(0, str.length() - 1);
				content.setText(str);
			}
			break;

		default:
			break;
		}
	}

}
