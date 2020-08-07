package com.accounting.ant;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashAirtime.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashAirtime#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashAirtime extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;


	private static final String AIRTIME_TAG = "AirtimeActivity";
	//The url for the AIRTIME API
	private static final String URL_AIRTIME = "https://api.antquakes.com.ng/test/airtime.php";

	//Getting the components from the xml file.
	TextInputEditText walletAmount, walletPhone, walletQuant;
	//Adding a progressbar.
	ProgressDialog progressDialog;
	Button buyAirtime;

	//Using the charsequence to hold the current value from the json array of networks.
	CharSequence text;

	//Creating the shared preference
	SharedPreferences preferences, walletPreference;
	//Getting Usernaqme and password and wallet
	String username, password, walletbalance;


	private OnFragmentInteractionListener mListener;

	String URL = "https://api.antquakes.com.ng/services/network.php";
	private static final String TAG = "NetworkActivity";


	public DashAirtime() {
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param param1 Parameter 1.
	 * @param param2 Parameter 2.
	 * @return A new instance of fragment DashAirtime.
	 */
	// TODO: Rename and change types and number of parameters
	public static DashAirtime newInstance(String param1, String param2) {
		DashAirtime fragment = new DashAirtime();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		//An array list to hold the value populated from the API
		ArrayList<String> Network = new ArrayList<>();

		//Creating an array to test on the spinner.
		View root = inflater.inflate(R.layout.fragment_dash_airtime, container, false);
		Spinner networkType = root.findViewById(R.id.networkType);

		//Getting the request from the server.
		RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
		StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, response -> {
			try {
				//Loading the array from the server.
				JSONArray jsonArray = new JSONArray(response);
				//Converting the array to a json object.
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				//Checking for errors from api
				boolean error = jsonObject.getBoolean("error");

				if (!error) {

					//Looping through the values from the array.
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject1 = jsonArray.getJSONObject(i);
						String networks = jsonObject1.getString("name");
						//Populating the array list.
						Network.add(networks);
					}

					//Creating the array adapter class to populate the spinner.
					ArrayAdapter<String> adapter = new ArrayAdapter<>(
							//Getting the activity.
							root.getContext(),
							//Getting the UI.
							R.layout.spinner_network,
							//Getting the array values.
							Network
					);
					//Setting the UI of the drop down.
					adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
					//Activating the adapter class.
					networkType.setAdapter(adapter);

				} else {
					String[] Netty = {"No Network Right Now"};
					//Creating the array adapter class to populate the spinner.
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							//Getting the activity.
							root.getContext(),
							//Getting the UI.
							R.layout.spinner_network,
							//Getting the array values.
							Netty
					);
					//Setting the UI of the drop down.
					adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
					//Activating the adapter class.
					networkType.setAdapter(adapter);
				}
			} catch (JSONException e) {
//				e.printStackTrace();
				Toasty.custom(root.getContext(), "Network Error", R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();

			}
		}, volleyError -> volleyError.printStackTrace());
		requestQueue.add(stringRequest);

		//Listening to events from the drop-down.
		networkType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				String selected = parentView.getItemAtPosition(position).toString();
				Context context = parentView.getContext();
				text = selected;
				int duration = Toast.LENGTH_SHORT;

				//Showing the selected network.
//				Toast toast = Toast.makeText(context, text, duration);
//				toast.show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		//Getting the Components.
		walletPhone = root.findViewById(R.id.walletPhone);
		walletAmount = root.findViewById(R.id.walletAmount);
		walletQuant = root.findViewById(R.id.walletQuant);
		//Button to buy airtime.
		buyAirtime = root.findViewById(R.id.buyAirtime);

		//Getting the userdetails with shared preference.
		preferences = getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
		username = preferences.getString("username", null);
		password = preferences.getString("password", null);


		//Getting the wallet balance with shared preference.
		walletPreference = getActivity().getSharedPreferences("walletDetails", Context.MODE_PRIVATE);
		walletbalance = walletPreference.getString("wallet", null);

		// Progress dialog
		progressDialog = new ProgressDialog(root.getContext());
		progressDialog.setCancelable(false);

		//Triggering the event to buy airtime.
		buyAirtime.setOnClickListener(e -> buyAirtimeClick(username, password, walletAmount.getText().toString(), text, walletPhone.getText().toString(), walletQuant.getText().toString()));

		//Adding the amount in the to the UI.
		walletAmount.setText("#" + walletbalance);

		// Inflate the layout for this fragment
		return root;

	}

	//Creating the event that occurs when the button is clicked to buy airtime.
	//Logic for login button.

	private void buyAirtimeClick(final String username, final String password, final String walletAmnt, final CharSequence network, final String number, final String amount) {

		//Validating users input.
		boolean checker = network == "" || number.equals("") || amount.equals("") || network == null;
		if (!checker) {

			boolean check = Pattern.compile("[0][789][01][\\d]{8}").matcher(number).matches();
			//On correct field entry, purchase the card
			if (check) {
				// Tag used to cancel the request
				String cancel_req_tag = "Airtime";
				progressDialog.setMessage("Purchasing Airtime...");
				showDialog();
				StringRequest strReq = new StringRequest(Request.Method.POST,
						URL_AIRTIME, response -> {
					Log.d(AIRTIME_TAG, "Register Response: " + response);
					hideDialog();
					try {
						JSONObject jObj = new JSONObject(response);
						boolean error = jObj.getBoolean("error");

						//Logic for correct airtime purchase
						if (!error) {
							Toasty.success(getActivity().getApplicationContext(), "Purchased Successfully.", Toast.LENGTH_SHORT, true).show();
							Intent intent = new Intent(getContext(), UserOptions.class);
							startActivity(intent);
							getActivity().finish();


						} else {

							String errorMsg = jObj.getString("text");
							Toasty.custom(getActivity().getApplicationContext(),
									errorMsg, R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}, error -> {
					Log.e(TAG, "Airtime Error: " + error.getMessage());
					Toasty.custom(getActivity().getApplicationContext(),
							error.getMessage(), R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
					hideDialog();
				}) {
					//Mapping the users input with the database user information
					@Override
					protected Map<String, String> getParams() {
						// Posting params to airtime url
						Map<String, String> params = new HashMap<>();
						params.put("username", username);
						params.put("password", password);
						params.put("network", network.toString());
						params.put("number", number);
						params.put("amount", amount);
						return params;
					}
				};
				// Adding request to request queue
				AppSingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);

			} else {
				Toasty.custom(getActivity().getApplicationContext(), "Incorrect phone number", R.drawable.close_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
			}

		} else {

			Toasty.custom(getActivity().getApplicationContext(), "Fields can not be empty", R.drawable.warning_24dp, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();

		}

	}

	private void showDialog() {
		if (!progressDialog.isShowing())
			progressDialog.show();
	}

	private void hideDialog() {
		if (progressDialog.isShowing())
			progressDialog.dismiss();
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
