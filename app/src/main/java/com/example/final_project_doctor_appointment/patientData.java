package com.example.final_project_doctor_appointment;

public class patientData {
    String name;
    int age;
    public patientData(String name, int age) {
        this.name=name;
        this.age=age;
    }

    public static int get(int position) {
        return position;
    }

    public static void getFilter() {
    }

    public String name() {
        return name;
    }
}
