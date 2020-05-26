package com.accounting.ant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
	//Using Shared preferences.
	SharedPreferences preferences;
	SharedPreferences.Editor editor;


	//Creating the tag to handle success or cancel events.
	private static final String TAG = "LoginActivity";
	//The url for the LoginAPI
	private static final String URL_FOR_LOGIN = "https://antquakes.codeweb.com.ng/API/login.php";

	ProgressDialog progressDialog;
	TextInputEditText username, password;
	Button loginBtn;
	TextView footerTime,forgotPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Progress dialog
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		//Getting the username, password and login button.
		username = findViewById(R.id.emailAdd);
		password = findViewById(R.id.txtPass);
		loginBtn = findViewById(R.id.loginBtn);


		//Setting the footer of the application to the current date.
		footerTime = findViewById(R.id.footerTime);

		Calendar calendar = Calendar.getInstance();
		footerTime.setText(calendar.getTime().toString());

		//Button to log in user.
		loginBtn.setOnClickListener(event -> {
			//Implementing the login method
			loginUser(username.getText().toString(), password.getText().toString());
		});

		//action listener for forgotPass
		forgotPass = findViewById(R.id.btnForgot);
		forgotPass.setOnClickListener(v ->
				btnForgotPass()
		);
	}


	//Logic for login button.
	private void loginUser(final String username, final String password) {
		// Tag used to cancel the request
		String cancel_req_tag = "login";
		progressDialog.setMessage("Logging you in...");
		showDialog();
		StringRequest strReq = new StringRequest(Request.Method.POST,
				URL_FOR_LOGIN, response -> {
			Log.d(TAG, "Register Response: " + response);
			hideDialog();
			try {
				JSONObject jObj = new JSONObject(response);
				boolean error = jObj.getBoolean("error");

				//Logic for correct login information
				if (!error) {

					//Using Shared preferences.
					preferences = getSharedPreferences("user_details", Context.MODE_PRIVATE);
					editor = preferences.edit();
					editor.putString("username", username);
					editor.apply();

					//Moving to the registration page.
					Intent intent = new Intent(this, UserOptions.class);
					startActivity(intent);
					finish();
				} else {

					String errorMsg = jObj.getString("text");
					Toast.makeText(getApplicationContext(),
							errorMsg, Toast.LENGTH_LONG).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}, error -> {
			Log.e(TAG, "Login Error: " + error.getMessage());
			Toast.makeText(getApplicationContext(),
					error.getMessage(), Toast.LENGTH_LONG).show();
			hideDialog();
		}) {
			//Mapping the users input with the database user information
			@Override
			protected Map<String, String> getParams() {
				// Posting params to login url
				Map<String, String> params = new HashMap<>();
				params.put("username", username);
				params.put("password", password);
				return params;
			}
		};
		// Adding request to request queue
		AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
	}

	private void showDialog() {
		if (!progressDialog.isShowing())
			progressDialog.show();
	}

	private void hideDialog() {
		if (progressDialog.isShowing())
			progressDialog.dismiss();
	}

	//Moving to the registration page.
	public void nextRegister(View view) {
		Intent intent = new Intent(this, Registration.class);
		startActivity(intent);

		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	}

	//Intent for forgot password
	public void btnForgotPass() {
		Intent intent = new Intent(this, ForgotPassword.class);
		startActivity(intent);
	}
}
