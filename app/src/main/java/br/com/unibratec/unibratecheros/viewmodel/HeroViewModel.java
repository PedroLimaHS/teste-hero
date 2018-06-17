package br.com.unibratec.unibratecheros.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;


import br.com.unibratec.unibratecheros.model.Hero;
import br.com.unibratec.unibratecheros.persistence.AppDataBase;

public class HeroViewModel extends AndroidViewModel {

    private final LiveData<List<Hero>> heros;
    private AppDataBase appDatabase;

    public HeroViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDataBase.getInstance(this.getApplication());
        heros = appDatabase.heroDAO().getList();
    }

    public LiveData<List<Hero>> getHeros() {
        return heros;
    }
    //salva  o heroi com o click na imagem
    public void insertHero(final Hero hero) {
        new addAsyncTask(appDatabase).execute(hero);
    }
    //remove  o heroi com o click no botao
    public void removeHero(final Hero hero) {
        new removeAsyncTask(appDatabase).execute(hero);
    }

    private static class addAsyncTask extends AsyncTask<Hero, Void, Void> {
        private AppDataBase db;

        addAsyncTask(AppDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Hero... params) {
            db.heroDAO().insert(params[0]);
            return null;
        }
    }

    private static class removeAsyncTask extends AsyncTask<Hero, Void, Void> {
        private AppDataBase db;

        removeAsyncTask(AppDataBase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Hero... params) {
            db.heroDAO().delete(params[0]);
            return null;
        }
    }
}
