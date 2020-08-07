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
 * {@link DashDataBundle.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashDataBundle#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashDataBundle extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashDataBundle() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashDataBundle.
     */
    // TODO: Rename and change types and number of parameters
    public static DashDataBundle newInstance(String param1, String param2) {
        DashDataBundle fragment = new DashDataBundle();
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
        View root =  inflater.inflate(R.layout.fragment_dash_data_bundle, container, false);


        //network type
        Spinner networkType = root.findViewById(R.id.networkTypeData);
        // using the array adapter class && the array adapter public constructor which accepts three parameters to get all items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                root.getContext(),//getting the application context(this);
                R.array.airtimeNetwork,
                R.layout.spinner_network
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        networkType.setAdapter(adapter);
        
        //for dataType
        Spinner dataType = root.findViewById(R.id.data_type);
        // using the array adapter class && the array adapter public constructor which accepts three parameters to get all items
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                root.getContext(),//getting the application context(this);
                R.array.dataType,
                R.layout.spinner_data
        );
        adapterType.setDropDownViewResource(R.layout.spinner_dropdown_item);
        dataType.setAdapter(adapterType);

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
