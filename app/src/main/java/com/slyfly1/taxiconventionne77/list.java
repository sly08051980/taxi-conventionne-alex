
package com.slyfly1.taxiconventionne77;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.SnapshotParser;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.firebase.ui.firestore.paging.LoadingState;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class list extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;
    private FirestorePagingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        firebaseFirestore=FirebaseFirestore.getInstance();
        mFirestoreList=findViewById(R.id.firestore_list);

        Query query=firebaseFirestore.collection("users")
                .whereNotEqualTo("chauffeurs","Alexandre")
                .whereEqualTo("valider","");

   PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(3)
                .build();

        FirestorePagingOptions<ProductModel> options =new FirestorePagingOptions.Builder<ProductModel>()
                .setLifecycleOwner(this)
                .setQuery(query, config, new SnapshotParser<ProductModel>() {
                    @NonNull
                    @Override
                    public ProductModel parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        ProductModel productModel=snapshot.toObject(ProductModel.class);
                        String itemID= snapshot.getId();
                        productModel.setItem_Id(itemID);
                        return productModel;
                    }


        })
                .build();

        adapter= new FirestorePagingAdapter<ProductModel, ProductViewHolder>(options) {

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom,parent,false);
                return new ProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductModel model) {
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

            @Override
            protected void onLoadingStateChanged(@NonNull LoadingState state) {
                super.onLoadingStateChanged(state);
                switch (state){
                    case LOADING_INITIAL:
                        Log.d("pacing_log","Initial");
                        break;
                    case LOADING_MORE:
                        Log.d("pacing_log","Next");
                        break;

                    case FINISHED:
                        Log.d("pacing_log","Loaded");
                        break;
                    case ERROR:
                        Log.d("pacing_log","error loading");
                        break;
                    case LOADED:
                        Log.d("pacing_log","Total item"+getItemCount());
                        break;
                }
            }
        };
        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);




    }

    private class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView listnom;
        private TextView listprenom;
        private TextView listtelephone;
        private TextView listadresse;
        private TextView listcodepostal;
        private TextView listville;
        private TextView listadresse2;
        private TextView listcodepostal2;
        private TextView listville2;
        private TextView listpersonne;
        private TextView listchauffeurs;



        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);




            listnom=itemView.findViewById(R.id.listnom);
            listprenom=itemView.findViewById(R.id.listprenom);
            listtelephone=itemView.findViewById(R.id.listtelephone);
            listadresse=itemView.findViewById(R.id.listadresse);
            listcodepostal=itemView.findViewById(R.id.listcodepostal);
            listville=itemView.findViewById(R.id.listville);
            listadresse2=itemView.findViewById(R.id.adress2);
            listcodepostal2=itemView.findViewById(R.id.listcodepostal2);
            listville2=itemView.findViewById(R.id.listville2);
            listpersonne=itemView.findViewById(R.id.listpersonne);
            listchauffeurs=itemView.findViewById(R.id.listchauffeur);
        }


    }




}
