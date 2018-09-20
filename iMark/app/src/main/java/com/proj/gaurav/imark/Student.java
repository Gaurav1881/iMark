package com.proj.gaurav.imark;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Gaurav on 2017-10-06.
 */

public class Student {

    private String name;
    private ArrayList<Course> courses;

    public Student(String i_name){
         name = i_name;
         courses = new ArrayList<Course>();
    }
    public Student(){
        name = "None";
    }

    public void add_course(Course c){
        courses.add(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void save(Context context) throws FileNotFoundException, IOException {

        //New FileOutputStream is created, and a new file called student will hold all the saved information.
        FileOutputStream fos = new FileOutputStream(new File(context.getFilesDir(), "student.txt"));

        //New objectOutputStream is created which will help us write all the instance variables.
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
        objectOutputStream.writeObject(this.name);
        objectOutputStream.writeObject(this.courses);
        objectOutputStream.close();
    }
    public void load(Context context) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File(context.getFilesDir(), "student.txt")));

        //Load in the same order as one saved this file.
        this.name = (String) in.readObject();
        this.courses = (ArrayList<Course>) in.readObject();
        in.close();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }

    public Double calc_percent(){
        double marks = 0;
        int num_marks = 0;
        for(int x = 0; x<courses.size(); x++){
            if(courses.get(x).isCredit()){
                marks = marks + courses.get(x).getPercentage();
                num_marks = num_marks + 1;
            }
        }
        if(num_marks>0){
            double ret_mark =  (marks/num_marks);
            return (Math.round(ret_mark * 100.0) / 100.0);
        }
        else{
            return 0.0;
        }

    }

    public Double get_CGpa() {
        double gpas = 0;
        int num_marks = 0;
        for(int x = 0; x<courses.size(); x++){
            if(courses.get(x).isCredit()){
                gpas = gpas + courses.get(x).getGPA();
                num_marks = num_marks + 1;
            }
        }
        if(num_marks>0){
            double ret_mark =  (gpas/num_marks);
            return (Math.round(ret_mark * 100.0) / 100.0);
        }
        else{
            return 0.0;
        }
    }
    public Double get_Gpa(){
        double percent = this.calc_percent();
        double gpv;
        if (percent >= 85) {
            gpv = 4.0;
        }
        else if (percent >= 80) {
            gpv = 3.7;
        }
        else if (percent >= 77) {
            gpv = 3.3;
        }
        else if (percent >= 73) {
            gpv = 3.0;
        }
        else if (percent >= 70) {
            gpv = 2.7;
        }
        else if (percent >= 67) {
            gpv = 2.3;
        }
        else if (percent >= 63) {
            gpv = 2.0;
        }
        else if (percent >= 60) {
            gpv = 1.7;
        }
        else if (percent >= 57) {
            gpv = 1.3;
        }
        else if (percent >= 53) {
            gpv = 1.0;
        }
        else if (percent >= 50) {
            gpv = 0.7;
        }
        else {
            gpv = 0.0;
        }
        return gpv;
    }

    public double projector(double mark, double weight, double want){
        double newMark = mark*(weight/100);
        double newerMark = ((want-newMark)/((100-weight)/100));
        return (Math.round(newerMark * 100.0) / 100.0);
    }
}
