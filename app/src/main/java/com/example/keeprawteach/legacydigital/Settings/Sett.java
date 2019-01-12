package com.example.keeprawteach.legacydigital.Settings;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.legacydigital.Calling.Calls;
import com.example.keeprawteach.legacydigital.R;

import static java.security.AccessController.getContext;

public class Sett extends AppCompatActivity {

    private ListView home;

    String[] values=new String[]{"Account Settings","General ","Notification","Contact Us","Terms and Privacy Policy","App Info","Logout"};

    int[] images = {
            R.drawable.user,
            R.drawable.cog,
            R.drawable.note,
            R.drawable.phone,
            R.drawable.terms,
            R.drawable.infors,
            R.drawable.logout};

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sett);

        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Settings");

        home=(ListView) findViewById(R.id.homelist);

        MyAdapter adapter=new MyAdapter(getApplicationContext(),values,images);

        home.setAdapter(adapter);


        home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(getApplicationContext(), "Account Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "General", Toast.LENGTH_SHORT).show();
//                        ipset();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:

                        Intent call = new Intent(getApplicationContext(), Calls.class);

                        startActivity(call);

                        break;
                    case 4:

//                        Intent intent = new Intent(getContext(), Conditions.class);

//                        startActivity(intent);
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), "App Info", Toast.LENGTH_SHORT).show();


//                        Intent a = new Intent(getContext(), MpesaPay.class);
//
//                        startActivity(a);

                        break;

                    case 6:
//                        promptLogout();
                        break;
                }

            }
        });

    }

    private class MyAdapter extends ArrayAdapter {


        Context context;

        String[] values;

        int [] imageArray;

        public MyAdapter(Context context, String[] values, int[] imageArray) {
            super(context,R.layout.settings,R.id.bb,values);

            this.context = context;

            this.values = values;

            this.imageArray = imageArray;

        }

        @NonNull
        @Override
        public View getView(int position,  @NonNull View convertView,@NonNull ViewGroup parent) {

            LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row=inflater.inflate(R.layout.settings,parent,false);

            ImageView imageView=(ImageView) row.findViewById(R.id.aa);

            TextView textView=(TextView)row.findViewById(R.id.bb);

            imageView.setImageResource(imageArray[position]);

            textView.setText(values[position]);

            return row;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return super.onSupportNavigateUp();
    }
}
