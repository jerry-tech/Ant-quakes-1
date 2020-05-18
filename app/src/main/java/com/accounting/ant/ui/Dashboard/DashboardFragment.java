package com.accounting.ant.ui.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.accounting.ant.DashboardOptions;
import com.accounting.ant.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel homeViewModel;
    private CardView airtimeCard, subCard, savingCard, transferCard, bulkCard, electricCard, convertCard, pinCard;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        homeViewModel.getText().observe(this, textView::setText);

        //intent for airtime
        airtimeCard = root.findViewById(R.id.cardClickAirtime);
        //intent for subscription
        subCard = root.findViewById(R.id.cardClickSubscription);
        //intent for Transfer
        transferCard = root.findViewById(R.id.cardClickTransfer);
        //intent for Bulk SMS
        bulkCard = root.findViewById(R.id.cardClickSms);
        //intent for Electrical Bills
        electricCard = root.findViewById(R.id.cardClickElectric);
        //intent for savings
        savingCard = root.findViewById(R.id.cardClickSavings);
        //intent for Airtime to Money
        convertCard = root.findViewById(R.id.cardClickConvert);
        //intent for Examination E-pin
        pinCard = root.findViewById(R.id.cardClickExam);

        //setting click listeners
        airtimeCard.setOnClickListener(event -> actionIntent("airtimeCard"));
        subCard.setOnClickListener(v -> actionIntent("subCard"));
        transferCard.setOnClickListener(v -> actionIntent("transferCard"));
        bulkCard.setOnClickListener(v -> actionIntent("bulkCard"));
        electricCard.setOnClickListener(v -> actionIntent("electricCard"));
        savingCard.setOnClickListener(v -> actionIntent("savingCard"));
        convertCard.setOnClickListener(v -> actionIntent("convertCard"));
        pinCard.setOnClickListener(v -> actionIntent("pinCard"));

        return root;
    }

    public void actionIntent(String btnValue){

        if(btnValue.equalsIgnoreCase("airtimeCard")){
            setIntent("0");
        }else if(btnValue.equalsIgnoreCase("subCard")){
            setIntent("1");
        }else if(btnValue.equalsIgnoreCase("transferCard")){
            setIntent("2");
        }else if(btnValue.equalsIgnoreCase("bulkCard")){
            setIntent("3");
        }else if(btnValue.equalsIgnoreCase("electricCard")){
            setIntent("4");
        }else if(btnValue.equalsIgnoreCase("savingCard")){
            setIntent("5");
        }else if(btnValue.equalsIgnoreCase("convertCard")){
            setIntent("6");
        }else if(btnValue.equalsIgnoreCase("pinCard")){
            setIntent("7");
        }


    }
    public void setIntent(String value){
        Intent intent = new Intent(getContext(), DashboardOptions.class);
        intent.putExtra("frag", value);
        startActivity(intent);
    }
}