package com.github.mobile.ui.roboactivities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by rkwagner on 2/13/17.
 */

public interface RoboActionBarDelegatorActivityInterface {
    void onRestart();
    void onStart();
    void onResume();
    void onPause();
    void onNewIntent( Intent intent );
    void onStop();
    void onDestroy();
    void onConfigurationChanged( Configuration newConfig );
    void onContentChanged();
    void onActivityResult( int requestCode, int resultCode, Intent data );

}

