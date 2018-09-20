package com.proj.gaurav.imark;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gaurav on 2017-10-06.
 */

public class Course implements Serializable {

    private String name;
    private ArrayList<String> entry_name;
    private ArrayList<Double> marks;
    private ArrayList<Double> weights;
    private boolean credit;

    public Course(String course_name){
        name = course_name;
        entry_name = new ArrayList<String>();
        marks = new ArrayList<Double>();
        weights = new ArrayList<Double>();
        credit = true;
    }

    public void addMark(String e_name, double mark, double weight){
        entry_name.add(e_name);
        marks.add(mark);
        weights.add(weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getEntry_name() {
        return entry_name;
    }

    public void setEntry_name(ArrayList<String> entry_name) {
        this.entry_name = entry_name;
    }

    public ArrayList<Double> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Double> marks) {
        this.marks = marks;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public double getMarkPercent(int index){
        return (marks.get(index)*weights.get(index));
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }

    public double getPercentage(){
        double sum_marks = 0;
        double weight = 0;
        int num_marks = 0;
        for(int x = 0; x <marks.size(); x++){
            sum_marks = sum_marks + marks.get(x)* (weights.get(x));
            weight = weight + weights.get(x);
            num_marks = num_marks+1;
        }
        if(num_marks>0){
            double x = (sum_marks/weight);
            return (Math.round(x * 100.0) / 100.0);
        }
        else{
            return 0;
        }
    }

    public double getSumWeight(){
        double sum = 0;

        for(int i=0; i<weights.size(); i++){
            sum=sum+weights.get(i);
        }

        return sum;
    }
    public double getGPA(){
        double percent = this.getPercentage();
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
}
