package com.example.crudsqlite.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pegawai implements Parcelable {
    private String id, name, birthDate, gender;

    public Pegawai(Parcel in) {
        id = in.readString();
        name = in.readString();
        birthDate = in.readString();
        gender = in.readString();
    }

    public static final Creator<Pegawai> CREATOR = new Creator<Pegawai>() {
        @Override
        public Pegawai createFromParcel(Parcel in) {
            return new Pegawai(in);
        }

        @Override
        public Pegawai[] newArray(int size) {
            return new Pegawai[size];
        }
    };

    public Pegawai() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(birthDate);
        dest.writeString(gender);
    }
}
