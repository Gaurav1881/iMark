package com.proj.gaurav.imark;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements View.OnClickListener{


    public Settings() {
        // Required empty public constructor
    }

    EditText name;
    Button save;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        name = (EditText) rootView.findViewById(R.id.settingsName);
        save = (Button) rootView.findViewById(R.id.saveSettings);

        try{
            Student student = new Student();
            student.load(getActivity());
            name.setText(student.getName());
        }
        catch(Exception e){

        }
        save.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveSettings){
            if(!name.getText().equals("")){
                try{
                    Student student = new Student();
                    student.load(getActivity());
                    student.setName(String.valueOf(name.getText()));
                    student.save(getActivity());

                    FragmentManager fragmentManager = getFragmentManager();
                    Default def = new Default();
                    fragmentManager.beginTransaction().replace(R.id.relativeLayout, def, def.getTag()).commit();
                }
                catch(Exception e){

                }
            }
        }

    }
}
