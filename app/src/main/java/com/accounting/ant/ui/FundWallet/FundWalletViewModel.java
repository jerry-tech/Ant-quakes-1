package com.accounting.ant.ui.FundWallet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FundWalletViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FundWalletViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}