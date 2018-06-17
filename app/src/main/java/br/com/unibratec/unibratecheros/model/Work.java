package br.com.unibratec.unibratecheros.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Work implements Parcelable {
    public String occupation;
    public String base;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.occupation);
        dest.writeString(this.base);
    }

    public Work() {
    }

    protected Work(Parcel in) {
        this.occupation = in.readString();
        this.base = in.readString();
    }

    public static final Parcelable.Creator<Work> CREATOR = new Parcelable.Creator<Work>() {
        @Override
        public Work createFromParcel(Parcel source) {
            return new Work(source);
        }

        @Override
        public Work[] newArray(int size) {
            return new Work[size];
        }
    };
}
