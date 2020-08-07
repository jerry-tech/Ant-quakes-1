package com.accounting.ant.api;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.accounting.ant.AppSingleton;
import com.accounting.ant.R;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Balance extends Fragment {
	public String trying;

	//Creating the tag to handle success or cancel events.
	private static final String TAG = "BalanceActivity";
	//The url for the BalanceAPI
	private static final String URL_FOR_BALANCE = "https://api.antquakes.com.ng/services/balance.php";

	//Method to get the balance and commission
	public void getBalance(final String username, final String password, final TextView walletBalance, final TextView commission) {
		// Tag used to cancel the request
		String cancel_req_tag = "balance";
		StringRequest strReq = new StringRequest(Request.Method.POST,
				URL_FOR_BALANCE, response -> {
			Log.d(TAG, "Balance Response: " + response);
			try {
				//Loading the array from the server.
				JSONArray jsonArray = new JSONArray(response);
				//Converting the array to a json object.
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				//Checking for errors from api
				boolean error = jsonObject.getBoolean("error");

				String wallet = jsonObject.getString("Wallet");
				String commissions = jsonObject.getString("Commssion");
				//Logic for correct login information
				if (!error) {
					//Using Shared preferences.
//					preferences = getActivity().getSharedPreferences("walletDetails", Context.MODE_PRIVATE);
//					editor = preferences.edit();
//					editor.putString("wallet", wallet);
//					editor.apply();
					walletBalance.setText("#" + wallet);
					commission.setText("#" + commissions);
				} else {

					String errorMsg = jsonObject.getString("text");
//					Toasty.custom(getActivity().getApplicationContext(),
//							errorMsg, R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}, error -> {
			Log.e(TAG, "Login Error: " + error.getMessage());
//			Toasty.custom(getActivity().getApplicationContext(),
//					error.getMessage(), R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
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
		AppSingleton.getInstance(getActivity()).addToRequestQueue(strReq, cancel_req_tag);


	}


	//Method to get the balance
	public void getBalance(final String username, final String password, final TextView walletBalance) {
		// Tag used to cancel the request
		String cancel_req_tag = "balance";
		StringRequest strReq = new StringRequest(Request.Method.POST,
				URL_FOR_BALANCE, response -> {
			Log.d(TAG, "Balance Response: " + response);
			try {
				//Loading the array from the server.
				JSONArray jsonArray = new JSONArray(response);
				//Converting the array to a json object.
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				//Checking for errors from api
				boolean error = jsonObject.getBoolean("error");

				String wallet = jsonObject.getString("Wallet");
				String commissions = jsonObject.getString("Commssion");

				//Logic for correct login information
				if (!error) {
					trying += wallet+"Boy";
					walletBalance.setText("#" + wallet);

				} else {

					String errorMsg = jsonObject.getString("text");
					Toasty.custom(getActivity(),
							errorMsg, R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}, error -> {
			Log.e(TAG, "Login Error: " + error.getMessage());
			Toasty.custom(getActivity(),
					error.getMessage(), R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
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
		AppSingleton.getInstance(getActivity()).addToRequestQueue(strReq, cancel_req_tag);


	}

}
