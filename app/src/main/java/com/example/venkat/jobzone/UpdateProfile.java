package com.example.venkat.jobzone;

/**
 * Created by Venkat on 31-12-2017.
 */

public class UpdateProfile {

    String jobid;
    String name;
    String degree;
    String dob;
    String exp;
    String no;
    String email;
    String address;

    public UpdateProfile(){

    }
    public UpdateProfile(String jobid,String name,String degree,String dob,String exp,String no,String email,String address){

        this.jobid=jobid;
        this.name = name;
        this.degree = degree;
        this.dob = dob;
        this.exp = exp;
        this.no = no;
        this.email = email;
        this.address=address;
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

    public String getAddress() {
        return address;
    }
}
