package com.proj.gaurav.imark;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CourseAdapter extends ArrayAdapter {

    //initialize variables
    static Context context;
    static int layoutResourceId;
    Course[] courses = null;

    /**
     *
     * This is a constructor method for the event class
     *
     * @param context - the Context of the fragment
     * @param layoutResourceId- the layout resource id of the list view
     * @param e - array of subjects
     */
    public CourseAdapter(Context context, int layoutResourceId, Course[] e) {

        //call arrayadapter superclass to create initialize context and layout id
        super(context, layoutResourceId, e);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.courses = e;
    }


    /**
     * Returns the items id of the row
     * @param  position - the position of the the row
     * @returns the long value of the item id of the row
     */
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    /**
     * Returns the view of the layout
     * @param  position - the position of the the row
     * @param convertView - the view which will be converted
     * @param parent - the parent view group
     * @returns a view
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        //initialize row and text holders
        View row = convertView;
        TextHolder holder = null;

        if(row == null)
        {
            //create the row
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            // create a holder
            holder = new TextHolder();

            //add the text lines to the holder
            holder.txtTitle = (TextView)row.findViewById(R.id.textviewname);
            holder.txtTitle2 = (TextView)row.findViewById(R.id.textviewmark);

            row.setTag(holder);
        }
        else
        {
            holder = (TextHolder)row.getTag();
        }

        //add subject name for the title and teacher for the sub title textview
        holder.txtTitle.setText(courses[position].getName());
        holder.txtTitle2.setText(String.valueOf(courses[position].getPercentage()));
        //    holder.imgIcon.setImageResource(weather.icon);
        return row;
    }

    static class TextHolder
    {
        // initialize the text view lines
        TextView txtTitle;
        TextView txtTitle2;

    }

}
