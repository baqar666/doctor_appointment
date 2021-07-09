package com.example.final_project_doctor_appointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.SearchManager;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView.OnQueryTextListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
//import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

//    SearchView ssearcView;
    private PatientAdapter patientAdapter;
    private ArrayList<patientData> patientDataList = new ArrayList<patientData>();
    String[] check = new String[] {"baqar","devil","Lucifer","Sameaul","Ali Hashmi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
//        ssearcView = findViewById(R.id.search_view);
        patientAdapter = new PatientAdapter(getApplicationContext(), patientDataList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(patientAdapter);
//        patientAdapter.setOnItemClickListener(MainActivity.this);
        patientDataPrepare();

    }


    private void patientDataPrepare() {

        for(int i=0;i<check.length;i++) {
            patientData data = new patientData(check[i], i);
            patientDataList.add(data);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }


            private void filter(String newText) {
                ArrayList<patientData> filteredlist = new ArrayList<>();
                for (patientData item : patientDataList) {
                    if (item.name().toLowerCase().contains(newText.toLowerCase())) {
                        filteredlist.add(item);
                    }
                    if (filteredlist.isEmpty()) {
                        Toast.makeText(MainActivity.this, "No Data Found..", Toast.LENGTH_SHORT).show();
                    } else {
                        patientAdapter.filterList(filteredlist);
                    }
                }
            }
        });
        return true;
    }


//    @Override
//    public void onIemClick(int position) {
//        Intent intent = new Intent(MainActivity.this,patient_detail.class);
//        startActivity(intent);
//    }
}