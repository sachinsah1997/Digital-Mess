package com.sachin.sachinsah.langar;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MenuFirebase extends ArrayAdapter<AdapterAll> {
        private FragmentActivity context;
        private int resource;
        private List<AdapterAll> list;

        public MenuFirebase(@NonNull FragmentActivity context, @LayoutRes int resource, @NonNull List<AdapterAll> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            list = objects;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public AdapterAll getItem(int position) {

            return list.get(getCount() - position - 1);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();

            View v = inflater.inflate(resource, null);

            TextView messname = (TextView) v.findViewById(R.id.messname);
            TextView menu = (TextView) v.findViewById(R.id.menu);

            messname.setText(list.get(position).getMessname());
            menu.setText(list.get(position).getMenu());

            return v;

        }
    }





