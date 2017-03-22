package com.droiddigger.mhlushan.utshobday4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText nameEt, idEt;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEt= (EditText) findViewById(R.id.nameEt);
        idEt= (EditText) findViewById(R.id.idET);

        //Initializing the firebase database reference
        reference= FirebaseDatabase.getInstance().getReference();

    }

    public void saveData(View view) {
        String name=nameEt.getText().toString();
        String id =idEt.getText().toString();

        Info info=new Info(id, name);


        //make the EditText empty again
        nameEt.setText("");
        idEt.setText("");

        //Making a map/hashmap variable what will be used to store data
        Map<String, Object> values = info.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        String key = reference.child("people").push().getKey();
        childUpdates.put("/people/"+key , values);

        //updating the database node
        reference.updateChildren(childUpdates);
        Toast.makeText(this, "Info added", Toast.LENGTH_SHORT).show();
    }

    public void viewData(View view) {
        startActivity(new Intent(MainActivity.this, Persons.class));
    }
}
