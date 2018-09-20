package com.proj.gaurav.imark;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindProjected extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{


    public FindProjected() {
        // Required empty public constructor
    }

    Button calculate;
    Spinner courses;
    EditText enterMark;
    TextView mark;
    TextView weight;
    TextView newMark;
    Student student;
    int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_find_projected, container, false);

        calculate = (Button) rootView.findViewById(R.id.calcButton);
        courses = (Spinner) rootView.findViewById(R.id.courseSpinner);
        enterMark = (EditText) rootView.findViewById(R.id.markWanted);
        mark = (TextView) rootView.findViewById(R.id.currentMark);
        weight = (TextView) rootView.findViewById(R.id.currentWeight);
        newMark = (TextView) rootView.findViewById(R.id.markNeeded);
        index = 0;
        student = new Student();
        try{

            student.load(getActivity());

            ArrayList<Course> stud_course = student.getCourses();
            String[] courseNames = new String[stud_course.size()];

            for(int i = 0; i<stud_course.size(); i++){
                courseNames[i] = stud_course.get(i).getName();
            }

            ArrayAdapter spin = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, courseNames);

            courses.setAdapter(spin);

            courses.setOnItemSelectedListener(this);


        }catch(Exception e){

        }

        calculate.setOnClickListener(this);

        return rootView;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try{
            mark.setText("Current Mark: " + student.getCourses().get(position).getPercentage());
            weight.setText("Used Weight " + student.getCourses().get(position).getSumWeight());
            index = position;
        }catch (Exception e){

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.calcButton){
            double in_mark = student.getCourses().get(index).getPercentage();
            double in_weight = student.getCourses().get(index).getSumWeight();
            if(!String.valueOf(enterMark.getText()).equals("")){
                double in_wanted = Double.valueOf(String.valueOf(enterMark.getText()));

                if(in_weight<100){
                    newMark.setText("You need " + student.projector(in_mark, in_weight, in_wanted) +
                            "% on the remaining " + (100-in_weight) + "% of the Course.");
                }
                else{
                    newMark.setText("The weight is already at 100 no more space");
                }

            }
        }
    }
}
