package br.com.unibratec.unibratecheros.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;


@Entity
public class Hero implements Parcelable {
    @PrimaryKey public int id;//chave primaria
    public String name;
    public String imagem;
    @Embedded public Powerstats powerstats; //Tabelas no bd
    @Embedded public Biography biography; //Tabelas no bd
    @Embedded public Appearance appearance;//Colunas na Tabela Hero
    @Embedded public Work work;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imagem);
        dest.writeParcelable(this.powerstats, flags);
        dest.writeParcelable(this.biography, flags);
        dest.writeParcelable(this.appearance, flags);
        dest.writeParcelable(this.work, flags);
    }

    public Hero() {
    }

    protected Hero(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.imagem = in.readString();
        this.powerstats = in.readParcelable(Powerstats.class.getClassLoader());
        this.biography = in.readParcelable(Biography.class.getClassLoader());
        this.appearance = in.readParcelable(Appearance.class.getClassLoader());
        this.work = in.readParcelable(Work.class.getClassLoader());
    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
