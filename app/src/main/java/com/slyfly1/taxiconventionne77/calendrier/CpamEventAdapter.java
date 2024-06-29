package com.slyfly1.taxiconventionne77.calendrier;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.slyfly1.taxiconventionne77.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CpamEventAdapter extends RecyclerView.Adapter<CpamEventAdapter.EventViewHolder>  {
    private Context mContext;
    private Cursor mCursor;
    private TextView eventNameTV, eventTimeTV,prenom,montant;
    private RelativeLayout parentLayout;
    private SQLiteDatabase mDatabase;
    private int ID;
    private String type;
    private String[] days = new String[]{"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi",
            "Vendredi","Samedi"};
    private String[] months = new String[]{"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};

    public CpamEventAdapter(Context context, Cursor cursor, String type) {
        mContext = context;
        mCursor = cursor;
        EventDBHelper dbHelper = new EventDBHelper(context);
        mDatabase = dbHelper.getWritableDatabase();
        this.type = type;
    }


    public class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameTV = itemView.findViewById(R.id.eventName);
            eventTimeTV = itemView.findViewById(R.id.date);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            prenom=itemView.findViewById(R.id.prenom);
            montant=itemView.findViewById(R.id.montant);
        }
    }

    @NonNull
    @Override
    public CpamEventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.activity_cpam_event_adapter, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CpamEventAdapter.EventViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }

        final String name = mCursor.getString(mCursor.getColumnIndex(EventDB.Event.COLUMN_NAME));
        final String startDate = mCursor.getString(mCursor.getColumnIndex(EventDB.Event.COLUMN_START));
        final String endDate = mCursor.getString(mCursor.getColumnIndex(EventDB.Event.COLUMN_END));
        final String superprenom=mCursor.getString(mCursor.getColumnIndex(EventDB.Event.COLUMN_PRENOM));
        final String supermontant=mCursor.getString(mCursor.getColumnIndex(EventDB.Event.COLUMN_MONTANT));
        long id=mCursor.getLong(mCursor.getColumnIndex(EventDB.Event.COLUMN_ID));
        ID = mCursor.getInt(mCursor.getColumnIndex(EventDB.Event.COLUMN_ID));

  holder.itemView.setTag(id);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date end = df.parse(endDate);
            Calendar c = Calendar.getInstance();
            Date currentDate = c.getTime();
            if (end != null && end.getTime() - currentDate.getTime() < 0) {
                eventNameTV.setTextColor(Color.parseColor("#BAA4CE"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        eventNameTV.setText(name);

        eventTimeTV.setText(startDate);
        montant.setText("Montant : "+supermontant+"€");
prenom.setText(superprenom);
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editEventIntent = new Intent(view.getContext(), EventDetailsActivity.class);
                editEventIntent.putExtra("EVENT NAME", name);
                editEventIntent.putExtra("START", startDate);
                editEventIntent.putExtra("END", endDate);
                view.getContext().startActivity(editEventIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }




}
