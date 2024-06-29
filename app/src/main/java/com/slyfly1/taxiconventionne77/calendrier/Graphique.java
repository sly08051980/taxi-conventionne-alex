package com.slyfly1.taxiconventionne77.calendrier;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.slyfly1.taxiconventionne77.MonthDalesData;
import com.slyfly1.taxiconventionne77.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Graphique extends AppCompatActivity {

    Button button,button2,button3;
    String spin;
Spinner spinnerannee;
    private SQLiteDatabase DB;
    Cursor cur,cur1;
    int amount,amount1;
    int janvier,fevrier,mars,avril,mai,juin,juillet,aout,septembre,octobre,novembre,decembre,annee;
TextView datespinner,test;
    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelName;
ArrayList<MonthDalesData>monthDalesDataArrayList=new ArrayList<>();
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphique);
        barChart=findViewById(R.id.barChart);
        barEntryArrayList=new ArrayList<>();
        labelName=new ArrayList<>();
spinnerannee=findViewById(R.id.spinnerannee);


        EventDBHelper dbHelper = new EventDBHelper(this);
        DB = dbHelper.getWritableDatabase();

        ArrayList<String> area = new ArrayList<>();
//add values in area arrayList
        spinnerannee.setAdapter(new ArrayAdapter<String>(this
                , android.R.layout.simple_list_item_1, area));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.annee, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerannee.setAdapter(adapter);


        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
               int x =barChart.getData().getDataSetForEntry(e).getEntryIndex((BarEntry) e);
