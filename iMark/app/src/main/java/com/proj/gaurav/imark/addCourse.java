package com.proj.gaurav.imark;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class addCourse extends Fragment implements View.OnClickListener{


    public addCourse() {
        // Required empty public constructor
    }

    Button save;
    Button back;
    TextView courseName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_course, container, false);

        save = (Button) rootView.findViewById(R.id.addCourseSave);
        back = (Button) rootView.findViewById(R.id.back1);
        courseName = (TextView) rootView.findViewById(R.id.addCourseName);

        save.setOnClickListener(this);
        back.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addCourseSave){
            if(!courseName.getText().equals("")){
                try {
                    Student student = new Student();
                    student.load(getActivity());
                    student.add_course(new Course(String.valueOf(courseName.getText())));
                    student.save(getActivity());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getFragmentManager();
                Default def = new Default();
                fragmentManager.beginTransaction().replace(R.id.relativeLayout, def, def.getTag()).commit();
            }
        }
        if(v.getId() == R.id.back1){
            FragmentManager fragmentManager = getFragmentManager();
            Default def = new Default();
            fragmentManager.beginTransaction().replace(R.id.relativeLayout, def, def.getTag()).commit();
        }
    }
}
