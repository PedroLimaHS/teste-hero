package br.com.unibratec.unibratecheros.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appearance implements Parcelable {
    public String gender;
    public String race;
    public String eyeColor;
    public String hairColor;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gender);
        dest.writeString(this.race);
        dest.writeString(this.eyeColor);
        dest.writeString(this.hairColor);
    }

    public Appearance() {
    }

    protected Appearance(Parcel in) {
        this.gender = in.readString();
        this.race = in.readString();
        this.eyeColor = in.readString();
        this.hairColor = in.readString();
    }

    public static final Parcelable.Creator<Appearance> CREATOR = new Parcelable.Creator<Appearance>() {
        @Override
        public Appearance createFromParcel(Parcel source) {
            return new Appearance(source);
        }

        @Override
        public Appearance[] newArray(int size) {
            return new Appearance[size];
        }
    };
}
