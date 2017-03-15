package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EducationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mCourse;
    private EditText mCollege;
    private EditText mDuration;
    private Button mOkBtn;
    String email;
    private DbHelper dbObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        mCourse = (EditText)findViewById(R.id.coursename_desc_editText);
        mCollege = (EditText)findViewById(R.id.collegename_desc_editText);
        mDuration = (EditText)findViewById(R.id.duration_desc_editText);
        mOkBtn = (Button)findViewById(R.id.education_ok_button);
        dbObj=new DbHelper(this);

        Intent getI = getIntent();
        email = getI.getExtras().getString("email");

        mCourse.setText(dbObj.getRecentEducation(email).getCourseOfStudy());
        mCollege.setText(dbObj.getRecentEducation(email).getCollegeName());
        mDuration.setText(dbObj.getRecentEducation(email).getStudyDuration());
        mOkBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.education_ok_button:
                String text1 = mCourse.getText().toString();
                String text2 = mCollege.getText().toString();
                String text3 = mDuration.getText().toString();
                Education eObj = new Education(text2,text3,text1);
                dbObj.updateEducation(eObj,email);
//                Intent intent = new Intent(this,ProfileActivity.class);
//                startActivity(intent);
                this.finish();
                break;
        }
    }
}
