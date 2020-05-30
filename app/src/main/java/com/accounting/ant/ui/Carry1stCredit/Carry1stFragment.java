package com.accounting.ant.ui.Carry1stCredit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.accounting.ant.R;

public class Carry1stFragment extends Fragment {

    private Carry1stViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(Carry1stViewModel.class);
        View root = inflater.inflate(R.layout.fragment_carry1stcredit, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, textView::setText);



        return root;
    }
}