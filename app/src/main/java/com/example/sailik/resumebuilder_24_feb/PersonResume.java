package com.example.sailik.resumebuilder_24_feb;

import java.util.ArrayList;

/**
 * Created by saili.k on 27-02-2017.
 */

public class PersonResume {
    private ResumeHeader headerObj;
    private AboutMe aboutObj;
    private ArrayList<Projects> projectsList = new ArrayList<Projects>();
    private ArrayList<Education> educationList = new ArrayList<Education>();
    private ArrayList<Interests> interestsList = new ArrayList<Interests>();

    public PersonResume(){

    }
    public PersonResume(ResumeHeader headerObj,AboutMe aboutObj,Projects projectObj,Education educationObj,Interests interestsObj){
        this.headerObj = headerObj;
        this.aboutObj = aboutObj;
        this.projectsList.add(projectObj);
        this.educationList.add(educationObj);
        this.interestsList.add(interestsObj);
    }

    public ResumeHeader getHeaderObj() {
        return headerObj;
    }

    public AboutMe getAboutObj() {
        return aboutObj;
    }

    public ArrayList<Projects> getProjectsList() {
        return projectsList;
    }

    public ArrayList<Education> getEducationList() {
        return educationList;
    }

    public ArrayList<Interests> getInterestsList() {
        return interestsList;
    }

    public void setHeaderObj(ResumeHeader headerObj) {
        this.headerObj = headerObj;
    }

    public void setAboutObj(AboutMe aboutObj) {
        this.aboutObj = aboutObj;
    }

    public void setProjectsList(ArrayList<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    public void setEducationList(ArrayList<Education> educationList) {
        this.educationList = educationList;
    }

    public void setInterestsList(ArrayList<Interests> interestsList) {
        this.interestsList = interestsList;
    }
    //    public PersonResume(String name,String email,String yearsOfExp,String aboutMe,ArrayList<Projects> p,ArrayList<Education> e){
//        projects = new ArrayList<Projects>();
//        education = new ArrayList<Education>();
//        this.projects = p;
//        this.education = e;
//        this.personName = name;
//        this.email = email;
//        this.yearsOfExp = yearsOfExp;
//        this.aboutMe = aboutMe;
//    }
//
//    public PersonResume(String name,String email,String imageURL){
//        //headers = new ArrayList<ResumeHeader>();
//        if(headers.size()==0) {
//            ResumeHeader obj = new ResumeHeader(name, email, imageURL);
//            headers.add(obj);
//        }
//        else{
//            for(int i=0;i<headers.size();i++){
//
//            }
//        }
//
//    }

//    public void setPersonName(String personName) {
//        this.personName = personName;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setYearsOfExp(String yearsOfExp) {
//        this.yearsOfExp = yearsOfExp;
//    }
//
//    public void setAboutMe(String aboutMe) {
//        this.aboutMe = aboutMe;
//    }
//
//    public void setProjects(ArrayList<Projects> projects) {
//        this.projects = projects;
//    }
//
//    public void setEducation(ArrayList<Education> education) {
//        this.education = education;
//    }
//
//    public ArrayList<Education> getEducation() {
//        return education;
//    }
//
//    public ArrayList<Projects> getProjects() {
//        return projects;
//    }
//
//    public String getAboutMe() {
//        return aboutMe;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getYearsOfExp() {
//        return yearsOfExp;
//    }
//
//    public String getPersonName() {
//        return personName;
//    }
}
