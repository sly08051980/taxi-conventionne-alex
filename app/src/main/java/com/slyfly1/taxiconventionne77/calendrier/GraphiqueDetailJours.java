package com.slyfly1.taxiconventionne77.calendrier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.slyfly1.taxiconventionne77.MonthDalesData;
import com.slyfly1.taxiconventionne77.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphiqueDetailJours extends AppCompatActivity {


    private SQLiteDatabase DB;
    Cursor cur;
    int amount;
    int un, dex, trios, quake;

    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelName;
    ArrayList<MonthDalesData>monthDalesDataArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphique_detail_jours);
        barChart=findViewById(R.id.barChart);
        barEntryArrayList=new ArrayList<>();
        labelName=new ArrayList<>();

        Intent it = getIntent();
        final String Annee=it.getStringExtra("Annee");//mois en lettre
        final String Mois=it.getStringExtra("Mois");//anne
        final String Jours=it.getStringExtra("Jours");//anne


        EventDBHelper dbHelper = new EventDBHelper(this);
        DB = dbHelper.getWritableDatabase();

//add values in area arrayList



//add values in area arrayList


        barEntryArrayList=new ArrayList<>();

        ArrayList<String> area = new ArrayList<>();
        moisjanvier();

        fillMonthSales();


        for (int i =0;i<monthDalesDataArrayList.size();i ++){
            String month = monthDalesDataArrayList.get(i).getMonth();
            int sales = monthDalesDataArrayList.get(i).getSales();
            barEntryArrayList.add(new BarEntry(i,sales));
            labelName.add(month);
        }
        String dates=Jours+"-"+Mois+"-"+Annee;
        BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Mensuel");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText(dates);

        barChart.setDescription(description);
        BarData barData=new BarData(barDataSet);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelName));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelName.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();





    }


    private void fillMonthSales(){
        monthDalesDataArrayList.clear();
        monthDalesDataArrayList.add(new MonthDalesData("CPAM",un));
        monthDalesDataArrayList.add(new MonthDalesData("Chèque", dex));
        monthDalesDataArrayList.add(new MonthDalesData("Espèce", trios));
        monthDalesDataArrayList.add(new MonthDalesData("CB", quake));

    }

    public void moisjanvier() {
        Intent it = getIntent();
        final String Annee=it.getStringExtra("Annee");//mois en lettre
        final String Mois=it.getStringExtra("Mois");//anne
        final String Jours=it.getStringExtra("Jours");//anne
        String supermmoi="";
        if (Mois.equals("Janvier")){
            supermmoi="01";
        }
        if (Mois.equals("Fevrier")){
            supermmoi="02";
        }
        if (Mois.equals("Mars")){
            supermmoi="03";
        }
        if (Mois.equals("Avril")) supermmoi = "04";
        if (Mois.equals("Mai")) supermmoi = "05";
        if (Mois.equals("Juin")) supermmoi = "06";
        if (Mois.equals("Juillet")) supermmoi = "07";
        if (Mois.equals("Aout")) supermmoi = "08";
        if (Mois.equals("Septembre")) supermmoi = "09";
        if (Mois.equals("Octobre")) supermmoi = "10";
        if (Mois.equals("Novembre")) supermmoi = "11";
        if (Mois.equals("Decembre")) supermmoi = "12";
        String startDate100;
        String endDate100;
        String superjours = null;
if(Jours.equals("1")){
    superjours="01";
}
else if(Jours.equals("2")){
            superjours="02";
        }
        else if(Jours.equals("3")){
            superjours="03";
        }
        else if(Jours.equals("4")){
            superjours="04";
        }
        else   if(Jours.equals("5")){
            superjours="05";
        }
        else    if(Jours.equals("6")){
            superjours="06";
        }
        else if(Jours.equals("7")){
            superjours="07";
        }
        else  if(Jours.equals("8")){
            superjours="08";
        }
      else  if(Jours.equals("9")){
            superjours="09";
        }else{
            superjours=Jours;
        }



        startDate100=Annee+"-"+supermmoi+"-"+superjours;

        int Jours12=Integer.parseInt(superjours)+01;
        String Jours1=Jours12+"";
        String superjours1=null ;
        if(Jours1.equals("1")){
            superjours1="01";
        }else
        if(Jours1.equals("2")){
            superjours1="02";
        }else
        if(Jours1.equals("3")){
            superjours1="03";
        }else
        if(Jours1.equals("4")){
            superjours1="04";
        }else
        if(Jours1.equals("5")){
            superjours1="05";
        }else
        if(Jours1.equals("6")){
            superjours1="06";
        }else
        if(Jours1.equals("7")){
            superjours1="07";
        }else
        if(Jours1.equals("8")){
            superjours1="08";
        }else
        if(Jours1.equals("9")){
            superjours1="09";
        }else {
            superjours1=Jours12+"";
        }

String CPAM="CPAM";
        endDate100=Annee+"-"+supermmoi+"-"+superjours1;


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate100 +"'AND "+EventDB.Event.COLUMN_MODEPAYEMENT+"='"+CPAM+"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate100 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        un=amount;

        cur.close();


        String Cheque="Chèque";


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate100 +"'AND "+EventDB.Event.COLUMN_MODEPAYEMENT+"='"+Cheque+ "'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate100 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        dex =amount;

        cur.close();


        String Espece="Espèce";



        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate100 +"'AND "+EventDB.Event.COLUMN_MODEPAYEMENT+"='"+Espece+"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate100+"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        trios =amount;
        cur.close();

        String CB="CB";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate100 +"'AND "+EventDB.Event.COLUMN_MODEPAYEMENT+"='"+CB+"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate100 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        quake =amount;
        cur.close();


    }

}
