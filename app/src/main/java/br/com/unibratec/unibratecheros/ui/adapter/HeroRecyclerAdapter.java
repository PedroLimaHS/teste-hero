package br.com.unibratec.unibratecheros.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.unibratec.unibratecheros.R;
import br.com.unibratec.unibratecheros.model.Hero;

public class HeroRecyclerAdapter extends RecyclerView.Adapter<HeroRecyclerAdapter.RecyclerViewHolder> {

    private List<Hero> mList;
    private HeroRecyclerOnClick mClick;

    public HeroRecyclerAdapter(List<Hero> list, HeroRecyclerOnClick mClick) {
        this.mList = list;
        this.mClick = mClick;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hero_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        Hero hero = mList.get(position);

        //parametros para os text view do CardView de hero item
        holder.name.setText(hero.name);
        holder.gender.setText(hero.appearance.gender);
        holder.race.setText(hero.appearance.race);
        holder.publisher.setText(hero.biography.publisher);


        Picasso.get()
                .load(hero.imagem)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addItems(List<Hero> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void clearData() {
        int size = this.mList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mList.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView gender;
        private TextView publisher;
        private TextView race;
        private ImageView image;

        RecyclerViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            name = view.findViewById(R.id.name);
            gender = view.findViewById(R.id.gender);
            publisher = view.findViewById(R.id.publisher);
            race = view.findViewById(R.id.race);
            image = view.findViewById(R.id.image);

        }

        @Override
        public void onClick(View v) {
            if (mClick != null) {
                mClick.onClickListener( mList.get(getLayoutPosition()));
            }
        }
    }
}

