package com.github.mobile.ui.roboactivities;

import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.google.inject.Key;

import java.util.HashMap;
import java.util.Map;

import roboguice.RoboGuice;
import roboguice.activity.event.OnActivityResultEvent;
import roboguice.activity.event.OnConfigurationChangedEvent;
import roboguice.activity.event.OnContentChangedEvent;
import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.activity.event.OnNewIntentEvent;
import roboguice.activity.event.OnPauseEvent;
import roboguice.activity.event.OnRestartEvent;
import roboguice.activity.event.OnResumeEvent;
import roboguice.activity.event.OnStartEvent;
import roboguice.activity.event.OnStopEvent;
import roboguice.event.EventManager;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

/**
 * A subclass of {@link AccountAuthenticatorActivity} that provides dependency injection
 * with RoboGuice.
 *
 * @author Marcus Better
 */
public class RoboActionBarAccountAuthenticatorActivity extends RoboActionBarDelegatorActivity implements RoboContext{
    protected EventManager eventManager;
    protected HashMap<Key<?>, Object> scopedObjects = new HashMap<>();
    AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
    private Bundle mResultBundle = null;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            final RoboInjector injector = RoboGuice.getInjector(this);
            eventManager = injector.getInstance(EventManager.class);
            injector.injectMembersWithoutViews(this);
            super.onCreate(savedInstanceState);

            mAccountAuthenticatorResponse =
                getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);

            if (mAccountAuthenticatorResponse != null) {
                mAccountAuthenticatorResponse.onRequestContinued();
            }

            eventManager.fire(new OnCreateEvent(savedInstanceState));
        }

    public void finish() {
        if (mAccountAuthenticatorResponse != null) {
            // send the result bundle back if set, otherwise send an error.
            if (mResultBundle != null) {
                mAccountAuthenticatorResponse.onResult(mResultBundle);
            } else {
                mAccountAuthenticatorResponse.onError(AccountManager.ERROR_CODE_CANCELED,
                        "canceled");
            }
            mAccountAuthenticatorResponse = null;
        }
        super.finish();
    }
    /*
       @Override
       public void onRestart() {
       super.onRestart();
       eventManager.fire(new OnRestartEvent());
       }

       @Override
       public void onStart() {
       super.onStart();
       eventManager.fire(new OnStartEvent());
       }

       @Override
       public void onResume() {
       super.onResume();
       eventManager.fire(new OnResumeEvent());
       }

       @Override
       public void onPause() {
       super.onPause();
       eventManager.fire(new OnPauseEvent());
       }

       @Override
       public void onNewIntent( Intent intent ) {
       super.onNewIntent(intent);
       eventManager.fire(new OnNewIntentEvent());
       }

       @Override
       public void onStop() {
       try {
       eventManager.fire(new OnStopEvent());
       } finally {
       super.onStop();
       }
       }

       @Override
       public void onDestroy() {
       try {
       eventManager.fire(new OnDestroyEvent());
       } finally {
       try {
       RoboGuice.destroyInjector(this);
       } finally {
       super.onDestroy();
       }
       }
       }

       @Override
       public void onConfigurationChanged(Configuration newConfig) {
       final Configuration currentConfig = getResources().getConfiguration();
       super.onConfigurationChanged(newConfig);
       eventManager.fire(new OnConfigurationChangedEvent(currentConfig, newConfig));
       }

       @Override
       public void onContentChanged() {
       super.onContentChanged();
       RoboGuice.getInjector(this).injectViewMembers(this);
       eventManager.fire(new OnContentChangedEvent());
       }

       @Override
       public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       eventManager.fire(new OnActivityResultEvent(requestCode, resultCode, data));
}

@Override
public Map<Key<?>, Object> getScopedObjectMap() {
    return scopedObjects;
}*/
}

