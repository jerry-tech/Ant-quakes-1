package com.accounting.ant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class ForgotPassword extends AppCompatActivity {
	//Getting the URL for the API
	private static final String TAG = "ForgotPasswordActivity";
	private static final String URL_FOR_REGISTRATION = "https://antquakes.codeweb.com.ng/API/lostpassword.php";

	//Creating a Progress Dialog.
	ProgressDialog progressDialog;

	//Getting the components
	EditText forgot_username, forgot_email;
	Button reset_password;

	TextView backLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);

		// Progress dialog
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);

		//Binding the components
		forgot_username = findViewById(R.id.forgot_username);
		forgot_email = findViewById(R.id.forgot_email);
		reset_password = findViewById(R.id.reset_password);

		//Setting the functionality of the button.
		reset_password.setOnClickListener(event -> {
			//Trigerring the forgot password method
			forgotPassword(forgot_username.getText().toString(),forgot_email.getText().toString());

		});

		backLogin = findViewById(R.id.backBtnLogin);

		backLogin.setOnClickListener(v -> backToLogin());
	}


	//Forgot password Method
	private void forgotPassword(final String username, final String email) {


		// Tag used to cancel the request
		String cancel_req_tag = "forgotpassword";

		progressDialog.setMessage("Sending New Password!!!");
		showDialog();

		StringRequest strReq = new StringRequest(Request.Method.POST,
				URL_FOR_REGISTRATION, response -> {
			Log.d(TAG, "ForgotPassword Response: " + response);
			hideDialog();

			try {
				JSONObject jObj = new JSONObject(response);
				boolean error = jObj.getBoolean("error");

				if (!error) {
					Toast.makeText(getApplicationContext(), jObj.getString("text"), Toast.LENGTH_SHORT).show();

					// Launch login activity
					Intent intent = new Intent(this, MainActivity.class);
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
			Log.e(TAG, "ForgotPassword Error: " + error.getMessage());
			Toast.makeText(getApplicationContext(),
					error.getMessage(), Toast.LENGTH_LONG).show();
			hideDialog();
		}) {
			@Override
			protected Map<String, String> getParams() {
				// Posting params to register url
				Map<String, String> params = new HashMap<>();
				params.put("username", username);
				params.put("email", email);
				return params;
			}
		};
		// Adding request to request queue
		AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);


	}

	//    Intent for going back to login
	public void backToLogin() {
		Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
		startActivity(intent);
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

