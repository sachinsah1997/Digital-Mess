package com.sachin.sachinsah.langar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMenu extends Fragment {

    EditText personname, suggestion;
    private DatabaseReference mDatabaseRef;
    Button addmenu,adddate;
    Spinner hostelname,shift;
    Fragment fragment;
    public AddMenu(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_menu, container, false);



                personname = (EditText)rootView.findViewById(R.id.messname);
                suggestion = (EditText) rootView.findViewById(R.id.suggestion);

                addmenu = (Button) rootView.findViewById(R.id.addmenu);
                adddate = (Button) rootView.findViewById(R.id.adddate);



                hostelname = (Spinner)rootView.findViewById(R.id.database);
                int initpos=hostelname.getSelectedItemPosition();
                hostelname.setSelection(initpos, false);

                shift = (Spinner)rootView.findViewById(R.id.shift);
                int initpos2=shift.getSelectedItemPosition();
                shift.setSelection(initpos2, false);

                addmenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String hostnme=hostelname.getSelectedItem().toString();

                        AdapterAll p = AdapterAll.getInstance();


                        mDatabaseRef = FirebaseDatabase.getInstance().getReference(hostnme);
                        String name = personname.getText().toString().trim();
                        String suggest =suggestion.getText().toString().trim();


                        if (name.equals("") || suggest.equals("")) {
                            Toast.makeText(getActivity(), "Dostar :) khali jagya bharo ne", Toast.LENGTH_LONG).show();
                            return;
                        }

                        else {
                            String i = personname.getText().toString();
                            String j = suggestion.getText().toString();

                            //Display success toast msg
                            AdapterAll a = new AdapterAll(hostnme,i,j);

                            //Save info in to firebase database
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(a);
                            Toast.makeText(getActivity(), "data uploaded", Toast.LENGTH_SHORT).show();

                            personname.setText("");
                            suggestion.setText("");

                              }
                    }
                });


        adddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s=shift.getSelectedItem().toString();

                AdapterAll p = AdapterAll.getInstance();


                mDatabaseRef = FirebaseDatabase.getInstance().getReference("shiftdate");


                java.util.Calendar cal = java.util.Calendar.getInstance();
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                String updatingdate = sdf.format(cal.getTime());

                //Display success toast msg
                AdapterAll df = new AdapterAll(updatingdate,s);

                    //Save info in to firebase database
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(df);
                    Toast.makeText(getActivity(), "data uploaded", Toast.LENGTH_SHORT).show();


                }
        });




        TextView aboutus=(TextView)rootView.findViewById(R.id.aboutus);
                aboutus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        fragment=new HomeFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                });


        // Inflate the layout for this fragment
        return rootView;



    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}




