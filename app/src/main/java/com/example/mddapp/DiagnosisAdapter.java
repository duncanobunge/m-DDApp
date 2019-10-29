package com.example.mddapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiagnosisAdapter extends RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewHolder>
implements Filterable {

     private Context context;
     private List<Diagnosis> diagnosisList;
     private List<Diagnosis> filterdiagnosisList;
     private DiagnosisAdapterListener listener;

    public DiagnosisAdapter(Context context, List<Diagnosis> diagnosisList, List<Diagnosis> filterdiagnosisList, DiagnosisAdapterListener listener) {
        this.context = context;
        this.diagnosisList = diagnosisList;
        this.filterdiagnosisList = filterdiagnosisList;
        this.listener = listener;
    }

    public DiagnosisAdapter(Context context, List<Diagnosis> diagnosisList) {
        this.context = context;
        this.diagnosisList = diagnosisList;
    }

    @NonNull
    @Override
    public DiagnosisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_layout,null);
        return new DiagnosisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosisViewHolder holder, int position) {
         Diagnosis diagnosis = diagnosisList.get(position);
         holder.textViewDiagnosisID.setText(String.valueOf(diagnosis.getDiagnosis_id()));
         holder.textViewProfName.setText(diagnosis.getProfname());
    }

    @Override
    public int getItemCount() {
        return diagnosisList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterdiagnosisList = diagnosisList;
                } else {
                    List<Diagnosis> filteredList = new ArrayList<>();
                    for (Diagnosis row : diagnosisList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getProfname().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filterdiagnosisList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterdiagnosisList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterdiagnosisList = (ArrayList<Diagnosis>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    class DiagnosisViewHolder extends RecyclerView.ViewHolder{
          TextView textViewProfName, textViewDiagnosisID;
        public DiagnosisViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDiagnosisID = itemView.findViewById(R.id.textViewDiagnosisID);
            textViewProfName = itemView.findViewById(R.id.textViewProfName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected diagnosis in callback
                    listener.onContactSelected(filterdiagnosisList.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface DiagnosisAdapterListener {
        void onContactSelected(Diagnosis contact);
    }



}
