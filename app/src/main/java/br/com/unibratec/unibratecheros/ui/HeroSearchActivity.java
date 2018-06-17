package br.com.unibratec.unibratecheros.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import br.com.unibratec.unibratecheros.R;
import br.com.unibratec.unibratecheros.deserializer.HeroResponse;
import br.com.unibratec.unibratecheros.internet.HeroRest;
import br.com.unibratec.unibratecheros.model.Hero;
import br.com.unibratec.unibratecheros.ui.adapter.HeroRecyclerAdapter;
import br.com.unibratec.unibratecheros.ui.adapter.HeroRecyclerOnClick;
import br.com.unibratec.unibratecheros.viewmodel.HeroViewModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class HeroSearchActivity extends AppCompatActivity implements HeroRecyclerOnClick {
    private EditText heroName;
    private RecyclerView rvHero;
    private HeroRecyclerAdapter heroRecyclerAdapter;
    private HeroViewModel heroViewModel;
    //load na tela de procura do heroi
    private MaterialDialog dialog;
    FloatingActionButton mBac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_search);
        loadView();
        loadRecyclerView();
        heroViewModel = ViewModelProviders.of(this).get(HeroViewModel.class);




    }

    private void loadView() {
        heroName = findViewById(R.id.heroName);
        rvHero = findViewById(R.id.rv_heros);
        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(view -> {
            heroRecyclerAdapter.clearData();
            //load na tela de procura do heroi
            dialog.show();
            find();
        });

        dialog = new MaterialDialog.Builder(this)
                .title(getResources().getString(R.string.MDSearching))
                .content(getResources().getString(R.string.MDplease))
                .progress(true, 0)
                //.progressIndeterminateStyle(true)
                .build();
        //implementação do botao de voltar para a MainActivity 17/06/2018 00:56
        FloatingActionButton fab = findViewById(R.id.btmain);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    private void loadRecyclerView() {
        heroRecyclerAdapter = new HeroRecyclerAdapter(new ArrayList<>(), this);
        rvHero.setLayoutManager(new LinearLayoutManager(this));
        rvHero.setAdapter(heroRecyclerAdapter);
    }

    public void find() {
        OkHttpClient client = new OkHttpClient();
        //setando o parse json
        Retrofit retrofit = new Retrofit
                .Builder()
                .client(client)
                .baseUrl("http://superheroapi.com/api/1736273063123549/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        //classe que implementa a interface que criamos  internet/Herorest
        HeroRest hp = retrofit.create(HeroRest.class);
        //objeto que faz a chamada dos metodos contidos na interface
        Call<HeroResponse> callColetor = hp.searchHero(getHeroName());
        callColetor.enqueue(new Callback<HeroResponse>() {

            @Override
            public void onResponse(Call<HeroResponse> call, Response<HeroResponse> response) {
                Log.d("teste", call.toString());
                HeroResponse heroResponse = response.body();
                assert heroResponse != null;
                heroRecyclerAdapter.addItems(heroResponse.heroes);
                // fecha o load quando o recycleadpter encontra o herói
                dialog.dismiss();

                if(heroResponse.heroes.isEmpty()){
                    new MaterialDialog.Builder(HeroSearchActivity.this)
                            .title(getResources().getString(R.string.MDattention))
                            .content(getResources().getString(R.string.MDheronotfound))
                            .positiveText("OK")
                            .show();
                }

            }

            @Override
            public void onFailure(Call<HeroResponse> call, Throwable t) {
                Log.d("teste", call.toString());
                // fecha o load quando o recycleadpter não encontra o herói
                dialog.dismiss();
                new MaterialDialog.Builder(HeroSearchActivity.this)
                        .title(getResources().getString(R.string.MDattention))
                        .content(getResources().getString(R.string.MDheronotfound))
                        .positiveText("OK")
                        .show();
            }
        });
    }

    private String getHeroName() {
        return heroName.getText().toString();
    }

    @Override
    public void onClickListener(Hero hero) {
        heroViewModel.insertHero(hero);
        finish();
    }

}
