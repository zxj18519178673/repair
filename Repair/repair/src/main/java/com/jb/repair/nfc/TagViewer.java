/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jb.repair.nfc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jb.repair.R;
import com.jb.repair.check.activity.CheckRegisActivity;
import com.jb.repair.ledger.db.Db;
import com.jb.repair.nfc.record.ParsedNdefRecord;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * An {@link Activity} which handles a broadcast of a new tag that the device
 * just discovered.
 */
public class TagViewer extends Activity {

    static final String TAG = "ViewTag";

    /**
     * This activity will finish itself in this amount of time if the user
     * doesn't do anything.
     */
    static final int ACTIVITY_TIMEOUT_MS = 1 * 1000;

    TextView mTitle;

    LinearLayout mTagContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_viewer);
        mTagContent = (LinearLayout) findViewById(R.id.list);
        mTitle = (TextView) findViewById(R.id.title);
        resolveIntent(getIntent());
    }

    void resolveIntent(Intent intent) {
        // Parse the intent
        String action = intent.getAction();
        Log.e("Tag",action);

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            // When a tag is discovered we send it to the service to be save. We
            // include a PendingIntent for the service to call back onto. This
            // will cause this activity to be restarted with onNewIntent(). At
            // that time we read it from the database and view it.

            byte[] bytesId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);



            String tagId = bytesToHexString(bytesId);//将其转换成字符串格式
            Log.e("Tag--",tagId);



            Cursor cursor = Db.getInstance(TagViewer.this).getCursor("Select nfc_ID from TB_SI_PLACE");
            while (cursor.moveToNext()){
                String tag = cursor.getString(cursor.getColumnIndex("nfc_ID"));
                String tag1 = tag.replaceAll(":", "");
                Log.e("Tag--",tag1);
                if (tagId.equals(tag1)){
                    Intent intent1 = new Intent(TagViewer.this,CheckRegisActivity.class);
                    String local = Db.getInstance(TagViewer.this).getName("palce_id","TB_SI_PLACE","nfc_ID",tagId);
                    Log.e("Tag--",local);
                    intent1.putExtra("posId",local);
                    startActivity(intent1);
                    finish();
                }
            }




            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs;
            Log.e("Tag","3333");
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                    Log.e("Tag","4444");
                }
            } else {
                // Unknown tag type
                byte[] empty = new byte[] {};
                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
                NdefMessage msg = new NdefMessage(new NdefRecord[] {record});
                msgs = new NdefMessage[] {msg};
                Log.e("Tag","5555");
                Log.e("Tag",msgs.toString());//

            }
            // Setup the views
            setTitle(R.string.title_scanned_tag);
//            setTitle(msgs.toString());

            buildTagViews(msgs);
        } else {
            Log.e(TAG, "Unknown intent " + intent);
            Log.e("Tag","6666");
            finish();
            return;
        }
    }

    void buildTagViews(NdefMessage[] msgs) {
        Log.e("Tag","7777");
        if (msgs == null || msgs.length == 0) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout content = mTagContent;
        // Clear out any old views in the content area, for example if you scan
        // two tags in a row.
        content.removeAllViews();
        // Parse the first message in the list
        // Build views for all of the sub records
        List<ParsedNdefRecord> records = NdefMessageParser.parse(msgs[0]);
        final int size = records.size();
        for (int i = 0; i < size; i++) {
            ParsedNdefRecord record = records.get(i);
            content.addView(record.getView(this, inflater, content, i));
            inflater.inflate(R.layout.tag_divider, content, true);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }


    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp);
        }
        return sb.toString();
}}
