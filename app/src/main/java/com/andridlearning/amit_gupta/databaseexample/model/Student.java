package com.andridlearning.amit_gupta.databaseexample.model;

/**
 * Created by Amit_Gupta on 8/20/15.
 */
public class Student {

    private int id ;
    private String name;
    private String subject;

    public Student(){
    }

    public Student(String name, String subject){

        this.name = name;
        this.subject = subject;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getSubject(){
        return subject;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", subject=" + subject
                + "]";
    }
}
