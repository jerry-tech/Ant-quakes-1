package com.accounting.ant.ui.upgrade_agent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AgentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Upgrade Account");
    }

    public LiveData<String> getText() {
        return mText;
    }
}