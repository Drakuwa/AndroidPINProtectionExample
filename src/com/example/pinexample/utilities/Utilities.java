package com.example.pinexample.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.pinexample.R;


/**
 * A model class that resolves some of the business logic in the application.
 * Also as a part of the MVC(Model View Controller) pattern. It
 * presents changes to the UI thread through the Controller classes.
 * 
 * @author drakuwa
 */
public class Utilities {
	
	private Context ctx;
	
	/**
	 * Constructor of the Model class which initializes the activity context.
	 * 
	 * @param context
	 */
	public Utilities(Context context) {
		ctx = context;
	}
	
	/**
	 * Method that creates and shows an AlertDialog with a message passed with
	 * the txt parameter, and a PositiveButton "OK"
	 * 
	 * @param txt
	 */
	public void customDialog(final String txt) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(txt)
				.setIcon(R.drawable.ic_launcher)
				.setTitle(R.string.app_name)
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						});
		AlertDialog alert = builder.create();
		try {
			alert.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Encrypt the given toHash string to SHA1 and return the result
	 * @param toHash
	 * @return
	 */
	public static String sha1Hash(String toHash){
		String hash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] bytes = toHash.getBytes("UTF-8");
			digest.update(bytes, 0, bytes.length);
			bytes = digest.digest();
			StringBuilder sb = new StringBuilder();
			for(byte b : bytes){
				sb.append(String.format("%02X", b));
			}
			hash = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			Log.e(Log.TAG, e.getLocalizedMessage());
		} catch (UnsupportedEncodingException e) {
			Log.e(Log.TAG, e.getLocalizedMessage());
		}
		return hash;
	}

}
