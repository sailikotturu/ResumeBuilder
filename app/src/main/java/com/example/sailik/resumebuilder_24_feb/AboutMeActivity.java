package com.example.sailik.resumebuilder_24_feb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AboutMeActivity extends Activity implements View.OnClickListener {
    private EditText mTextEdit;
    private TextView mTextView;
    private Button mOkBtn;
    DbHelper dbObj;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        mTextEdit = (EditText)findViewById(R.id.aboutme_editText);
        mTextView = (TextView)findViewById(R.id.aboutme_desc_tv);
        mOkBtn = (Button)findViewById(R.id.aboutme_ok_button);

        mOkBtn.setOnClickListener(this);
        dbObj = new DbHelper(this);


        Intent i = getIntent();
        email = i.getExtras().getString("email");
        mTextEdit.setText(dbObj.getAboutRow(email).getAboutmeText());

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.aboutme_ok_button:
                String text = mTextEdit.getText().toString();
                dbObj.updateAboutMe(email,text);
                this.finish();
                break;
        }
    }
}
