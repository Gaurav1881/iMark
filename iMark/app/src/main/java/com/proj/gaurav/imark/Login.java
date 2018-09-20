package com.proj.gaurav.imark;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements OnClickListener {

    // variables are initialized
    Button loginButton ;
    EditText textName;
    View rootView;

    /**
     * Empty constructor
     */
    public Login() {
        // Required empty public constructor
    }

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

        // Inflate the rootview for this fragment
        rootView = inflater.inflate(R.layout.fragment_login, container, false);

        // set the variables to their specific variables
        loginButton = (Button) rootView.findViewById(R.id.loginButton);
        textName = (EditText) rootView.findViewById(R.id.textName);

        // apply on click listener
        loginButton.setOnClickListener(this);

        //rootView is returned
        return rootView;
    }


    /**
     * enables on click function which controls what happens when something is clicked
     *
     * @param v - the view object from which the object is clicked
     */
    public void onClick(View view) {


        if(view.getId()== R.id.loginButton){
            try{

                // the users names and grade are obtained from the text views
                String name = String.valueOf(textName.getText());

                // if the users name is not filled in, then the user is not saved and the com will not move on
                if(name!=""){
                    Student newStudent = new Student(name);
                    newStudent.save(getActivity());
                }

                //the new student is opened
                Student ns = new Student();
                ns.load(getActivity());

                // the login is relaunched
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);

            }
            catch(Exception e){
                e.printStackTrace();

            }



        }

    }
}
