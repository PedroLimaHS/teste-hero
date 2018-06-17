package br.com.unibratec.unibratecheros.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Biography implements Parcelable {
    @JsonProperty("full-name")
    public String fullName;
    @JsonProperty("alter-egos")
    public String alterEgos;
    @JsonProperty("place-of-birth")
    public String placeOfBirth;
    @JsonProperty("first-appearance")
    public String firstAppearance;
    public String publisher;
    public String alignment;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fullName);
        dest.writeString(this.alterEgos);
        dest.writeString(this.placeOfBirth);
        dest.writeString(this.firstAppearance);
        dest.writeString(this.publisher);
        dest.writeString(this.alignment);
    }

    public Biography() {
    }

    protected Biography(Parcel in) {
        this.fullName = in.readString();
        this.alterEgos = in.readString();
        this.placeOfBirth = in.readString();
        this.firstAppearance = in.readString();
        this.publisher = in.readString();
        this.alignment = in.readString();
    }

    public static final Parcelable.Creator<Biography> CREATOR = new Parcelable.Creator<Biography>() {
        @Override
        public Biography createFromParcel(Parcel source) {
            return new Biography(source);
        }

        @Override
        public Biography[] newArray(int size) {
            return new Biography[size];
        }
    };
}
