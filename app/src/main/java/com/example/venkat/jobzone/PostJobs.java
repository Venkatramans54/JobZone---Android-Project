package com.example.venkat.jobzone;

/**
 * Created by Venkat on 31-12-2017.
 */

public class PostJobs {

    String jobid;
    String name;
    String post;
    String desc;
    String pow;

    public PostJobs(){

    }
    public PostJobs(String jobid, String name, String post, String pow, String desc) {
        this.jobid = jobid;
        this.name = name;
        this.post = post;
        this.pow = pow;
        this.desc = desc;

    }

    public String getJobid() {
        return jobid;
    }

    public String getName() {
        return name;
    }

    public String getPost() {
        return post;
    }

    public String getPow() {
        return pow;
    }

    public String getDesc() {
        return desc;
    }


}
