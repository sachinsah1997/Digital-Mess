package com.sachin.sachinsah.langar;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MenuListFragment extends Fragment {

    private DatabaseReference mDatabaseRef,date;
    private List<AdapterAll> list;
    private ListView lv;
    private MenuFirebase firebaseobj;
    private ProgressDialog progressDialog;
    private Fragment fragment;
    private TextView dt,shift;
    private String subtitle;

    Bundle passkey = new Bundle();

    public MenuListFragment() {
        // Required empty public constructo
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menulistview, container, false);
        Bundle bundle = this.getArguments();

       subtitle= bundle.getString("hostel");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(subtitle);
        list = new ArrayList<>();
        lv = (ListView) rootView.findViewById(R.id.listViewMenu);

        dt = (TextView) rootView.findViewById(R.id.date);
        shift = (TextView) rootView.findViewById(R.id.shift);

        //Show progress dialog during list image loading
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Keep Patience...");
        progressDialog.show();

        date = FirebaseDatabase.getInstance().getReference("shiftdate");

        date.addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    AdapterAll img = snapshot.getValue(AdapterAll.class);
                    dt.setText(img.getDate());
                    shift.setText(img.getShift());


                }


                }


            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }


        });

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(subtitle);



        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    AdapterAll img = snapshot.getValue(AdapterAll.class);
                    list.add(img);
                }

                //Init adapter
                firebaseobj = new MenuFirebase(getActivity(), R.layout.menu_structure, list);
                //Set adapter for listview
                lv.setAdapter(firebaseobj);
            }


            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                AdapterAll game = AdapterAll.get(position);



                String key = mDatabaseRef.push().getKey();
               DatabaseReference DatabaseRef = FirebaseDatabase.getInstance().getReference(subtitle);
                    // String messname=DatabaseRef.


                DatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {




                        String messname = dataSnapshot.getValue(String.class);


                         fragment=new MenuUpdateFragment();
                        passkey.putString("key", messname);
                        fragment.setArguments(passkey);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container_body, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Toast toast = Toast.makeText(getActivity().getApplicationContext(), key, Toast.LENGTH_SHORT);
                toast.show();


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












