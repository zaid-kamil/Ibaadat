package com.example.ibaadat;

public class Namaz {
    String fajr,zohar,asr,magrib,isha;

    public Namaz(String date, String fajr, String zohar, String asr, String magrib, String isha) {
        this.fajr = fajr;
        this.zohar = zohar;
        this.asr = asr;
        this.magrib = magrib;
        this.isha = isha;
    }

    @Override
    public String toString() {
        return "Namaz{" +
                "fajr='" + fajr + '\'' +
                ", zohar='" + zohar + '\'' +
                ", asr='" + asr + '\'' +
                ", magrib='" + magrib + '\'' +
                ", isha='" + isha + '\'' +
                '}';
    }
}
