package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EditPhoto extends AppCompatActivity implements View.OnClickListener {
    private ImageView mProfilePic;
    static final int RESULT_LOAD_IMG = 2;
    private Button mOkBtn;
    DbHelper dbObj;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_photo);

        mProfilePic = (ImageView)findViewById(R.id.profilepic);
        mOkBtn = (Button)findViewById(R.id.btn_ok);
        mOkBtn.setOnClickListener(this);
        dbObj = new DbHelper(this);

        Intent intent = getIntent();
        email= intent.getExtras().getString("email");

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(galleryIntent,RESULT_LOAD_IMG);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                && null != data){
           Log.d("onactivityrsult","called");
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            mProfilePic.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            String path = selectedImage+"";
            Log.d("editphoto",""+path);
            dbObj.updatePhoto(email,path);

        }
        else if (requestCode == RESULT_LOAD_IMG && resultCode != RESULT_OK
                && null != data){
//            Toast.makeText(this, getResources().getString(R.string.cancel_toast),
//                    Toast.LENGTH_LONG).show();
        }
        else{

        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_ok:
                this.finish();
                break;
        }
    }
}
