package br.com.unibratec.unibratecheros.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.unibratec.unibratecheros.R;
import br.com.unibratec.unibratecheros.model.Hero;

public class HeroFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private Hero mHero;

    public HeroFragment() {
    }

    public static HeroFragment newInstance(Hero hero) {
        HeroFragment fragment = new HeroFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, hero);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHero = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hero, container, false);
        loadView(v);
        return v;
    }

    public void loadView(View v){
        TextView name = v.findViewById(R.id.heroName);
        ((TextView)v.findViewById(R.id.txfullname)).setText(mHero.biography.fullName);
        ((TextView)v.findViewById(R.id.txplaceOfBirth)).setText(mHero.biography.placeOfBirth);
        ((TextView)v.findViewById(R.id.txpfirst)).setText(mHero.biography.firstAppearance);
        ImageView imo = v.findViewById(R.id.imageFragment);
        name.setText(mHero.name);
        Picasso.get()
                .load(mHero.imagem)
                .placeholder(R.drawable.imagenull)
                .into(imo);

    }

}
