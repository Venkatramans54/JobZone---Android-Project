package com.example.venkat.jobzone;

/**
 * Created by Venkat on 01-01-2018.
 */

public class NewJob {

    String jobid;
    String name;
    String degree;
    String dob;
    String exp;
    String no;
    String email;
    public NewJob(){

    }
    public NewJob(String jobid,String name,String degree,String dob,String exp,String no,String email){

        this.jobid=jobid;
        this.name = name;
        this.degree = degree;
        this.dob = dob;
        this.exp = exp;
        this.no = no;
        this.email = email;
    }

    public String getJobid() {
        return jobid;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    public String getDob() {
        return dob;
    }

    public String getExp() {
        return exp;
    }

    public String getNo() {
        return no;
    }

    public String getEmail() {
        return email;
    }
}
