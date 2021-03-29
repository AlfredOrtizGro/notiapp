package com.teambaa.notiapp.ui.acerca_de;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AcercaDeViewModel extends ViewModel
{

    private MutableLiveData<String> mText;

    public AcercaDeViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("ACERCA DE NOTIAPP");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}