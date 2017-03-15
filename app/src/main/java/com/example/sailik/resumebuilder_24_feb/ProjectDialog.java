package com.example.sailik.resumebuilder_24_feb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProjectDialog extends Activity implements View.OnClickListener {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_project_dialog);
//    }

    Button ok_btn, cancel_btn;
    private EditText mPname;
    private EditText mPdesc;
    private EditText mPduration;
    private DbHelper dbObj;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_project_dialog);

        ok_btn = (Button) findViewById(R.id.ok_btn_id);
        cancel_btn = (Button) findViewById(R.id.cancel_btn_id);
        mPname = (EditText)findViewById(R.id.projectname_desc_editText);
        mPdesc = (EditText)findViewById(R.id.projectdesc_editText);
        mPduration = (EditText)findViewById(R.id.projectduration_editText);
        dbObj = new DbHelper(this);

        ok_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        Intent getI = getIntent();
        email=getI.getStringExtra("email");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ok_btn_id:
                String pname = mPname.getText().toString();
                String pdesc = mPdesc.getText().toString();
                String pdur = mPduration.getText().toString();
                Projects pObj = new Projects(pname,pdur,pdesc);
                dbObj.createProjectRow(pObj,email);
//                Intent i = new Intent(this,ProfileActivity.class);
//                startActivity(i);
//                showToastMessage();
                this.finish();

                break;

            case R.id.cancel_btn_id:

//                showToastMessage("Cancel Button Clicked");
                this.finish();
                break;
        }

    }

    void showToastMessage() {
        Toast.makeText(getApplicationContext(),"project added", Toast.LENGTH_SHORT)
                .show();
    }
}
