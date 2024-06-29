package com.slyfly1.taxiconventionne77.calendrier;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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

public class GraphiqueDetail extends AppCompatActivity {
TextView testmoi,testanne;
    AlertDialog alertDialog;
    private SQLiteDatabase DB;
    Cursor cur;
    int amount;
    int un, dex, trios, quake, cine,six,sept, hui, nerf,dix, once, douse, treble, quantize, quince,seize, dissect, digit, diane, vang, vigour, vintner, venturous, vanguard, venting, vents, vincent, vengeful, vintners, tenet, rested;

    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelName;
    ArrayList<MonthDalesData>monthDalesDataArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphique_detail);
        barChart=findViewById(R.id.barChart);
        barEntryArrayList=new ArrayList<>();
        labelName=new ArrayList<>();

      Intent it = getIntent();
      final String MoisAnnee=it.getStringExtra("Moisannee");//mois en lettre
      final String MoiS=it.getStringExtra("Anne");//anne

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
    BarDataSet barDataSet = new BarDataSet(barEntryArrayList,"Chiffre Mensuel");
    barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    Description description = new Description();
    description.setText("Jours");
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


        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener()
        {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                int x =barChart.getData().getDataSetForEntry(e).getEntryIndex((BarEntry) e);
                String month =monthDalesDataArrayList.get(x).getMonth();
                String sales = NumberFormat.getCurrencyInstance().format(monthDalesDataArrayList.get(x).getSales());

                AlertDialog.Builder builder = new AlertDialog.Builder(GraphiqueDetail.this);
                builder.setCancelable(true);
                View nview= LayoutInflater.from(GraphiqueDetail.this).inflate(R.layout.monthly_sales_layout,null);
                TextView month_txt = nview.findViewById(R.id.month);
                TextView sales_txt= nview.findViewById(R.id.sales);
                TextView anne_txt=nview.findViewById(R.id.anne);
                month_txt.setText(month);//jours
                sales_txt.setText(MoiS);//année
                anne_txt.setText(MoisAnnee);//mois en lettre



             //   builder.setView(nview);
//alertDialog=builder.create();
//alertDialog.show();
              Intent intent= new Intent(GraphiqueDetail.this,GraphiqueDetailJours.class);
                intent.putExtra("Jours", month);
intent.putExtra("Annee",MoiS);
intent.putExtra("Mois",MoisAnnee);
intent.putExtra("Jours",month);
                startActivity(intent);


            }

            @Override
            public void onNothingSelected()
            {

            }
        });


    }


    private void fillMonthSales(){
        monthDalesDataArrayList.clear();
        monthDalesDataArrayList.add(new MonthDalesData("1",un));
        monthDalesDataArrayList.add(new MonthDalesData("2", dex));
        monthDalesDataArrayList.add(new MonthDalesData("3", trios));
        monthDalesDataArrayList.add(new MonthDalesData("4", quake));
        monthDalesDataArrayList.add(new MonthDalesData("5", cine));
        monthDalesDataArrayList.add(new MonthDalesData("6",six));
        monthDalesDataArrayList.add(new MonthDalesData("7",sept));
        monthDalesDataArrayList.add(new MonthDalesData("8", hui));
        monthDalesDataArrayList.add(new MonthDalesData("9", nerf));
        monthDalesDataArrayList.add(new MonthDalesData("10",dix));
        monthDalesDataArrayList.add(new MonthDalesData("11", once));
        monthDalesDataArrayList.add(new MonthDalesData("12", douse));
        monthDalesDataArrayList.add(new MonthDalesData("13", treble));
        monthDalesDataArrayList.add(new MonthDalesData("14", quantize));
        monthDalesDataArrayList.add(new MonthDalesData("15", quince));
        monthDalesDataArrayList.add(new MonthDalesData("16",seize));
        monthDalesDataArrayList.add(new MonthDalesData("17", dissect));
        monthDalesDataArrayList.add(new MonthDalesData("18", digit));
        monthDalesDataArrayList.add(new MonthDalesData("19", diane));
        monthDalesDataArrayList.add(new MonthDalesData("20", vang));
        monthDalesDataArrayList.add(new MonthDalesData("21", vigour));
        monthDalesDataArrayList.add(new MonthDalesData("22", vintner));
        monthDalesDataArrayList.add(new MonthDalesData("23", venturous));
        monthDalesDataArrayList.add(new MonthDalesData("24", vanguard));
        monthDalesDataArrayList.add(new MonthDalesData("25", venting));
        monthDalesDataArrayList.add(new MonthDalesData("26", vents));
        monthDalesDataArrayList.add(new MonthDalesData("27", vincent));
        monthDalesDataArrayList.add(new MonthDalesData("28", vengeful));
        monthDalesDataArrayList.add(new MonthDalesData("29", vintners));
        monthDalesDataArrayList.add(new MonthDalesData("30", tenet));
        monthDalesDataArrayList.add(new MonthDalesData("31",rested));
    }

    public void moisjanvier() {
        Intent it = getIntent();
        String MoiS=it.getStringExtra("Anne");//anne
        String month=it.getStringExtra("Moisannee");
        String supermmoi="";
        if (month.equals("Janvier")){
            supermmoi="01";
        }
        if (month.equals("Fevrier")){
            supermmoi="02";
        }
        if (month.equals("Mars")){
            supermmoi="03";
        }
        if (month.equals("Avril")) supermmoi = "04";
        if (month.equals("Mai")) supermmoi = "05";
        if (month.equals("Juin")) supermmoi = "06";
        if (month.equals("Juillet")) supermmoi = "07";
        if (month.equals("Aout")) supermmoi = "08";
        if (month.equals("Septembre")) supermmoi = "09";
        if (month.equals("Octobre")) supermmoi = "10";
        if (month.equals("Novembre")) supermmoi = "11";
        if (month.equals("Decembre")) supermmoi = "12";
        String startDate100;
        String endDate100;


        startDate100=MoiS+"-"+supermmoi+"-01";
        endDate100=MoiS+"-"+supermmoi+"-02";


        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate100 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate100 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        un=amount;

        cur.close();


        String startDate1;
        String endDate1;
        startDate1=MoiS+"-"+supermmoi+"-02";
        endDate1=MoiS+"-"+supermmoi+"-03";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate1 + "'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate1 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        dex =amount;

        cur.close();

        String startDate2;

String endDate2;
endDate2=MoiS+"-"+supermmoi+"-04";
        startDate2=MoiS+"-"+supermmoi+"-03";



        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate2 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate2+"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        trios =amount;
        cur.close();

        String startDate3;
        String endDate3;
        startDate3=MoiS+"-"+supermmoi+"-04";
endDate3=MoiS+"-"+supermmoi+"-05";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate3 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate3 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        quake =amount;
        cur.close();

        String startDate4;
        startDate4=MoiS+"-"+supermmoi+"-05";
String endDate4;
endDate4=MoiS+"-"+supermmoi+"-06";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate4 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate4 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        cine =amount;
        cur.close();

        String startDate5;
        startDate5=MoiS+"-"+supermmoi+"-06";
String endDate5=MoiS+"-"+supermmoi+"-07";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate5 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate5 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        six=amount;
        cur.close();

        String startDate6;
        startDate6=MoiS+"-"+supermmoi+"-07";
String endDate6;
endDate6=MoiS+"-"+supermmoi+"-08";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate6 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate6 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        sept=amount;
        cur.close();

        String startDate7;
        startDate7=MoiS+"-"+supermmoi+"-08";
String endDate7;
endDate7=MoiS+"-"+supermmoi+"-09";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate7 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate7  +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        hui =amount;
        cur.close();

        String startDate8;
        startDate8=MoiS+"-"+supermmoi+"-09";
String endDate8;
endDate8=MoiS+"-"+supermmoi+"-10";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate8 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate8 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        nerf =amount;
        cur.close();

        String startDate9;
        startDate9=MoiS+"-"+supermmoi+"-10";
String endDate9;
endDate9=MoiS+"-"+supermmoi+"-11";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate9 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate9+"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        dix=amount;
        cur.close();

        String startDate10;
        startDate10=MoiS+"-"+supermmoi+"-11";
String endDate10;
endDate10=MoiS+"-"+supermmoi+"-12";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate10  +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate10 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        once =amount;
        cur.close();

        String startDate11;
        startDate11=MoiS+"-"+supermmoi+"-12";
String endDate11;
endDate11=MoiS+"-"+supermmoi+"-13";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate11 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate11 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        douse =amount;
        cur.close();

        String startDate12;
        startDate12=MoiS+"-"+supermmoi+"-13";
String endDate12;
endDate12=MoiS+"-"+supermmoi+"-14";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate12 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate12 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        treble =amount;
        cur.close();

        String startDate13;
        startDate13=MoiS+"-"+supermmoi+"-14";
String endDate13;
endDate13=MoiS+"-"+supermmoi+"-15";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate13 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate13 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        quantize =amount;
        cur.close();

        String startDate15;
        startDate15=MoiS+"-"+supermmoi+"-15";
String endDate15;
endDate15=MoiS+"-"+supermmoi+"-16";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate15 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate15 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        quince =amount;
        cur.close();

        String startDate16;
        startDate16=MoiS+"-"+supermmoi+"-16";
String endDate16;
endDate16=MoiS+"-"+supermmoi+"-17";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate16 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate16 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        seize=amount;
        cur.close();

        String startDate17;
        startDate17=MoiS+"-"+supermmoi+"-17";
String endDate17;
endDate17=MoiS+"-"+supermmoi+"-18";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate17 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate17 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        dissect =amount;
        cur.close();

        String startDate18;
        startDate18=MoiS+"-"+supermmoi+"-18";
        String endDate18;
        endDate18=MoiS+"-"+supermmoi+"-19";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate18 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate18 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        digit =amount;
        cur.close();

        String startDate19;
        startDate19=MoiS+"-"+supermmoi+"-19";
        String endDate19;
        endDate19=MoiS+"-"+supermmoi+"-20";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate19 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate19 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        diane =amount;
        cur.close();

        String startDate20;
        startDate20=MoiS+"-"+supermmoi+"-20";
        String endDate20;
        endDate20=MoiS+"-"+supermmoi+"-21";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate20 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate20 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vang =amount;
        cur.close();

        String startDate21;
        startDate21=MoiS+"-"+supermmoi+"-21";
        String endDate21;
        endDate21=MoiS+"-"+supermmoi+"-22";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate21 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate21 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vigour =amount;
        cur.close();

        String startDate22;
        startDate22=MoiS+"-"+supermmoi+"-22";
        String endDate22;
        endDate22=MoiS+"-"+supermmoi+"-23";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate22 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate22 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vintner =amount;
        cur.close();

        String startDate23;
        startDate23=MoiS+"-"+supermmoi+"-23";
        String endDate23;
        endDate23=MoiS+"-"+supermmoi+"-24";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate23 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate23 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        venturous =amount;
        cur.close();


        String startDate24;
        startDate24=MoiS+"-"+supermmoi+"-24";
        String endDate24;
        endDate24=MoiS+"-"+supermmoi+"-25";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate24 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate24 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vanguard =amount;
        cur.close();

        String startDate25;
        startDate25=MoiS+"-"+supermmoi+"-25";
        String endDate25;
        endDate25=MoiS+"-"+supermmoi+"-26";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate25 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate25 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        venting =amount;
        cur.close();

        String startDate26;
        startDate26=MoiS+"-"+supermmoi+"-26";
        String endDate26;
        endDate26=MoiS+"-"+supermmoi+"-27";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate26 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate26 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vents =amount;
        cur.close();

        String startDate27;
        startDate27=MoiS+"-"+supermmoi+"-27";
        String endDate27;
        endDate27=MoiS+"-"+supermmoi+"-28";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate27 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate27 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vincent =amount;
        cur.close();

        String startDate28;
        startDate28=MoiS+"-"+supermmoi+"-28";
        String endDate28;
        endDate28=MoiS+"-"+supermmoi+"-29";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate28 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate28 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vengeful =amount;
        cur.close();

        String startDate29;
        startDate29=MoiS+"-"+supermmoi+"-29";
        String endDate29;
        endDate29=MoiS+"-"+supermmoi+"-30";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate29 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate29 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        vintners =amount;
        cur.close();

        String startDate30;
        startDate30=MoiS+"-"+supermmoi+"-30";
        String endDate30;
        endDate30=MoiS+"-"+supermmoi+"-31";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate30 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate30 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        tenet =amount;
        cur.close();

        String startDate31;
        startDate31=MoiS+"-"+supermmoi+"-31";
        String endDate31;
        endDate31=MoiS+"-"+supermmoi+"-32";

        cur = DB.rawQuery("select sum("+EventDB.Event.COLUMN_MONTANT +") AS Total from "+ EventDB.Event.TABLE_NAME +" WHERE "+ EventDB.Event.COLUMN_START
                +">='"+ startDate31 +"'AND "+ EventDB.Event.COLUMN_START+"<'"+ endDate31 +"';", null);
        if(cur.moveToFirst())

            amount = cur.getInt(0);

        else
            amount = -1;


        rested=amount;
        cur.close();
    }

}
