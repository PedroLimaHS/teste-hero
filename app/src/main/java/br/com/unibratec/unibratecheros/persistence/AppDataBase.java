package br.com.unibratec.unibratecheros.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.unibratec.unibratecheros.model.Hero;

@Database(entities = Hero.class, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "myUnibratecHero.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract HeroDAO heroDAO();


}