package com.accounting.ant;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashElectric.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashElectric#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashElectric extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashElectric() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashElectric.
     */
    // TODO: Rename and change types and number of parameters
    public static DashElectric newInstance(String param1, String param2) {
        DashElectric fragment = new DashElectric();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dash_electric, container, false);

        //dropdown for wallet amount
        Spinner walletSpinner = root.findViewById(R.id.walletElectric);
        ArrayAdapter<CharSequence> adapterWallet = ArrayAdapter.createFromResource(
                root.getContext(),
                R.array.walletAmt,
                R.layout.spinner_wallet
        );
        adapterWallet.setDropDownViewResource(R.layout.spinner_dropdown_item);
        walletSpinner.setAdapter(adapterWallet);

        //dropdown for electric company
        Spinner electricSpinner = root.findViewById(R.id.electricComp);
        ArrayAdapter<CharSequence> adapterElectric = ArrayAdapter.createFromResource(
                root.getContext(),
                R.array.electricCompany,
                R.layout.spinner_electric
        );
        adapterElectric.setDropDownViewResource(R.layout.spinner_dropdown_item);
        electricSpinner.setAdapter(adapterElectric);


        //dropdown for meter type
        Spinner discoSpinner = root.findViewById(R.id.meterType);
        ArrayAdapter<CharSequence> adapterMeter = ArrayAdapter.createFromResource(
                root.getContext(),
                R.array.meterType,
                R.layout.spinner_meter
        );
        adapterMeter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        discoSpinner.setAdapter(adapterMeter);


        return root;
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
