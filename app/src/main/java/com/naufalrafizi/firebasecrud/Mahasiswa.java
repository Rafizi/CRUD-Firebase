package com.naufalrafizi.firebasecrud;

/**
 * Created by Naufal on 11/3/2017.
 */

public class Mahasiswa  {
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSiapa() {
        return siapa;
    }

    public void setSiapa(String siapa) {
        this.siapa = siapa;
    }

    public String nama,siapa;
    public Mahasiswa(){

    }
    public Mahasiswa(String nama, String siapa){
        this.nama=nama;
        this.siapa=siapa;
    }
    public String toString() {
        return nama +" "+ siapa +" " ;
    }
}
