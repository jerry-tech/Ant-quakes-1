package com.accounting.ant;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashSubscription.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashSubscription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashSubscription extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayAdapter<CharSequence> gotvAdapter,dstvAdapter,starAdapter;
    Spinner packageType;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DashSubscription() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashSubscription.
     */
    // TODO: Rename and change types and number of parameters
    public static DashSubscription newInstance(String param1, String param2) {
        DashSubscription fragment = new DashSubscription();
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
        View root =  inflater.inflate(R.layout.fragment_dash_subscription, container, false);

        Spinner cableType = root.findViewById(R.id.cableType);
        packageType = root.findViewById(R.id.packageType);

        // using the array adapter class && the array adapter public constructor which accepts three parameters to get all items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                root.getContext(),//getting the application context(this);
                R.array.cableTv,
                R.layout.spinner_cable
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cableType.setAdapter(adapter);

        // using the array adapter class && the array adapter public constructor which accepts three parameters to get all items
        ArrayAdapter<CharSequence> packageTp = ArrayAdapter.createFromResource(
                root.getContext(),//getting the application context(this);
                R.array.dom,
                R.layout.spinner_cable
        );
        packageTp.setDropDownViewResource(R.layout.spinner_dropdown_item);
        packageType.setAdapter(packageTp);

        //array adapter class && the array adapter FOR GOTV
        gotvAdapter = ArrayAdapter.createFromResource(
                root.getContext(),//getting the application context(this);
                R.array.gotvPackage,
                R.layout.spinner_cable
        );
        gotvAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        //array adapter class && the array adapter FOR DSTV
        dstvAdapter = ArrayAdapter.createFromResource(
                root.getContext(),//getting the application context(this);
                R.array.dstvPackage,
                R.layout.spinner_item
        );
        dstvAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        //array adapter class && the array adapter FOR STARTIMES
        starAdapter = ArrayAdapter.createFromResource(
                root.getContext(),//getting the application context(this);
                R.array.starPackage,
                R.layout.spinner_item
        );
        starAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);


        cableType.setOnItemSelectedListener(this);
        return root;
    }

        //second method under the itemListener interface
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedText = parent.getSelectedItem().toString().trim();

        if(selectedText.equalsIgnoreCase("Gotv")){
            packageType.setAdapter(gotvAdapter);
        }else if(selectedText.equalsIgnoreCase("Dstv")){
            packageType.setAdapter(dstvAdapter);
        }else if(selectedText.equalsIgnoreCase("StarTimes")){
            packageType.setAdapter(starAdapter);
        }
    }

//    second method under the itemListener interface
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
