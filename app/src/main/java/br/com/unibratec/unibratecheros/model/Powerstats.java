package br.com.unibratec.unibratecheros.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Powerstats implements Parcelable {
    public String intelligence;
    public String strength;
    public String speed;
    public String durability;
    public String power;
    public String combat;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.intelligence);
        dest.writeString(this.strength);
        dest.writeString(this.speed);
        dest.writeString(this.durability);
        dest.writeString(this.power);
        dest.writeString(this.combat);
    }

    public Powerstats() {
    }

    protected Powerstats(Parcel in) {
        this.intelligence = in.readString();
        this.strength = in.readString();
        this.speed = in.readString();
        this.durability = in.readString();
        this.power = in.readString();
        this.combat = in.readString();
    }

    public static final Parcelable.Creator<Powerstats> CREATOR = new Parcelable.Creator<Powerstats>() {
        @Override
        public Powerstats createFromParcel(Parcel source) {
            return new Powerstats(source);
        }

        @Override
        public Powerstats[] newArray(int size) {
            return new Powerstats[size];
        }
    };
}
