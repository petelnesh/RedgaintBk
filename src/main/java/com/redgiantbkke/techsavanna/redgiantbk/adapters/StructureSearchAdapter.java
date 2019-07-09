package com.redgiantbkke.techsavanna.redgiantbk.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.redgiantbkke.techsavanna.redgiantbk.R;
import com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity;
import com.redgiantbkke.techsavanna.redgiantbk.methods.Structure;

import java.util.List;

import static com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity.STRUCTURE_COUNTRY;
import static com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity.STRUCTURE_ID;
import static com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity.STRUCTURE_NAME;
import static com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity.STRUCTURE_REGION;
import static com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity.STRUCTURE_REGISTATIONTYPE;
import static com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity.STRUCTURE_REGISTRATIONDATE;
import static com.redgiantbkke.techsavanna.redgiantbk.SingleStructureActivity.STRUCTURE_TELEPHONE;

public class StructureSearchAdapter extends  RecyclerView.Adapter<StructureSearchAdapter.StructureSearchHolder>  {
    private List<Structure> structureList;
    private Context contexts;
    private LayoutInflater mInflater;

//    SearchSiteFragment dialog;

    //    SiteSearchAdapter(SearchSiteFragment dialog) {
//        this.dialog = dialog;
//    }


    public StructureSearchAdapter(List<Structure> structureList, Context context) {
        this.structureList = structureList;
        this.contexts = context;
    }

    @Override
    public StructureSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_structure, parent, false);

        return new StructureSearchHolder(view);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void onBindViewHolder(StructureSearchHolder holder, final int position) {
            final Structure structure = structureList.get(position);
            System.out.println("Data detiles" + structure.getTelephone());
            // holder.cardView.set;
            holder.name.setText(structure.getStructure_name());
            holder.phone.setText(structure.getTelephone());
            holder.country.setText(structure.getCounty());
            holder.registerdate.setText(structure.getRegistrationdate());

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(contexts, SingleStructureActivity.class);

                        intent.putExtra(STRUCTURE_ID, structure.getId());
                        intent.putExtra(STRUCTURE_NAME, structure.getStructure_name());
                        intent.putExtra(STRUCTURE_REGISTRATIONDATE, structure.getRegistrationdate());
                        intent.putExtra(STRUCTURE_REGISTATIONTYPE, structure.getCounty());
                        intent.putExtra(STRUCTURE_REGION, structure.getRegion());
                        intent.putExtra(STRUCTURE_COUNTRY, structure.getCounty());
                        intent.putExtra(STRUCTURE_TELEPHONE,structure.getTelephone());

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        contexts.startActivity(intent);

                    }
                }
            });



    }


    @Override
    public int getItemCount() {
        return structureList.size();
    }

    public static class StructureSearchHolder extends RecyclerView.ViewHolder {
        TextView name, country, registerdate, phone;

        CardView cardView;
        public StructureSearchHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            country = itemView.findViewById(R.id.country);
            registerdate = itemView.findViewById(R.id.resdate);
            phone = itemView.findViewById(R.id.phone);
            cardView=itemView.findViewById(R.id.cardveiw);

        }
    }
}
