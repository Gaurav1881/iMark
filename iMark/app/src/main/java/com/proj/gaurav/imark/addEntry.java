package com.proj.gaurav.imark;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class addEntry extends Fragment implements View.OnClickListener{



    public addEntry() {
        // Required empty public constructor
    }

    EditText name;
    EditText mark;
    EditText weight;
    Button save;
    Button back;
    Button delete;
    int index;
    int index2;
    boolean newEntry;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_entry, container, false);
        name = (EditText) rootView.findViewById(R.id.entryName);
        mark = (EditText) rootView.findViewById(R.id.entryMark);
        weight = (EditText) rootView.findViewById(R.id.entryWeight);
        save = (Button) rootView.findViewById(R.id.saveEntry);
        back = (Button) rootView.findViewById(R.id.back3);
        delete = (Button) rootView.findViewById(R.id.delEntry);

        index = getArguments().getInt("index");
        newEntry = getArguments().getBoolean("new");
        save.setOnClickListener(this);
        back.setOnClickListener(this);
        delete.setOnClickListener(this);
        // Inflate the layout for this fragment

        if(newEntry == false){
            index2 = getArguments().getInt("newIndex");
            try {
                Student student = new Student();
                student.load(getActivity());
                name.setText(student.getCourses().get(index).getEntry_name().get(index2));
                mark.setText(""  +student.getCourses().get(index).getMarks().get(index2));
                weight.setText(""  +student.getCourses().get(index).getWeights().get(index2));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {

        double prev_weight = 0;
        double last_ent = 0;
        try{
            Student student = new Student();
            student.load(getActivity());

            last_ent = student.getCourses().get(index).getWeights().get(index2);
            prev_weight = student.getCourses().get(index).getSumWeight();
            student.save(getActivity());
        }
        catch(Exception e){

        }

        if(v.getId()== R.id.saveEntry){
            if(!name.equals("") && !mark.equals("") && !weight.equals("")){

                String entryName = String.valueOf(name.getText());
                Double entryMark = Double.valueOf(String.valueOf(mark.getText()));
                Double entryWeight = Double.valueOf(String.valueOf(weight.getText()));
                entryMark = Math.round(entryMark * 100.0) / 100.0;
                entryWeight = Math.round(entryWeight * 100.0) / 100.0;

                try {
                    Student student = new Student();
                    student.load(getActivity());

                    if(newEntry){
                        if(entryWeight+ prev_weight <= 100){
                            student.getCourses().get(index).addMark(entryName, entryMark, entryWeight);
                            student.save(getActivity());
                        }
                        else{
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
                            dlgAlert.setMessage("Weights must add up to 100 or less");
                            dlgAlert.setTitle("Error");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dismiss the dialog
                                        }
                                    });
                        }

                    }
                    else{
                        if((entryWeight+ prev_weight - last_ent) <= 100){
                            student.getCourses().get(index).getEntry_name().set(index2, entryName);
                            student.getCourses().get(index).getMarks().set(index2, entryMark);
                            student.getCourses().get(index).getWeights().set(index2, entryWeight);

                            student.save(getActivity());


                        }
                        else{
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
                            dlgAlert.setMessage("Weights must add up to 100 or less");
                            dlgAlert.setTitle("Error");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(true);
                            dlgAlert.create().show();
                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            //dismiss the dialog
                                        }
                                    });
                        }

                    }

                    student.save(getActivity());

                    FragmentManager fragmentManager = getFragmentManager();
                    CourseInfo info = new CourseInfo();
                    Bundle bundle = new Bundle();
                    bundle.putInt("index", index);

                    info.setArguments(bundle);

                    fragmentManager.beginTransaction().replace(R.id.relativeLayout, info, info.getTag()).commit();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    if(v.getId()== R.id.back3){
        FragmentManager fragmentManager = getFragmentManager();
        CourseInfo info = new CourseInfo();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);

        info.setArguments(bundle);

        fragmentManager.beginTransaction().replace(R.id.relativeLayout, info, info.getTag()).commit();
        }
    if(v.getId() == R.id.delEntry){
        if(newEntry){

        }
        else{
            try {
                Student student = new Student();
                student.load(getActivity());
                student.getCourses().get(index).getEntry_name().remove(index2);
                student.getCourses().get(index).getMarks().remove(index2);
                student.getCourses().get(index).getWeights().remove(index2);

                student.save(getActivity());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getFragmentManager();
            CourseInfo info = new CourseInfo();
            Bundle bundle = new Bundle();
            bundle.putInt("index", index);

            info.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.relativeLayout, info, info.getTag()).commit();
        }
    }
    }
}
