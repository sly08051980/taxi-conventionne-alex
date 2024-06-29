package com.slyfly1.taxiconventionne77;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
public class NoteAdapter extends FirestoreRecyclerAdapter<Note1, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note1> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Note1 model) {
        holder.listnom.setText(model.getNom());
        holder.listprenom.setText(model.getPrenom());
        holder.listtelephone.setText(model.getTelephone());
        holder.listadresse.setText(model.getAdresse());
        holder.listcodepostal.setText(model.getCodepostal());
        holder.listville.setText(model.getVille());
        holder.listadresse2.setText(model.getAdresse2());
        holder.listcodepostal2.setText(model.getCodepostal2());
        holder.listville2.setText(model.getVille2());
        holder.listpersonne.setText(model.getPersonne());
        holder.listchauffeurs.setText(model.getChauffeurs());
    }
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent, false);
        return new NoteHolder(v);
    }
    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }
    class NoteHolder extends RecyclerView.ViewHolder {
        TextView listnom;
        TextView listprenom;
        TextView listtelephone;
       TextView listadresse;
        TextView listcodepostal;
        TextView listville;
        TextView listadresse2;
        TextView listcodepostal2;
       TextView listville2;
        TextView listpersonne;
        TextView listchauffeurs;

        public NoteHolder(View itemView) {
            super(itemView);
            listnom = itemView.findViewById(R.id.listnom);
            listprenom = itemView.findViewById(R.id.listprenom);
            listtelephone = itemView.findViewById(R.id.listtelephone);
            listadresse = itemView.findViewById(R.id.listadresse);
            listcodepostal = itemView.findViewById(R.id.listcodepostal);
            listville = itemView.findViewById(R.id.listville);
            listadresse2 = itemView.findViewById(R.id.adress2);
            listcodepostal2 = itemView.findViewById(R.id.listcodepostal2);
            listville2 = itemView.findViewById(R.id.listville2);
            listpersonne = itemView.findViewById(R.id.listpersonne);
            listchauffeurs = itemView.findViewById(R.id.listchauffeur);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);

                    }


                }

            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}