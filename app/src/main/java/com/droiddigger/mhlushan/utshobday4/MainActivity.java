package com.droiddigger.mhlushan.utshobday4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        reference= FirebaseDatabase.getInstance().getReference();

    }

    public void saveData(View view) {
        String name=nameEt.getText().toString();
        String id =idEt.getText().toString();

        Info info=new Info(name, id);

        Map<String, Object> values = info.toMap();


        Map<String, Object> childUpdates = new HashMap<>();


        String key = reference.child("people").push().getKey();

        childUpdates.put("/people/"+key , values);

        reference.updateChildren(childUpdates);
    }
}
