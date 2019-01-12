package com.example.keeprawteach.legacydigital.Calling;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.legacydigital.R;

public class Calls extends AppCompatActivity {


    private Toolbar toolbar;
    private ImageView MANAGER,CALLTELKOM,CUSTOMERCARE,CALLFPRHELP;
    private TextView Japheth,Telkom,Customer,Help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Call Us");

        Japheth=(TextView)findViewById(R.id.japhee);

        Telkom=(TextView)findViewById(R.id.orange);

        Customer=(TextView)findViewById(R.id.customerCare);

        Help=(TextView)findViewById(R.id.helpLine);

        MANAGER=(ImageView)findViewById(R.id.Branch);

        CALLFPRHELP=(ImageView)findViewById(R.id.CallHelp);

        CALLTELKOM=(ImageView)findViewById(R.id.CallTelcom);

        CUSTOMERCARE=(ImageView)findViewById(R.id.CallCustomerCare);

        actionListener();
    }


    private void actionListener() {
        final String number=Japheth.getText().toString();

        final String Telkomline=Telkom.getText().toString();

        final String customer=Customer.getText().toString();

        final String help=Help.getText().toString();

        CALLFPRHELP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(help);
            }
        });

        CUSTOMERCARE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(customer);
            }
        });
        CALLTELKOM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(Telkomline);
            }
        });
        MANAGER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(number);
            }
        });
    }

    private void makeCall(String number) {


        Intent i=new Intent(Intent.ACTION_CALL);

        if (number.isEmpty()){
            i.setData(Uri.parse("tel:0724743788"));
        }else{
            i.setData(Uri.parse("tel:"+number));
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Please grant permision to call", Toast.LENGTH_SHORT).show();
            requestPermission();
        }else{
            startActivity(i);
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }
}
