package com.example.final_project_doctor_appointment;

import android.content.Context;
import android.content.Intent;
import android.security.identity.CipherSuiteNotSupportedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.widget.TextView;
import android.widget.Toast;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.MyViewHolder> {
    //    public PatientAdapter(List patientDataList) {
    Context c;
    ArrayList<patientData> patientDataList;
//    private onItemClickListener mListener;

//    public PatientAdapter(ArrayList<patientData> patientDataList) {

//    }


//    public  interface  onItemClickListener{
//            void onIemClick(int position);
//        }
//    public void setOnItemClickListener(MainActivity mainActivity) {
//        mListener = (onItemClickListener) mainActivity;
//    }
//        public PatientAdapter(Context context , ArrayList<patientData> patientDataList) {
//        mContext = context;
//    }

    public PatientAdapter(Context c,ArrayList<patientData> patientDataList) {
        this.patientDataList = patientDataList;
        this.c=c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
//        patientData data=patientData.get(position);
        patientData data = (patientData) patientDataList.get(position);
        holder.name.setText(data.name);
        holder.age.setText(String.valueOf(data.age));
//        holder.itemView.setOnClickListener(onItemClicked(item));
//        holder.setItemClickListener(new itemClickListener(){
//            @Override
//            public void onItemClickListener(View v,int position){
//                Intent intent = new Intent(c,patient_detail.class);
//                Toast.makeText(c, "Lucifer", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        holder.setItemClickListener(new itemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Toast.makeText(c,"Lucifer",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(c,patient_detail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientDataList.size();
    }

    public void filterList(ArrayList<patientData> filteredlist) {
        patientDataList = filteredlist;
        // below line is to notify our adapter
//         as change in recycler view data.
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, age;
        View mView;
        itemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            mView = name;
            itemView.setOnClickListener(this);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mListener !=null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            mListener.onIemClick(position);
//                        }
//                    }
//                }
//            });

        }


        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v, getLayoutPosition());
        }
        public void setItemClickListener(itemClickListener ic){
            this.itemClickListener=ic;
        }

    }
}
