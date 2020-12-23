package com.sachin.sachinsah.langar;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;


public class MenuUpdateFragment extends Fragment {


    EditText messname,ownername,newmenu;
    Button updatemenubutton;
    private DatabaseReference mDatabaseRef;
    private String key;

    public MenuUpdateFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.activity_menu_update, container, false);



        Bundle passkey = this.getArguments();
        key=  passkey.getString("key");



        messname = (EditText)layout.findViewById(R.id.omessname);
        ownername = (EditText)layout.findViewById(R.id.ownernumber);
        newmenu = (EditText)layout.findViewById(R.id.newmenu);
        updatemenubutton = (Button)layout.findViewById(R.id.updatemenu);


        updatemenubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                messname.setText(key);

        /*        mDatabaseRef = FirebaseDatabase.getInstance().getReference("shiftdate");

                mDatabaseRef.child("type").child("Whatsapp").child("Shopping Deals").child(key).child("report_status").setValue(report_status);

*/

            }
        });

        return layout;
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


