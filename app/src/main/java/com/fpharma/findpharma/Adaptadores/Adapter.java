package com.fpharma.findpharma.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fpharma.findpharma.Model.Pharma;
import com.fpharma.findpharma.R;
import com.fpharma.findpharma.Views.MapActivity;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Pharma> valores;
    private Context context;

    public Adapter(List<Pharma> input, Context context) {
    this.valores = input;
    this.context = context;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.template_recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(v, context);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pharma farmacia = valores.get(position);
        holder.txtNomeRemedio.setText(farmacia.getNomeFantasia());
        holder.txtNomeFarma.setText(farmacia.getTurnoAtendimento());
        holder.setFarmacia(farmacia);
    }

    @Override
    public int getItemCount() {
        return valores.size();
    }

    public void addAll(List<Pharma>lista){
        this.valores.addAll(lista);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtNomeRemedio;
        public TextView txtNomeFarma;
        public View layout;
        private Context context;
        private Pharma farmacia;


        public ViewHolder(View v, Context context){
            super(v);
            v.setOnClickListener(this);
            layout = v;
            txtNomeRemedio = (TextView) v.findViewById(R.id.txtNomeRemedio);
            txtNomeFarma = (TextView) v.findViewById(R.id.txtNomeFarma);
            this.context = context;
        }

        public void setFarmacia(Pharma farmacia) {
            this.farmacia = farmacia;
        }

        @Override
        public void onClick(View v) {
            if (farmacia != null){
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("farmacia", farmacia);
                context.startActivity(intent);
            }
        }
    }
}
