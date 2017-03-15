package com.example.sailik.resumebuilder_24_feb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EducationDialog extends Activity implements View.OnClickListener {
    private EditText mCourse;
    private EditText mCollege;
    private EditText mDuration;
    private Button mOkBtn;
    private Button mCancelBtn;
    private DbHelper dbObj;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_dialog);

        mCourse = (EditText) findViewById(R.id.coursename_desc_editText);
        mCollege = (EditText) findViewById(R.id.collegename_desc_editText);
        mDuration = (EditText) findViewById(R.id.duration_desc_editText);
        mOkBtn = (Button) findViewById(R.id.ok_button);
        mCancelBtn = (Button) findViewById(R.id.cancel_button);

        dbObj = new DbHelper(this);
        mOkBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
        Intent getI = getIntent();
        email=getI.getStringExtra("email");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_button:
                String course = mCourse.getText().toString();
                String college = mCollege.getText().toString();
                String duration = mDuration.getText().toString();
                Education eObj = new Education(college,duration,course);
                dbObj.createEducationRow(eObj,email);
                this.finish();
                break;
            case R.id.cancel_button:
                this.finish();
                break;

        }
    }
}
