package com.example.firebaseejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    TextView textView;

    //implementando al recyclerView
    List<Persona> lista = new ArrayList<>();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicial();
        //LeerDato();

        FirebaseRecycleView();
        usarRecycleView();

    }

    public void inicial(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(Referencias.GDG_Reference);


        textView = (TextView) findViewById(R.id.text);

       // addView();
    }

    public void addView(){
        //El nombre de la key
        textView.setText(databaseReference.getKey());
    }

    public void LeerDato(){
        //para actualizar datos
        //databaseReference.setValue("StudyJams 2");

        //para añadir valores a la referencia
       // databaseReference.push().setValue("StudyJams 2");

        //referencia a todos los datos q entren
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor= dataSnapshot.getValue(String.class);
                textView.setText(valor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //referencia a el primer dato q entre y que no repita
        /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor= dataSnapshot.getValue(String.class);
                textView.setText(valor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    public void FirebaseRecycleView(){
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lista.removeAll(lista);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {
                    Map<String, Object> dataChild = (Map<String, Object>) snapshot.getValue();
                    String nombre=dataChild.get("nombre").toString();
                    String apellido=dataChild.get("apellido").toString();
                    String ci=dataChild.get("ci").toString();
                    lista.add(new Persona(apellido,ci,nombre));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void usarRecycleView() {
        recycler = (RecyclerView) findViewById(R.id.recycler_view);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);
        recycler.setLayoutManager(new GridLayoutManager(this, 1));
        // Crear un nuevo adaptador
        adapter = new PersonaAdapter(lista);
        recycler.setAdapter(adapter);
    }

}
