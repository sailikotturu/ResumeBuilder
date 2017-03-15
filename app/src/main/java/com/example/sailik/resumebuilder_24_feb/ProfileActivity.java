package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import static android.R.color.black;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private ImageView profileImage;
    //private TextView Name;
    private TextView profileName;
    private TextView email;
    private EditText experience;
    private Button signout;
    private Button edit_exp;
    private GoogleApiClient mGoogleApiClient;
    String CUSTOM_ACTION = "SIGN_OUT";
    private Button revoke;
    private GoogleApiClient client;
    private GoogleSignInOptions gso;


    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtEmail;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ResumeList listObj = new ResumeList();
    private PersonResume resumeObj = new PersonResume();
    private ResumeHeader hObj;
    public static int navItemIndex = 0;
    //public String personEmail;
    private SQLiteDatabase mdb;
    private String editEmail;
    private String imageURL;
    private String personName;
    private String personEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ProfileActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profileImage = (ImageView) findViewById(R.id.imageView);
        profileName = (TextView) findViewById(R.id.name_editText);
        email = (TextView) findViewById(R.id.email_editText);
        experience = (EditText) findViewById(R.id.exp_editText);
        //signout = (Button) findViewById(R.id.signout_button);
        edit_exp = (Button) findViewById(R.id.edit_button);
        //revoke =(Button) findViewById(R.id.revoke_button);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navHeader = navigationView.getHeaderView(0);
        imgProfile =(ImageView) navigationView.getHeaderView(0).findViewById(R.id.img_profile);
        txtName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        txtEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email);


        String action = getIntent().getAction();
        Intent details;

        if (action.equals("PERSON_DETAILS")||action.equals("FB_LOGIN")) {
            details = getIntent();
            if(action.equals("FB_LOGIN")){
                //signout.setVisibility(View.INVISIBLE);
            }
            else{}
            personName = details.getStringExtra("Name");
            imageURL = details.getStringExtra("image_url");
            personEmail = details.getStringExtra("email");
            //profileName.setText(personName);
            editEmail = "";
            int i=0;
            while(personEmail.charAt(i)!='@'){
                editEmail = editEmail+personEmail.charAt(i);
                i++;
            }
            hObj = new ResumeHeader(personName,personEmail,imageURL,"");
            DbHelper dbObj = new DbHelper(this);
            mdb = dbObj.getReadableDatabase();
            dbObj.createHeaderRow(hObj);
            //Cursor c = getHeaderRow
            Log.d("profileactivity",""+dbObj.getHeaderRow(editEmail).getPersonName());

        }
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // load nav menu header data
        loadNavHeader(imageURL);

        // initializing navigation menu
        setUpNavigationView();
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Log.d("profileactivity","onnavigationitemselected");

        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {


            case R.id.nav_resume:
                drawer.closeDrawers();
                return true;
            case R.id.nav_about:


                //startActivity(new Intent(ProfileActivity.this, RecyclerActivity.class));
                drawer.closeDrawers();
                return true;

        }

        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
        menuItem.setChecked(true);



        return true;
    }



    private void loadNavHeader(String url) {

        txtName.setText(""+personName);
        txtName.setTextColor(getResources().getColor(black));

        txtEmail.setText(""+personEmail);
        txtEmail.setTextColor(getResources().getColor(black));

        Glide.with(this).load(url).into(imgProfile);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.profile, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.logout) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            finish();

                        }
                    });
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d("second screen", "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onClick(View v) {
        Log.d("profileactivity","onclick");
        switch (v.getId()) {

//            case R.id.signout_button:
//
//                Log.d("profileactivity","signout button");
//
//                break;
//
//            case R.id.revoke_button:
//                Log.d("profileactivity","revoke button");
//
//                break;

        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SecondScreen Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        DifferentRowAdapter adapter = new DifferentRowAdapter(this,DummyData.getData(),personEmail,hObj);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);
    }

    public void onImageClick(){

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





}
