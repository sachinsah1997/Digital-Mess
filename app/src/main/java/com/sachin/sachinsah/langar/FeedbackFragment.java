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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackFragment extends Fragment {


    EditText personname, suggestion,phoneno;
    private DatabaseReference mDatabaseRef;
    Button submit;
    Fragment fragment;

    public FeedbackFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);



        personname = (EditText)rootView.findViewById(R.id.name);
        suggestion = (EditText)rootView.findViewById(R.id.suggestion);
        phoneno = (EditText)rootView.findViewById(R.id.phoneno);
        submit = (Button)rootView.findViewById(R.id.feedsubmit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AdapterAll p = AdapterAll.getInstance();


                mDatabaseRef = FirebaseDatabase.getInstance().getReference("feedback");
                String name = personname.getText().toString().trim();
                String suggest =suggestion.getText().toString().trim();
                String number =phoneno.getText().toString().trim();



                if (name.equals("") || suggest.equals("")) {
                    Toast.makeText(getActivity(), "Dostar :) khali jagya bharo ne", Toast.LENGTH_LONG).show();
                    return;
                }

                else {
                    String i = personname.getText().toString();
                    String j = suggestion.getText().toString();

                    //Display success toast msg
                    AdapterAll a = new AdapterAll(i,j,"5",number);

                    //Save info in to firebase database
                    String uploadId = mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(a);
                    Toast.makeText(getActivity(), "data uploaded", Toast.LENGTH_SHORT).show();

                    personname.setText("");
                    suggestion.setText("");
                    phoneno.setText("");

                }
            }
        });

        TextView aboutus=(TextView)rootView.findViewById(R.id.aboutus);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment=new AboutUsFragment();
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







