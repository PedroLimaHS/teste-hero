package br.com.unibratec.unibratecheros.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.unibratec.unibratecheros.R;
import br.com.unibratec.unibratecheros.model.Powerstats;


public class PowerFragment extends Fragment {
    private static final String ARG_PARAM1 = "powerstats";
    private Powerstats mPowerstats;

    public PowerFragment() {
    }

    public static PowerFragment newInstance(Powerstats powerstats) {
        PowerFragment fragment = new PowerFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, powerstats);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPowerstats = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate o layout para esse fragment
        View v = inflater.inflate(R.layout.fragment_power, container, false);
        loadView(v);
        return v;
    }

    private void loadView(View v){
        ((TextView)v.findViewById(R.id.txInteligence)).setText(mPowerstats.intelligence);
        ((TextView)v.findViewById(R.id.txstrength)).setText(mPowerstats.strength);
        ((TextView)v.findViewById(R.id.txspeed)).setText(mPowerstats.speed);
        ((TextView)v.findViewById(R.id.txdurability)).setText(mPowerstats.durability);
        ((TextView)v.findViewById(R.id.txpower)).setText(mPowerstats.power);
        ((TextView)v.findViewById(R.id.txcombat)).setText(mPowerstats.combat);
    }


}
