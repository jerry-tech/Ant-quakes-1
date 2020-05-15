package com.accounting.ant.ui.FundWallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.accounting.ant.MainWallet;
import com.accounting.ant.R;
import com.google.android.material.textfield.TextInputEditText;

public class FundWalletFragment extends Fragment {

    private FundWalletViewModel galleryViewModel;
    Button button;
    TextInputEditText textInputAmount;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel = ViewModelProviders.of(this).get(FundWalletViewModel.class);

        View root = inflater.inflate(R.layout.fragment_fundwallet, container, false);

        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, textView::setText);

        button = root.findViewById(R.id.btnSubmit); //getting the id of the submit button
        textInputAmount = root.findViewById(R.id.txtAmountInput);//getting the id of the TextInputEditText


        button.setOnClickListener(v -> {
            String txtAmount = textInputAmount.getText().toString().trim();
            if (txtAmount.isEmpty()) {
                // Creating a Toast notification/message which is a little diff from the implementation in an activity
                Toast toast = Toast.makeText(
                    //getActivity(),"Custom Toast From Fragment",Toast.LENGTH_LONG
                    getActivity().getApplicationContext(), "Fund Wallet Amount Cannot be Empty", Toast.LENGTH_LONG
                );
                // Set the Toast display position layout center
                toast.setGravity(Gravity.CENTER, 0, 0);
                // Finally, show the toast
                toast.show();
            }else{
                Intent fundIntent = new Intent(getContext(), MainWallet.class);
                fundIntent.putExtra("inputAmount",txtAmount);
                startActivity(fundIntent);
            }

        });


        return root;


    }


}