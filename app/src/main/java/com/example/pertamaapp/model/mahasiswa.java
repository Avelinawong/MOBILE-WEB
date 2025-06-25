package com.example.pertamaapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class mahasiswa extends RealmObject {
    @PrimaryKey
    private int studentid;
    private String nama, jeniskelamin, hobi, email, programstudi;

    public mahasiswa() {}

    public mahasiswa(int studentid, String nama, String jeniskelamin, String hobi, String email, String programstudi) {
        this.studentid = studentid;
        this.nama = nama;
        this.jeniskelamin = jeniskelamin;
        this.hobi = hobi;
        this.email = email;
        this.programstudi = programstudi;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramstudi() {
        return programstudi;
    }

    public void setProgramstudi(String programstudi) {
        this.programstudi = programstudi;
    }

    @Override
    public String toString() {
        return "mahasiswa{" +
                "studentid=" + studentid +
                ", nama='" + nama + '\'' +
                ", jeniskelamin='" + jeniskelamin + '\'' +
                ", hobi='" + hobi + '\'' +
                ", email='" + email + '\'' +
                ", programstudi='" + programstudi + '\'' +
                '}';
    }
}
