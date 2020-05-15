package com.accounting.ant.ui.Carry1stCredit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Carry1stViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Carry1stViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}