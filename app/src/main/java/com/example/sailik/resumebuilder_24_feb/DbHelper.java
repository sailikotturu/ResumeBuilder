package com.example.sailik.resumebuilder_24_feb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saili.k on 04-03-2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG = "DbHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ResumesManager";

    // Table Names
    private static final String TABLE_HEADER = "headers";
    private static final String TABLE_ABOUTME = "AboutMe";
    public static final String TABLE_PROJECTS = "Projects";
    private static final String TABLE_EDUCATION = "Education";
    private static final String TABLE_INTERESTS = "Interests";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_EXPERIENCE = "experience";
    private static final String KEY_PHOTOURL = "photo_url";


    private static final String KEY_ABOUT_EMAIL = "email";
    private static final String KEY_ABOUTDESC = "Description";


    public static final String KEY_PROJECT_EMAIL = "email";
    public static final String KEY_PROJECT_NAME = "projectname";
    public static final String KEY_PROJECT_DESC = "projectdesc";
    public static final String KEY_PROJECT_DURATION = "projectduration";

    public static final String KEY_EDUCATION_EMAIL = "email";
    public static final String KEY_EDUCATION_COURSE = "course";
    public static final String KEY_EDUCATION_COLLEGE = "college";
    public static final String KEY_EDUCATION_DURATION = "duration";


    private static final String KEY_INTERESTS_EMAIL = "email";
    private static final String KEY_INTEREST_ONE = "interest1";
    private static final String KEY_INTEREST_TWO = "interest2";
    private static final String KEY_INTEREST_THREE = "interest3";
    private ResumeHeader hObj;




    private static final String sql5 = String.format("create table %s (%s INTEGER PRIMARY KEY , %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
            TABLE_HEADER, KEY_ID, KEY_EMAIL, KEY_NAME, KEY_EXPERIENCE,KEY_PHOTOURL);

    private static final String CREATE_TABLE_ABOUTME = "CREATE TABLE " + TABLE_ABOUTME
            + "("+ KEY_ID+ " INTEGER PRIMARY KEY ," + KEY_ABOUT_EMAIL + " TEXT," + KEY_ABOUTDESC + " TEXT" + ")";



    private static final String CREATE_TABLE_PROJECTS = "CREATE TABLE "
            + TABLE_PROJECTS + "("+ KEY_ID+ " INTEGER PRIMARY KEY ," + KEY_PROJECT_EMAIL + " TEXT,"
            + KEY_PROJECT_NAME + " TEXT," + KEY_PROJECT_DESC + " TEXT,"
            + KEY_PROJECT_DURATION + " TEXT" + ")";

    private static final String CREATE_TABLE_EDUCATION = "CREATE TABLE "
            + TABLE_EDUCATION + "("+ KEY_ID+ " INTEGER PRIMARY KEY ," + KEY_EDUCATION_EMAIL + " TEXT,"
            + KEY_EDUCATION_COURSE + " TEXT," + KEY_EDUCATION_COLLEGE + " TEXT,"
            + KEY_EDUCATION_DURATION + " TEXT" + ")";

    private static final String CREATE_TABLE_INTERESTS = "CREATE TABLE "
            + TABLE_INTERESTS + "("+ KEY_ID+ " INTEGER PRIMARY KEY," + KEY_INTERESTS_EMAIL + " TEXT,"
            + KEY_INTEREST_ONE + " TEXT," + KEY_INTEREST_TWO + " TEXT," + KEY_INTEREST_THREE + " TEXT" + ")";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(sql5);
        db.execSQL(CREATE_TABLE_ABOUTME);
        db.execSQL(CREATE_TABLE_PROJECTS);
        db.execSQL(CREATE_TABLE_EDUCATION);
        db.execSQL(CREATE_TABLE_INTERESTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEADER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABOUTME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EDUCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INTERESTS);
        onCreate(db);

    }

    public int getProjectCount(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String emailText = '"'+email+'"';
        String selection = KEY_PROJECT_EMAIL+" = ?";
        String[] selectionArgs = {emailText};
        int i=0;

        Cursor cursor=db.query(TABLE_PROJECTS,null,selection,selectionArgs,null,null,null);
        if(cursor.moveToFirst()){
            while(!cursor.moveToLast()){
                cursor.moveToNext();
                i++;
            }
            i++;
        }
        else
            return i;
        return i;
    }
    public int getEducationCount(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String emailText = '"'+email+'"';
        String selection = KEY_EDUCATION_EMAIL+" = ?";
        String[] selectionArgs = {emailText};
        int i=0;

        Cursor cursor=db.query(TABLE_EDUCATION,null,selection,selectionArgs,null,null,null);
        if(cursor.moveToFirst()){
            while(!cursor.moveToLast()){
                cursor.moveToNext();
                i++;
            }
            i++;
        }
        else
            return i;
        return i;
    }

    public Projects getRecentProject(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String emailText = '"'+email+'"';
        String selection = KEY_PROJECT_EMAIL+" = ?";
        String[] selectionArgs = {emailText};
        int i=0;

        Cursor cursor=db.query(TABLE_PROJECTS,null,selection,selectionArgs,null,null,null);
        cursor.moveToLast();
        Projects pObj = new Projects();
        pObj.setProjectName(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
        pObj.setDescription(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_DESC)));
        pObj.setProjectDuration(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_DURATION)));
        return pObj;
    }

    public Education getRecentEducation(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String emailText = '"'+email+'"';
        String selection = KEY_EDUCATION_EMAIL+" = ?";
        String[] selectionArgs = {emailText};
        int i=0;

        Cursor cursor=db.query(TABLE_EDUCATION,null,selection,selectionArgs,null,null,null);
        cursor.moveToLast();
        Education eObj = new Education();
        eObj.setCourseOfStudy(cursor.getString(cursor.getColumnIndex(KEY_EDUCATION_COURSE)));
        eObj.setCollegeName(cursor.getString(cursor.getColumnIndex(KEY_EDUCATION_COLLEGE)));
        eObj.setStudyDuration(cursor.getString(cursor.getColumnIndex(KEY_EDUCATION_DURATION)));
        return eObj;
    }

    /*
 * Creating a header row
 */
    public void createHeaderRow(ResumeHeader headerObj) {
        SQLiteDatabase db = this.getReadableDatabase();
        String email = '"'+headerObj.getPersonEmail()+ '"';
        String selectQuery = "SELECT  * FROM " + TABLE_HEADER + " WHERE "
                + KEY_EMAIL + " = " + email;

        String selection = KEY_EMAIL+" = ?";
        String[] selectionArgs = {email};


        Cursor cursor=db.query(TABLE_HEADER,null,selection,selectionArgs,null,null,null);

        if(!cursor.moveToFirst()){
            ContentValues values = new ContentValues();
            values.put(KEY_EMAIL, headerObj.getPersonEmail());
            values.put(KEY_NAME, headerObj.getPersonName());
            values.put(KEY_EXPERIENCE, headerObj.getPersonExperience());
            values.put(KEY_PHOTOURL, headerObj.getImageURL());

            db.insert(TABLE_HEADER, null, values);
            //db.close();
        }
        else{
                updatePhoto(headerObj.getPersonEmail(),headerObj.getImageURL());
            }



    }
    public void createAboutRow(AboutMe aboutObj,ResumeHeader headerObj) {


        SQLiteDatabase db = this.getReadableDatabase();
        String email = '"' + headerObj.getPersonEmail() + '"';
        String selectQuery = "SELECT  * FROM " + TABLE_ABOUTME + " WHERE "
                + KEY_ABOUT_EMAIL + " = " + email;

        String selection = KEY_ABOUT_EMAIL + " = ?";
        String[] selectionArgs = {email};



        Cursor cursor = db.query(TABLE_ABOUTME, null, selection, selectionArgs, null, null, null);

        if (!cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(KEY_ABOUT_EMAIL, headerObj.getPersonEmail());
            values.put(KEY_ABOUTDESC, aboutObj.getAboutmeText());

            db.insert(TABLE_ABOUTME, null, values);
            //db.close();
        }
    }

    public void createProjectRow(Projects projectObj,String emailText) {

        SQLiteDatabase db = this.getWritableDatabase();
        String email = '"' + emailText + '"';


        Log.d("dphelper project",""+email);
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECTS + " WHERE "
                + KEY_PROJECT_EMAIL + " = " + email;

        String selection = KEY_PROJECT_EMAIL + " = ?";
        String[] selectionArgs = {email};



        Cursor cursor = db.query(TABLE_PROJECTS, null, selection, selectionArgs, null, null, null);


            Log.d("dphelper project","if loop");
            ContentValues values1 = new ContentValues();
            values1.put(KEY_PROJECT_EMAIL, email);
            values1.put(KEY_PROJECT_NAME, projectObj.getProjectName());
            values1.put(KEY_PROJECT_DESC, projectObj.getDescription());
            values1.put(KEY_PROJECT_DURATION, projectObj.getProjectDuration());


            db.insert(TABLE_PROJECTS, null, values1);

    }

    public void createEducationRow(Education educationObj,String emailText) {

        SQLiteDatabase db = this.getReadableDatabase();
        String email = '"' + emailText + '"';
        String selectQuery = "SELECT  * FROM " + TABLE_EDUCATION + " WHERE "
                + KEY_EDUCATION_EMAIL + " = " + email;

        String selection = KEY_EDUCATION_EMAIL + " = ?";
        String[] selectionArgs = {email};



        //Cursor cursor = db.query(TABLE_EDUCATION, null, selection, selectionArgs, null, null, null);


            ContentValues values = new ContentValues();
            values.put(KEY_EDUCATION_EMAIL, email);
            values.put(KEY_EDUCATION_COURSE, educationObj.getCourseOfStudy());
            values.put(KEY_EDUCATION_COLLEGE, educationObj.getCollegeName());
            values.put(KEY_EDUCATION_DURATION, educationObj.getStudyDuration());

            db.insert(TABLE_EDUCATION, null, values);
            db.close();




    }

    public void createInterestsRow(Interests iObj,ResumeHeader headerObj) {

        SQLiteDatabase db = this.getReadableDatabase();
        String email = '"' + headerObj.getPersonEmail() + '"';
        String selectQuery = "SELECT  * FROM " + TABLE_INTERESTS + " WHERE "
                + KEY_INTERESTS_EMAIL + " = " + email;

        String selection = KEY_INTERESTS_EMAIL + " = ?";
        String[] selectionArgs = {email};



        Cursor cursor = db.query(TABLE_INTERESTS, null, selection, selectionArgs, null, null, null);

        if (!cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(KEY_INTERESTS_EMAIL, headerObj.getPersonEmail());
            values.put(KEY_INTEREST_ONE, iObj.getInterestOne());
            values.put(KEY_INTEREST_TWO, iObj.getInterestTwo());
            values.put(KEY_INTEREST_THREE, iObj.getInterestThree());
            Log.d("create interests",""+values.getAsString(KEY_INTEREST_ONE));
            db.insert(TABLE_INTERESTS, null, values);
            db.close();
        }

    }

    /*
 * get single todo
 */
    public ResumeHeader getHeaderRow(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_EMAIL+" = ?";
        String[] selectionArgs = {email};






        ResumeHeader headerObj = new ResumeHeader();

            Cursor cursor=db.query(TABLE_HEADER,null,selection,selectionArgs,null,null,null);

                if(cursor.moveToFirst()) {
                    headerObj.setPersonName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    //headerObj.setPersonName(cursor.moveToFirst().getString(cursor.getColumnIndex(KEY_NAME));
                    headerObj.setPersonEmail((cursor.getString(cursor.getColumnIndex(KEY_EMAIL))));
                    headerObj.setPersonExperience(cursor.getString(cursor.getColumnIndex(KEY_EXPERIENCE)));
                    headerObj.setImageURL(cursor.getString(cursor.getColumnIndex(KEY_PHOTOURL)));
                }


        return headerObj;
    }

    public AboutMe getAboutRow(String email) {


        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_ABOUT_EMAIL+" = ?";
        String[] selectionArgs = {email};


        AboutMe aboutObj = new AboutMe();

        Cursor cursor=db.query(TABLE_ABOUTME,null,selection,selectionArgs,null,null,null);

        if(cursor.moveToFirst()) {
            aboutObj.setAboutmeText(cursor.getString(cursor.getColumnIndex(KEY_ABOUTDESC)));

        }


        return aboutObj;

    }

    public Projects getProjectRow(String email) {


        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_PROJECT_EMAIL+" = ?";

        String[] selectionArgs = {email};


        Projects pObj = new Projects();

        Cursor cursor=db.query(TABLE_PROJECTS,null,selection,selectionArgs,null,null,null);

        if(cursor.moveToFirst()) {
            Log.d("get project","if loop");
            pObj.setProjectName(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
            Log.d("get project if loop",""+pObj.getProjectName());
            pObj.setDescription(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_DESC)));
            pObj.setProjectDuration(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_DURATION)));

        }
        else{
            Log.d("get project","else loop");
        }
        Log.d("get project",""+pObj.getProjectName());
        return pObj;

    }

    public Education getEducationRow(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_EDUCATION_EMAIL+" = ?";
        String[] selectionArgs = {email};


        Education eObj = new Education();

        Cursor cursor=db.query(TABLE_EDUCATION,null,selection,selectionArgs,null,null,null);

        if(cursor.moveToFirst()) {
            eObj.setCourseOfStudy(cursor.getString(cursor.getColumnIndex(KEY_EDUCATION_COURSE)));
            eObj.setCollegeName(cursor.getString(cursor.getColumnIndex(KEY_EDUCATION_COLLEGE)));
            eObj.setStudyDuration(cursor.getString(cursor.getColumnIndex(KEY_EDUCATION_DURATION)));

        }

        return eObj;

    }

    public Interests getInterestsRow(String email) {



        SQLiteDatabase db = this.getReadableDatabase();
        String selection = KEY_INTERESTS_EMAIL+" = ?";
        String[] selectionArgs = {email};


        Interests iObj = new Interests();

        Cursor cursor=db.query(TABLE_INTERESTS,null,selection,selectionArgs,null,null,null);

        if(cursor.moveToFirst()) {
            Log.d("get interests if loop",""+cursor.getString(cursor.getColumnIndex(KEY_INTEREST_ONE)));
            iObj.setInterestOne(cursor.getString(cursor.getColumnIndex(KEY_INTEREST_ONE)));
            iObj.setInterestTwo(cursor.getString(cursor.getColumnIndex(KEY_INTEREST_TWO)));
            iObj.setInterestThree(cursor.getString(cursor.getColumnIndex(KEY_INTEREST_THREE)));

        }

        return iObj;

    }


    public Cursor getAllProjects(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String emailText = '"'+email+'"';
        String selection = KEY_PROJECT_EMAIL+" = ?";
        String[] selectionArgs = {emailText};

        Cursor cursor=db.query(TABLE_PROJECTS,null,selection,selectionArgs,null,null,null);
        return cursor;


    }

    public Cursor getAllEducation(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String emailText = '"'+email+'"';
        String selection = KEY_EDUCATION_EMAIL+" = ?";
        String[] selectionArgs = {emailText};

        Cursor cursor=db.query(TABLE_EDUCATION,null,selection,selectionArgs,null,null,null);
        return cursor;


    }



    public List<Interests> getAllInterests(String email) {
        List<Interests> interests = new ArrayList<Interests>();

        return interests;
    }

    /*
 * Updating a todo
 */
    public void updateExperience(String email,String text) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXPERIENCE, text);

        db.update(TABLE_HEADER, values, KEY_EMAIL + " = ?",
                new String[] { email });

    }
    public void updatePhoto(String email,String text) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Log.d("updatePhoto",""+text);
        values.put(KEY_PHOTOURL, text);

        db.update(TABLE_HEADER, values, KEY_EMAIL + " = ?",
                new String[] { email });

    }

    public void updateAboutMe(String email,String text) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ABOUTDESC, text);

        db.update(TABLE_ABOUTME, values, KEY_ABOUT_EMAIL + " = ?",
                new String[] {email});

    }

    public void updateProjects(String email,String pName,String pDesc,String pDuration) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROJECT_NAME,pName);
        values.put(KEY_PROJECT_DESC,pDesc);
        values.put(KEY_PROJECT_DURATION,pDuration);

        db.update(TABLE_PROJECTS, values, KEY_PROJECT_EMAIL + " = ?",
                new String[] {email});

    }
    public void updateEducation(Education obj,String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EDUCATION_COURSE, obj.getCourseOfStudy());
        values.put(KEY_EDUCATION_COLLEGE, obj.getCollegeName());
        values.put(KEY_EDUCATION_DURATION, obj.getStudyDuration());

        db.update(TABLE_EDUCATION, values, KEY_EDUCATION_EMAIL + " = ?",
                new String[] {email});

    }

    public void updateInterests(String text1,String text2,String text3,String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INTEREST_ONE,text1);
        values.put(KEY_INTEREST_TWO,text2);
        values.put(KEY_INTEREST_THREE,text3);
        Log.d("update interests",""+values.getAsString(KEY_INTEREST_ONE));
        db.update(TABLE_INTERESTS, values, KEY_INTERESTS_EMAIL + " = ?",
                new String[] {email});

    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }




}
