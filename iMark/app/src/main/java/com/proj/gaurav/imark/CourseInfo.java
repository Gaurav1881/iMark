package com.proj.gaurav.imark;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
    public class CourseInfo extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{


    public CourseInfo() {
        // Required empty public constructor
    }

    int index = 0;
    ListView markList;
    TextView courseName;
    TextView courseMark;
    CheckBox isNcr;
    Button add;
    Button back;
    Button delete;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_course_info, container, false);

        index = getArguments().getInt("index");
        markList = (ListView) rootView.findViewById(R.id.marksList);
        courseName = (TextView) rootView.findViewById(R.id.disCourseName) ;
        courseMark = (TextView) rootView.findViewById(R.id.disCourseMark) ;
        isNcr = (CheckBox) rootView.findViewById(R.id.creditBox);
        add = (Button) rootView.findViewById(R.id.addNewEntry);
        back = (Button) rootView.findViewById(R.id.back2);
        delete = (Button) rootView.findViewById(R.id.deleteCourse);
        Student student = new Student();// new student instance

        try {
            student.load(getActivity());// load activity
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!student.getCourses().get(index).isCredit()){
            isNcr.setChecked(true);
        }

        isNcr.setOnClickListener(this);
        courseName.setText(student.getCourses().get(index).getName());
        courseMark.setText(""+ student.getCourses().get(index).getPercentage() + "%  "
                + student.getCourses().get(index).getGPA());
        String[] nameArray = new String[0];
        Double[] markArray = new Double[0];
        Double[] weightArray = new Double[0];

        try {
            nameArray = new String[student.getCourses().get(index).getEntry_name().size()];
            student.getCourses().get(index).getEntry_name().toArray(nameArray);

            markArray = new Double[student.getCourses().get(index).getMarks().size()];
            student.getCourses().get(index).getMarks().toArray(markArray);

            weightArray = new Double[student.getCourses().get(index).getWeights().size()];
            student.getCourses().get(index).getWeights().toArray(weightArray);

        } catch (Exception e) {
        }
        //Object of subjectAdapter is made.
        MarkAdapter adapter = new MarkAdapter(getActivity(), R.layout.entry_layout, nameArray, markArray, weightArray);
        //The list will get a new adapter (subject adapter).
        markList.setAdapter(adapter);
        //The list will also listen for any clicks.
        markList.setOnItemClickListener(this);
        add.setOnClickListener(this);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addNewEntry){
            //New fragmentManger is created.
            FragmentManager fragmentManager = getFragmentManager();
            //All objects and information needed to transaction into new interface.
            addEntry add = new addEntry();
            Bundle bundle = new Bundle();
            bundle.putInt("index", index);
            bundle.putBoolean("new", true);

            add.setArguments(bundle);
            //Will now show a detailed version of the subject with all the information (disSub).
            fragmentManager.beginTransaction().replace(R.id.relativeLayout, add, add.getTag()).commit();
        }
        if(v.getId() == R.id.back2){
            FragmentManager fragmentManager = getFragmentManager();
            Default def = new Default();
            fragmentManager.beginTransaction().replace(R.id.relativeLayout, def, def.getTag()).commit();
        }
        if(v.getId()== R.id.creditBox){
            if(isNcr.isChecked()){
                try{
                    Student student = new Student();
                    student.load(getActivity());
                    student.getCourses().get(index).setCredit(false);
                    student.save(getActivity());
                }
                catch(Exception e){

                }
            }
            else{
                try{
                    Student student = new Student();
                    student.load(getActivity());
                    student.getCourses().get(index).setCredit(true);
                    student.save(getActivity());
                }
                catch(Exception e){

                }
            }
        }
        if(v.getId() == R.id.deleteCourse){
            try{
                Student student = new Student();
                student.load(getActivity());
                student.getCourses().remove(index);
                student.save(getActivity());

                FragmentManager fragmentManager = getFragmentManager();
                Default def = new Default();
                fragmentManager.beginTransaction().replace(R.id.relativeLayout, def, def.getTag()).commit();
            }
            catch(Exception e){

            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //New fragmentManger is created.
        FragmentManager fragmentManager = getFragmentManager();
        //All objects and information needed to transaction into new interface.
        addEntry add = new addEntry();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        bundle.putInt("newIndex", position);
        bundle.putBoolean("new", false);

        add.setArguments(bundle);
        //Will now show a detailed version of the subject with all the information (disSub).
        fragmentManager.beginTransaction().replace(R.id.relativeLayout, add, add.getTag()).commit();
    }
}
