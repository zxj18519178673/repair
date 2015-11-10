package com.jb.repair.nfc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.jb.repair.R;

public class NdefViewer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndef_viewer);
        String action = getIntent().getAction();
        Log.e("Tag------", action);
    }

}
