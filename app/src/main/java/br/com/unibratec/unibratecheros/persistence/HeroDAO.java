package br.com.unibratec.unibratecheros.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.unibratec.unibratecheros.model.Hero;

@Dao
public interface HeroDAO {

    @Query("SELECT * FROM Hero ")
    LiveData<List<Hero>> getList();

    @Update
    void update(Hero os);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Hero... oss);

    @Delete
    void delete(Hero o);
}