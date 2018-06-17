package br.com.unibratec.unibratecheros.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

import br.com.unibratec.unibratecheros.R;
import br.com.unibratec.unibratecheros.model.Hero;
import br.com.unibratec.unibratecheros.ui.adapter.ViewPagerAdapter;
import br.com.unibratec.unibratecheros.ui.fragments.HeroFragment;
import br.com.unibratec.unibratecheros.ui.fragments.PowerFragment;
import br.com.unibratec.unibratecheros.viewmodel.HeroViewModel;

public class HeroActivity extends AppCompatActivity {

    private Hero mHero;
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar tb;
    HeroViewModel heroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        heroViewModel = ViewModelProviders.of(this).get(HeroViewModel.class);
        loadHero();
        loadView();

    }

    private void loadView() {
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        tb = findViewById(R.id.toolbar);

        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(tb);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        tb.setTitle(mHero.name);

        setupViewPager(viewPager);
    }

    private void loadHero() {
        mHero = Objects.requireNonNull(getIntent().getExtras()).getParcelable("hero");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.hero_menu, menu);

        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HeroFragment.newInstance(mHero), getString(R.string.hero));
        adapter.addFragment(PowerFragment.newInstance(mHero.powerstats), getString(R.string.status));
        adapter.setOnlyIcons(false);
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }else if (id == R.id.remove_hero){
            heroViewModel.removeHero(mHero);
            finish();
        }
        return true;
    }
}
