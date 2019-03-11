package com.example.vedantmehra.relationpagedynamicinvestor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RelationPageInvestor extends AppCompatActivity  {

    String name, email;
    DatabaseReference databaseReferenceInvestor, databaseReferenceStudent;
    ArrayList<String> StudentId = new ArrayList<>();
    ArrayList<String[]> StudentDetail = new ArrayList<>();
    MyRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation_page_investor);

        databaseReferenceInvestor = FirebaseDatabase.getInstance().getReference("Investor");

        databaseReferenceStudent = FirebaseDatabase.getInstance().getReference("Student");
        recyclerView = (RecyclerView)findViewById(R.id.rvStudent);

        RecyclerView.LayoutManager  manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new MyRecyclerAdapter(this, StudentDetail);
        recyclerView.setAdapter(adapter);

        databaseReferenceInvestor.child("vedant").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.child("relations").getChildren()){
                    String s= null;
                    s = dataSnapshot1.getValue().toString();
                    //while(s==null);
                   // Toast.makeText(RelationPageInvestor.this, "in child vedant", Toast.LENGTH_SHORT).show();
                    StudentId.add(s);
                }
                //Toast.makeText(RelationPageInvestor.this, StudentId.size() + "", Toast.LENGTH_SHORT).show();
                StudentDetail.clear();
                for(String s : StudentId){
                    databaseReferenceStudent.child(s).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            name = dataSnapshot.child("name").getValue().toString();
                            email = dataSnapshot.child("email").getValue().toString();
                            //while (name == null && email == null);
                            //Toast.makeText(RelationPageInvestor.this, "in one relation " + name + " " + email, Toast.LENGTH_SHORT).show();
                            StudentDetail.add(new String[]{name, email});
                            Toast.makeText(RelationPageInvestor.this, StudentDetail.size()+"", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                //Toast.makeText(RelationPageInvestor.this, StudentDetail.size()+"", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



}