package com.accounting.ant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class AuthOtp extends AppCompatActivity {
	//Getting the URL for the API
	private static final String TAG = "RegisterActivity";

	private static final String URL_FOR_VERIFICATION = "https://api.antquakes.com.ng/register.php?action=Verify";

	private static final String URL_FOR_RESENDING_OTP = "https://api.antquakes.com.ng/register.php?action=Resend";
	//Creating a Progress Dialog.
	ProgressDialog progressDialog;

	//Getting the components.
	EditText otpone, otptwo, otpthree, otpfour, otpfive, otpsix;
	TextView countdown;
	Button verify;
	TextWatcher textWatcherOne = null, textWatcherTwo = null, textWatcherThree = null, textWatcherFour = null, textWatcherFive = null;

	//Creating the shared preference
	SharedPreferences preferences;
	//	SharedPreferences.Editor editor;
	//Retrieving the email with shared preferences
	String email;
	//Getting the action
	String action, resendAction;

	//Creating an object of the countdown timer
	CountDownTimer countDownTimer;
	//Setting the countdown timer to 29 seconds.
	int counter = 59;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth_otp);

		// Progress dialog
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);

		//Getting the email of the user with shared preference.
		preferences = getSharedPreferences("user_details", Context.MODE_PRIVATE);
		email = preferences.getString("Email", null);
//		email = "wijojames@gmail.com";

		//Setting the action
		action = "Verify";
		resendAction = "Resend";

		//Binding the components.
		otpone = findViewById(R.id.OtpOne);
		otptwo = findViewById(R.id.OtpTwo);
		otpthree = findViewById(R.id.OtpThree);
		otpfour = findViewById(R.id.OtpFour);
		otpfive = findViewById(R.id.OtpFive);
		otpsix = findViewById(R.id.OtpSix);
		verify = findViewById(R.id.verify);


		//text watcher for the first input field
		textWatcherOne = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				otptwo.requestFocus();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}

		};
		otpone.addTextChangedListener(textWatcherOne);

		//text watcher for the second input field
		textWatcherTwo = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				otpthree.requestFocus();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}

		};
		otptwo.addTextChangedListener(textWatcherTwo);

		//text watcher for the third input field
		textWatcherThree = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				otpfour.requestFocus();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}

		};
		otpthree.addTextChangedListener(textWatcherThree);

		//text watcher for the fourth input field
		textWatcherFour = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				otpfive.requestFocus();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}

		};
		otpfour.addTextChangedListener(textWatcherFour);

		//text watcher for the fifth input field
		textWatcherFive = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				otpsix.requestFocus();
			}

			@Override
			public void afterTextChanged(Editable s) {

			}

		};
		otpfive.addTextChangedListener(textWatcherFive);

		//Getting the value of the countdown timer
		countdown = findViewById(R.id.timer_otp);

		//Setting the countdown timer on the creation of the Authentication page.
		countdownTimer();

		//Validating the users OTP.
		verify.setOnClickListener(event -> {
			//Checking for empty OTP fields
			emptyChecker(otpone.getText().toString(), otptwo.getText().toString(), otpthree.getText().toString(),
					otpfour.getText().toString(), otpfive.getText().toString(), otpsix.getText().toString());
		});

	}

	//Method for countdown timer
	private void countdownTimer() {
		countDownTimer = new CountDownTimer(60000, 1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				countdown.setText(counter + " seconds");
				counter--;
			}

			@Override
			public void onFinish() {
				//Resending the OTP if the countdown elapses.
				resendOTP(resendAction, email);
				if (counter <= 0) {
					cancel();

					counter = 59;
					countdown.setText(counter + " seconds");
					counter--;
				}

				start();
			}
		}.start();

	}

	//Method to check for empty OTP
	private void emptyChecker(String one, String two, String three, String four, String five, String six) {
		if (one.equals("") || two.equals("") || three.equals("") || four.equals("") || five.equals("") || six.equals("")) {
			Toasty.custom(this, "You can not have an empty OTP!", R.drawable.warning_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
		} else {
			verify(action, email, otpone.getText().toString() + otptwo.getText().toString() + otpthree.getText().toString() + otpfour.getText().toString()
					+ otpfive.getText().toString() + otpsix.getText().toString()
			);

		}
	}

	//Method to send OTP
	private void verify(String action, String email, String otp) {
		// Tag used to cancel the request
		String cancel_req_tag = "verify";

		progressDialog.setMessage("VERIFYING!!!");
		showDialog();

		StringRequest strReq = new StringRequest(Request.Method.POST,
				URL_FOR_VERIFICATION, response -> {
			Log.d(TAG, "Verification Response: " + response);
			hideDialog();

			try {
				JSONObject jObj = new JSONObject(response);
				boolean error = jObj.getBoolean("error");

				if (!error) {
					Toasty.success(getApplicationContext(), "Hi " + email + ", Your account has been verified!", Toast.LENGTH_SHORT, true).show();
					//Stopping the countdown timer if OTP is correct
					countDownTimer.cancel();

					// Launch login activity
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
					finish();

				} else {

					String errorMsg = jObj.getString("text");
					Toasty.custom(getApplicationContext(),
							errorMsg, R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
				}
			} catch (JSONException e) {
//				e.printStackTrace();
				Toasty.custom(getApplicationContext(), "Network Error", R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
			}

		}, error -> {
			Log.e(TAG, "Verification Error: " + error.getMessage());
			Toasty.custom(getApplicationContext(),
					error.getMessage(), R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
			hideDialog();
		}) {
			@Override
			protected Map<String, String> getParams() {
				// Posting params to register url
				Map<String, String> params = new HashMap<>();
				params.put("action", action);
				params.put("email", email);
				params.put("otp", otp);
				return params;
			}
		};
		// Adding request to request queue
		AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);


	}

	//Method to resend OTP
	private void resendOTP(String action, String email) {

		// Tag used to cancel the request
		String cancel_req_tag = "resend";

		progressDialog.setMessage("Resending OTP!");
		showDialog();

		StringRequest strReq = new StringRequest(Request.Method.POST,
				URL_FOR_RESENDING_OTP, response -> {
			Log.d(TAG, "Resending OTP Response: " + response);
			hideDialog();

			try {
				JSONObject jObj = new JSONObject(response);
				boolean error = jObj.getBoolean("error");
				String message = jObj.getString("text");

				if (!error) {
					Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();

				} else {
					Toasty.custom(getApplicationContext(), message, R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}, error -> {
			Log.e(TAG, "Resending OTP Error: " + error.getMessage());
			Toasty.custom(getApplicationContext(),
					error.getMessage(), R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
			hideDialog();
		}) {
			@Override
			protected Map<String, String> getParams() {
				// Posting params to register url
				Map<String, String> params = new HashMap<>();
				params.put("action", action);
				params.put("email", email);
				return params;
			}
		};
		// Adding request to request queue
		AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);


	}


	//ProgressDialog Logic
	private void showDialog() {
		if (!progressDialog.isShowing())
			progressDialog.show();
	}

	private void hideDialog() {
		if (progressDialog.isShowing())
			progressDialog.dismiss();
	}


}
