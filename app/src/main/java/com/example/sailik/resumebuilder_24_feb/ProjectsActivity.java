package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProjectsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mPname;
    private EditText mPnameEdit;
    private TextView mPdesc;
    private EditText mPdescEdit;
    private TextView mPduration;
    private EditText mPdurationET;
    private Button mOkBtn;
    private DbHelper dbObj;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        mPname = (TextView)findViewById(R.id.projectname_desc_tv);
        mPnameEdit = (EditText)findViewById(R.id.projectname_desc_editText);
        mPdesc = (TextView)findViewById(R.id.projectdesc_tv);
        mPdescEdit = (EditText)findViewById(R.id.projectdesc_editText);
        mPduration = (TextView)findViewById(R.id.projectduration_tv);
        mPdurationET = (EditText)findViewById(R.id.projectduration_editText);
        mOkBtn = (Button)findViewById(R.id.project_ok_button);
        dbObj=new DbHelper(this);
        Intent getI = getIntent();
        email = getI.getExtras().getString("email");

        mPnameEdit.setText(dbObj.getProjectRow(email).getProjectName());
        mPdescEdit.setText(dbObj.getProjectRow(email).getDescription());
        mPdurationET.setText(dbObj.getProjectRow(email).getProjectDuration());
        mOkBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.project_ok_button:
                String pName = mPnameEdit.getText().toString();
                String pDesc = mPdescEdit.getText().toString();
                String pduration = mPdurationET.getText().toString();
                dbObj.updateProjects(email,pName,pDesc,pduration);
                Intent i = new Intent(this,ProfileActivity.class);
                startActivity(i);
                break;
        }
    }
}
