package com.accounting.ant.ui.upgrade_agent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.accounting.ant.R;

public class AgentFragment extends Fragment {

    private AgentViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(AgentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_agent, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, textView::setText);


        //spinner dropdown for UpgradePlan
        Spinner upgrade = root.findViewById(R.id.upgradePlan);
        ArrayAdapter upgradeAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.upgradePlan,
                R.layout.spinner_upgrade
        );
        upgradeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        upgrade.setAdapter(upgradeAdapter);

        //spinner dropdown for currentPlan
        Spinner currentPlan = root.findViewById(R.id.currentPlan);
        ArrayAdapter currentAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.currentPlan,
                R.layout.spinner_plan
        );
        currentAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        currentPlan.setAdapter(currentAdapter);

        return root;
    }
}