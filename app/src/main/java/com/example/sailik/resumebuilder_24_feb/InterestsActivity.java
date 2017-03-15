package com.example.sailik.resumebuilder_24_feb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InterestsActivity extends Activity implements View.OnClickListener {

    private EditText mInterest1;
    private EditText mInterest2;
    private EditText mInterest3;
    private Button mOkBtn;
    private Button mCancelBtn;
    private DbHelper dbObj;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        mInterest1 = (EditText)findViewById(R.id.interest1_editText);
        mInterest2 = (EditText)findViewById(R.id.interest2_editText);
        mInterest3 = (EditText)findViewById(R.id.interest3_editText);
        mOkBtn = (Button)findViewById(R.id.interests_ok_button);
        mCancelBtn = (Button) findViewById(R.id.interest_d_cancelbtn);

        dbObj = new DbHelper(this);
        Intent getI = getIntent();
        email = getI.getExtras().getString("email");
        mInterest1.setText(dbObj.getInterestsRow(email).getInterestOne());
        mInterest2.setText(dbObj.getInterestsRow(email).getInterestTwo());
        mInterest3.setText(dbObj.getInterestsRow(email).getInterestThree());

        mOkBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.interests_ok_button:
                String text1 = mInterest1.getText().toString();
                String text2 = mInterest2.getText().toString();
                String text3 = mInterest3.getText().toString();
                dbObj.updateInterests(text1,text2,text3,email);
                this.finish();
//                Intent i = new Intent(this,ProfileActivity.class);
//                startActivity(i);
                break;
            case R.id.interest_d_cancelbtn:
                this.finish();
                break;
        }

    }
}
