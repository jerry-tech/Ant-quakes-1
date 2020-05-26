package com.accounting.ant.ui.TransHistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TransHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TransHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Wallet Balance");
    }

    public LiveData<String> getText() {
        return mText;
    }
}