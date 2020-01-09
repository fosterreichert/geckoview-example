package com.foster.website;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoView;

public class MainActivity extends AppCompatActivity {

    // variables
    GeckoView geckoView;
    GeckoSession geckoSession;
    GeckoRuntime geckoRuntime;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // opens a new session (this MUST be before "setSession" or it WILL crash)
        geckoSession.open(geckoRuntime);
        // sets the geckoview session
        geckoView.setSession(geckoSession);
        // loads your url
        geckoSession.loadUri(url);
    }

    // initialize variables
    private void init(){
        geckoView = findViewById(R.id.geckoview);
        geckoSession = new GeckoSession();
        geckoRuntime = GeckoRuntime.create(this);
        url = "https://example.com";
    }

    // overrides the current back button movement. this allows to go back to the previous site visited (if any)
    @Override
    public void onBackPressed() {
        geckoSession.goBack();
    }

    // overrides the current onDestroy method so we can add in our own code
    @Override
    protected void onDestroy() {
        // closes the geckoSession (duh)
        geckoSession.close();

        super.onDestroy();
    }
}

// code by foster reichert
