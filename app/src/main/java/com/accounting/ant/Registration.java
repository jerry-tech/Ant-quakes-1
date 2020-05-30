package com.accounting.ant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class Registration extends AppCompatActivity {
	//Getting the URL for the API
	private static final String TAG = "RegisterActivity";
	private static final String URL_FOR_REGISTRATION = "https://antquakes.codeweb.com.ng/API/register.php";
	//Creating a Progress Dialog.
	ProgressDialog progressDialog;

	//Getting the components
	EditText firstName, lastName, username, email, refID, phoneNumber, countryCode, password, verify_password;

	//Using Shared preferences.
	SharedPreferences preferences;
	SharedPreferences.Editor editor;


	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		// Progress dialog
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);

		//Binding the components.
		firstName = findViewById(R.id.firstName);
		lastName = findViewById(R.id.lastName);
		username = findViewById(R.id.userName);
		email = findViewById(R.id.forgot_email);
		refID = findViewById(R.id.refId);
		phoneNumber = findViewById(R.id.phNumber);
		countryCode = findViewById(R.id.dialCode);
		password = findViewById(R.id.pass);
		verify_password = findViewById(R.id.confPass);
		button = findViewById(R.id.btnRegister);
		button.setOnClickListener(v -> {

			//Validating the phone number and registering the users account.
			numberChecker(countryCode.getText().toString(), phoneNumber.getText().toString());

		});
	}

	//Code to validate phone number
	private void numberChecker(String countryCode, String phonenumber) {
		String phone = "";
		String newCode = "";

		//Pattern for phone number
		boolean check = Pattern.compile("[0]*[\\d]{9,}").matcher(phonenumber).matches();

		//Pattern for country code
		boolean checker = Pattern.compile("[+][1-9]{1,3}").matcher(countryCode).matches();

		if (check && checker) {
			phone = phonenumber;
			newCode = countryCode;

			//Triggering the register page.
			registerUser(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), phone.toString(),
					username.getText().toString(), password.getText().toString(), verify_password.getText().toString(), refID.getText().toString()
			);
		} else {
			Toasty.custom(this, "Country Code or Phone number is wrong!", R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
		}

	}

	//Method to register users.
	private void registerUser(final String firstName, final String lastName, final String email, final String phone, final String username,
	                          final String password, final String password1, final String referral) {
		// Tag used to cancel the request
		String cancel_req_tag = "register";

		progressDialog.setMessage("REGISTERING!!!");
		showDialog();

		StringRequest strReq = new StringRequest(Request.Method.POST,
				URL_FOR_REGISTRATION, response -> {
			Log.d(TAG, "Register Response: " + response);
			hideDialog();

			try {
				JSONObject jObj = new JSONObject(response);
				boolean error = jObj.getBoolean("error");

				if (!error) {
					Toasty.success(getApplicationContext(), "Hi " + username + ", Your account has been created!", Toast.LENGTH_SHORT, true).show();
					//Using Shared preferences.
					preferences = getSharedPreferences("user_details", Context.MODE_PRIVATE);
					editor = preferences.edit();
					editor.putString("Email", email);
//					editor.remove("Email");
					editor.apply();

					// Launch login activity
					Intent intent = new Intent(Registration.this, AuthOtp.class);
					startActivity(intent);
					finish();

				} else {

					String errorMsg = jObj.getString("text");
					Toasty.custom(getApplicationContext(),
							errorMsg, R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}, error -> {
			Log.e(TAG, "Registration Error: " + error.getMessage());
			Toasty.custom(getApplicationContext(),
					error.getMessage(), R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
			hideDialog();
		}) {
			@Override
			protected Map<String, String> getParams() {
				// Posting params to register url
				Map<String, String> params = new HashMap<>();
				params.put("firstname", firstName);
				params.put("lastname", lastName);
				params.put("email", email);
				params.put("phone", phone);
				params.put("username", username);
				params.put("password", password);
				params.put("password1", password1);
				params.put("referral", referral);
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


	//intent and animation to go to login page
	public void nextLogin(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}


}
