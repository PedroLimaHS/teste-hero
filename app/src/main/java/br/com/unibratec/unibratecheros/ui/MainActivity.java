package br.com.unibratec.unibratecheros.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.unibratec.unibratecheros.R;
import br.com.unibratec.unibratecheros.model.Hero;
import br.com.unibratec.unibratecheros.ui.adapter.HeroRecyclerAdapter;
import br.com.unibratec.unibratecheros.ui.adapter.HeroRecyclerOnClick;
import br.com.unibratec.unibratecheros.viewmodel.HeroViewModel;

public class MainActivity extends AppCompatActivity implements HeroRecyclerOnClick {

    private HeroRecyclerAdapter heroRecyclerAdapter;
    private HeroViewModel heroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heroViewModel = ViewModelProviders.of(this).get(HeroViewModel.class);

        HeroViewModel heroViewModel = ViewModelProviders.of(this).get(HeroViewModel.class);
        loadView();

        heroViewModel.getHeros().observe(MainActivity.this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(@Nullable List<Hero> os) {
                heroRecyclerAdapter.addItems(os);
            }
        });

    }

    private void loadView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        heroRecyclerAdapter = new HeroRecyclerAdapter(new ArrayList<Hero>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(heroRecyclerAdapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(this, HeroSearchActivity.class));
        });
    }


    @Override
    public void onClickListener(Hero hero) {
        Intent i = new Intent(this, HeroActivity.class);
        i.putExtra("hero", hero);
        startActivity(i);
    }
}