String month =monthDalesDataArrayList.get(x).getMonth();
String sales = NumberFormat.getCurrencyInstance().format(monthDalesDataArrayList.get(x).getSales());
String anne=spinnerannee.getSelectedItem().toString();
AlertDialog.Builder builder = new AlertDialog.Builder(Graphique.this);
builder.setCancelable(true);
View nview= LayoutInflater.from(Graphique.this).inflate(R.layout.monthly_sales_layout,null);
TextView month_txt = nview.findViewById(R.id.month);
TextView sales_txt= nview.findViewById(R.id.sales);
TextView anne_txt=nview.findViewById(R.id.anne);
month_txt.setText(month);
sales_txt.setText(sales);
anne_txt.setText(anne);
builder.setView(nview);
//alertDialog=builder.create();
//alertDialog.show();
                Intent intent= new Intent(Graphique.this,GraphiqueDetail.class);
                intent.putExtra("Moisannee", month);
                intent.putExtra("Anne", anne);
                startActivity(intent);


            }

            @Override
            public void onNothingSelected()
            {

            }
        });


        spinnerannee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int it, long l) {

                spin= spinnerannee.getSelectedItem().toString();

                if (spin.equals("2021")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    moisjanvier();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2022")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2022();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2023")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2023();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2024")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2024();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2025")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2025();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2026")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2026();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2027")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2027();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2028")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2028();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2029")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2029();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
                if (spin.equals("2030")){

                    ArrayList<String> area = new ArrayList<>();
//add values in area arrayList


                    barEntryArrayList=new ArrayList<>();

                    mois2030();

                    fillMonthSales();




                    for (int i =0;i<monthDalesDataArrayList.size();i ++){
                        String month = monthDalesDataArrayList.get(i).getMonth();
                        int sales = monthDalesDataArrayList.get(i).getSales();
                        barEntryArrayList.add(new BarEntry(i,sales));
                        labelName.add(month);
                    }
                    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Annuel");
                    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    Description description = new Description();
                    description.setText("Mois");
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
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    private void fillMonthSales(){
        monthDalesDataArrayList.clear();
        monthDalesDataArrayList.add(new MonthDalesData("Janvier",janvier));
        monthDalesDataArrayList.add(new MonthDalesData("Fevrier",fevrier));
        monthDalesDataArrayList.add(new MonthDalesData("Mars",mars));
        monthDalesDataArrayList.add(new MonthDalesData("Avril",avril));
        monthDalesDataArrayList.add(new MonthDalesData("Mai",mai));
        monthDalesDataArrayList.add(new MonthDalesData("Juin",juin));
        monthDalesDataArrayList.add(new MonthDalesData("Juillet",juillet));
        monthDalesDataArrayList.add(new MonthDalesData("Aout",aout));
        monthDalesDataArrayList.add(new MonthDalesData("Septembre",septembre));
        monthDalesDataArrayList.add(new MonthDalesData("Octobre",octobre));
        monthDalesDataArrayList.add(new MonthDalesData("Novembre",novembre));
        monthDalesDataArrayList.add(new MonthDalesData("Decembre",decembre));
    }

    public void moisjanvier() {


            String startDate,startEnd;
            startDate="2021-01-01";
            startEnd="2021-01-31";

            cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                    +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
            if(cur.moveToFirst())

                amount = cur.getInt(0);

            else
                amount = -1;


            janvier=amount;
            cur.close();


        String startDate1,startEnd1;
        startDate1="2021-02-01";
        startEnd1="2021-02-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        fevrier=amount;
        cur.close();

        String startDate2,startEnd2;
        startDate2="2021-03-01";
        startEnd2="2021-03-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        mars=amount;
        cur.close();

        String startDate3,startEnd3;
        startDate3="2021-04-01";
        startEnd3="2021-04-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        avril=amount;
        cur.close();

        String startDate4,startEnd4;
        startDate4="2021-05-01";
        startEnd4="2021-05-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        mai=amount;
        cur.close();

        String startDate5,startEnd5;
        startDate5="2021-06-01";
        startEnd5="2021-06-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        juin=amount;
        cur.close();

        String startDate6,startEnd6;
        startDate6="2021-07-01";
        startEnd6="2021-07-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        juillet=amount;
        cur.close();

        String startDate7,startEnd7;
        startDate7="2021-08-01";
        startEnd7="2021-08-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        aout=amount;
        cur.close();

        String startDate8,startEnd8;
        startDate8="2021-09-01";
        startEnd8="2021-09-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        septembre=amount;
        cur.close();

        String startDate9,startEnd9;
        startDate9="2021-10-01";
        startEnd9="2021-10-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        octobre=amount;
        cur.close();

        String startDate10,startEnd10;
        startDate10="2021-11-01";
        startEnd10="2021-11-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        novembre=amount;
        cur.close();

        String startDate11,startEnd11;
        startDate11="2021-12-01";
        startEnd11="2021-12-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        decembre=amount;
        cur.close();
    }

    public void mois2022() {


        String startDate,startEnd;
        startDate="2022-01-01";
        startEnd="2022-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2022-02-01";
        startEnd1="2022-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2022-03-01";
        startEnd2="2022-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2022-04-01";
        startEnd3="2022-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2022-05-01";
        startEnd4="2022-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2022-06-01";
        startEnd5="2022-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2022-07-01";
        startEnd6="2022-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2022-08-01";
        startEnd7="2022-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2022-09-01";
        startEnd8="2022-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2022-10-01";
        startEnd9="2022-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2022-11-01";
        startEnd10="2022-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2022-12-01";
        startEnd11="2022-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }
    public void mois2023() {


        String startDate,startEnd;
        startDate="2023-01-01";
        startEnd="2023-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2023-02-01";
        startEnd1="2023-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2023-03-01";
        startEnd2="2023-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2023-04-01";
        startEnd3="2023-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2023-05-01";
        startEnd4="2023-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2023-06-01";
        startEnd5="2023-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2023-07-01";
        startEnd6="2023-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2023-08-01";
        startEnd7="2023-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2023-09-01";
        startEnd8="2023-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2023-10-01";
        startEnd9="2023-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2023-11-01";
        startEnd10="2023-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2023-12-01";
        startEnd11="2023-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }

    public void mois2024() {


        String startDate,startEnd;
        startDate="2024-01-01";
        startEnd="2024-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2024-02-01";
        startEnd1="2024-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2024-03-01";
        startEnd2="2024-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2024-04-01";
        startEnd3="2024-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2024-05-01";
        startEnd4="2024-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2024-06-01";
        startEnd5="2024-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2024-07-01";
        startEnd6="2024-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2024-08-01";
        startEnd7="2024-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2024-09-01";
        startEnd8="2024-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2024-10-01";
        startEnd9="2024-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2024-11-01";
        startEnd10="2024-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2024-12-01";
        startEnd11="2024-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }
    public void mois2025() {


        String startDate,startEnd;
        startDate="2025-01-01";
        startEnd="2025-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2025-02-01";
        startEnd1="2025-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2025-03-01";
        startEnd2="2025-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2025-04-01";
        startEnd3="2025-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2025-05-01";
        startEnd4="2025-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2025-06-01";
        startEnd5="2025-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2025-07-01";
        startEnd6="2025-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2025-08-01";
        startEnd7="2025-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2025-09-01";
        startEnd8="2025-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2025-10-01";
        startEnd9="2025-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2025-11-01";
        startEnd10="2025-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2025-12-01";
        startEnd11="2025-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }
    public void mois2026() {


        String startDate,startEnd;
        startDate="2026-01-01";
        startEnd="2026-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2026-02-01";
        startEnd1="2026-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2026-03-01";
        startEnd2="2026-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2026-04-01";
        startEnd3="2026-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2026-05-01";
        startEnd4="2026-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2026-06-01";
        startEnd5="2026-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2026-07-01";
        startEnd6="2026-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2026-08-01";
        startEnd7="2026-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2026-09-01";
        startEnd8="2026-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2026-10-01";
        startEnd9="2026-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2026-11-01";
        startEnd10="2026-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2026-12-01";
        startEnd11="2026-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }
    public void mois2027() {


        String startDate,startEnd;
        startDate="2027-01-01";
        startEnd="2027-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2027-02-01";
        startEnd1="2027-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2027-03-01";
        startEnd2="2027-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2027-04-01";
        startEnd3="2027-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2027-05-01";
        startEnd4="2027-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2027-06-01";
        startEnd5="2027-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2027-07-01";
        startEnd6="2027-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2027-08-01";
        startEnd7="2027-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2027-09-01";
        startEnd8="2027-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2027-10-01";
        startEnd9="2027-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2027-11-01";
        startEnd10="2027-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2027-12-01";
        startEnd11="2027-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }
    public void mois2028() {


        String startDate,startEnd;
        startDate="2028-01-01";
        startEnd="2028-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2028-02-01";
        startEnd1="2028-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2028-03-01";
        startEnd2="2028-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2028-04-01";
        startEnd3="2028-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2028-05-01";
        startEnd4="2028-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2028-06-01";
        startEnd5="2028-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2028-07-01";
        startEnd6="2028-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2028-08-01";
        startEnd7="2028-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2028-09-01";
        startEnd8="2028-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2028-10-01";
        startEnd9="2028-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2028-11-01";
        startEnd10="2028-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2028-12-01";
        startEnd11="2028-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }
    public void mois2029() {


        String startDate,startEnd;
        startDate="2029-01-01";
        startEnd="2029-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2029-02-01";
        startEnd1="2029-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2029-03-01";
        startEnd2="2029-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2029-04-01";
        startEnd3="2029-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2029-05-01";
        startEnd4="2029-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2029-06-01";
        startEnd5="2029-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2029-07-01";
        startEnd6="2029-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2029-08-01";
        startEnd7="2029-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2029-09-01";
        startEnd8="2029-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2029-10-01";
        startEnd9="2029-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2029-11-01";
        startEnd10="2029-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2029-12-01";
        startEnd11="2029-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }
    public void mois2030() {


        String startDate,startEnd;
        startDate="2030-01-01";
        startEnd="2030-01-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        janvier=amount1;
        cur1.close();


        String startDate1,startEnd1;
        startDate1="2030-02-01";
        startEnd1="2030-02-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd1 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        fevrier=amount1;
        cur1.close();

        String startDate2,startEnd2;
        startDate2="2030-03-01";
        startEnd2="2030-03-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd2 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mars=amount1;
        cur1.close();

        String startDate3,startEnd3;
        startDate3="2030-04-01";
        startEnd3="2030-04-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd3 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        avril=amount1;
        cur1.close();

        String startDate4,startEnd4;
        startDate4="2030-05-01";
        startEnd4="2030-05-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd4 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        mai=amount1;
        cur1.close();

        String startDate5,startEnd5;
        startDate5="2030-06-01";
        startEnd5="2030-06-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd5 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juin=amount1;
        cur1.close();

        String startDate6,startEnd6;
        startDate6="2030-07-01";
        startEnd6="2030-07-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd6 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        juillet=amount1;
        cur1.close();

        String startDate7,startEnd7;
        startDate7="2030-08-01";
        startEnd7="2030-08-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd7 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        aout=amount1;
        cur1.close();

        String startDate8,startEnd8;
        startDate8="2030-09-01";
        startEnd8="2030-09-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd8 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        septembre=amount1;
        cur1.close();

        String startDate9,startEnd9;
        startDate9="2030-10-01";
        startEnd9="2030-10-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd9 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        octobre=amount1;
        cur1.close();

        String startDate10,startEnd10;
        startDate10="2030-11-01";
        startEnd10="2030-11-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd10 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        novembre=amount1;
        cur1.close();

        String startDate11,startEnd11;
        startDate11="2030-12-01";
        startEnd11="2030-12-31";

        cur1 = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<='"+ startEnd11 +"';", null);
        if(cur1.moveToFirst())

            amount1 = cur1.getInt(0);

        else
            amount1 = -1;


        decembre=amount1;
        cur1.close();
    }

}
