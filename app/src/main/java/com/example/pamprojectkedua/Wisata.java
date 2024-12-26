package com.example.pamprojectkedua;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Wisata implements Parcelable {
    private String nama;
    private String deskripsi;
    private float rating;
    private boolean isSaved;
    private int image;

    // Constructor
    public Wisata(String nama, String deskripsi, float rating, boolean isSaved, int image) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.rating = rating;
        this.isSaved = isSaved;
        this.image = image;
    }

    // Getter and Setter methods
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    // Parcelable implementation
    protected Wisata(Parcel in) {
        nama = in.readString();
        deskripsi = in.readString();
        rating = in.readFloat();
        isSaved = in.readByte() != 0;
        image = in.readInt();
    }

    public static final Creator<Wisata> CREATOR = new Creator<Wisata>() {
        @Override
        public Wisata createFromParcel(Parcel in) {
            return new Wisata(in);
        }

        @Override
        public Wisata[] newArray(int size) {
            return new Wisata[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(deskripsi);
        dest.writeFloat(rating);
        dest.writeByte((byte) (isSaved ? 1 : 0));
        dest.writeInt(image);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wisata wisata = (Wisata) o;
        return nama.equals(wisata.nama);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nama);
    }
}
