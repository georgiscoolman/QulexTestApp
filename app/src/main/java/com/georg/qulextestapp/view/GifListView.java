package com.georg.qulextestapp.view;

import java.util.ArrayList;

/**
 * Created by georg on 23.09.2016.
 */
public interface GifListView extends MVPView {
    void startUpdate();

    void stopUpdate();

    void showMessage(String title, String message);

    void setGifItemList(ArrayList<String> gifItemList);
}
