package com.proj.gaurav.imark;

//nessessary imports for the class

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Author: Gaurav, Smit and Dhanraj
 *
 * A simple deafult state for events
 *
 */
public class Default extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    /**
     * Empty constructor
     */
    public Default() {
        // Required empty public constructor
    }

    ListView list;
    Button add;
    /**
     *
     *Creates a default interface layout.
     *
     * @param inflater - Instantiates a layout XML file into its according View objects.
     * @param container - Creates allocated space for the view object
     * @param savedInstanceState - saved instance of view.
     * @return  a default interface layout for an com.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Student student = new Student(); // creats a student instance

        try {

            student.load(getActivity()); // gets student activity

        } catch (Exception e) {

            e.printStackTrace();	//catches exception id student activity cannot be acivated.
        }


        //initalizes rootview and textview
        View rootView = inflater.inflate(R.layout.fragment_default, container, false);

        list = (ListView) rootView.findViewById(R.id.course_list);
        add = (Button) rootView.findViewById(R.id.add_course);
        TextView text = (TextView) rootView.findViewById(R.id.dispName);
        TextView dis_mark = (TextView) rootView.findViewById(R.id.disp_cgpa) ;

        dis_mark.setText("Mark: " + student.calc_percent() + "   GPA: " + student.get_CGpa());
        text.setText("Hi " + student.getName().toUpperCase().trim() + "!"); // sets student text

        ArrayList<Course> studentSubjects = new ArrayList<Course>();
        //Subject array is also created to store subjects.
        Course[] subjectArray = new Course[0];

        try {
            subjectArray = new Course[student.getCourses().size()];
            student.getCourses().toArray(subjectArray);
        } catch (Exception e) {
        }
        //Object of subjectAdapter is made.
        CourseAdapter adapter = new CourseAdapter(getActivity(), R.layout.course_layout, subjectArray);
        //The list will get a new adapter (subject adapter).
        list.setAdapter(adapter);
        add.setOnClickListener(this);
        //The list will also listen for any clicks.
        list.setOnItemClickListener(this);
        return rootView; //returns rootview

    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.add_course){
            FragmentManager fragmentManager = getFragmentManager();
            addCourse add = new addCourse();
            fragmentManager.beginTransaction().replace(R.id.relativeLayout, add, add.getTag()).commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //New fragmentManger is created.
        FragmentManager fragmentManager = getFragmentManager();
        //All objects and information needed to transaction into new interface.
        CourseInfo disCourse = new CourseInfo();
        Bundle bundle = new Bundle();
        bundle.putInt("index", position);

        disCourse.setArguments(bundle);
        //Will now show a detailed version of the subject with all the information (disSub).
        fragmentManager.beginTransaction().replace(R.id.relativeLayout, disCourse, disCourse.getTag()).commit();
    }
}
