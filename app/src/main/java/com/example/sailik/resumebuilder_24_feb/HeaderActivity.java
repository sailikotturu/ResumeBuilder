package com.example.sailik.resumebuilder_24_feb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HeaderActivity extends Activity implements View.OnClickListener {

    private Button mOkButton;
    private EditText mExpEdit;
    private DbHelper dbObj;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        Log.d("headeractivity","oncreate");

        mOkButton = (Button) findViewById(R.id.ok_button);
        mExpEdit = (EditText) findViewById(R.id.exp_editText);

        mExpEdit.setFocusable(true);

        Intent i = getIntent();
        email = i.getExtras().getString("Email");
//        String imageURL = i.getExtras().getString("image");

        mOkButton.setOnClickListener(this);
        dbObj = new DbHelper(this);

        mExpEdit.setText(dbObj.getHeaderRow(email).getPersonExperience());




    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ok_button:
                String text = mExpEdit.getText().toString();
                dbObj.updateExperience(email,text);
                Log.d("headeractivity",""+dbObj.getHeaderRow(email).getPersonExperience());
                this.finish();
                break;

        }
    }
}
