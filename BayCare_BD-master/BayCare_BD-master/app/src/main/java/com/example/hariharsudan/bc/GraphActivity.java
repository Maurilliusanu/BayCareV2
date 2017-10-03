package com.example.hariharsudan.bc;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class GraphActivity extends Activity {
    LineChart chart;
    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;

    public GraphActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Resources r = getResources();

//        chart = (LineChart) findViewById(R.id.chart);
//       // SQLiteDatabase mydatabase = SQLiteDatabase.openDatabase("/data/data/com.example.tanay.sleepcyclemonitor/databases/sleep_data", null, MODE_PRIVATE);
//       // Cursor resultSet = mydatabase.rawQuery("Select * from sensor_data", null);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
//      //  resultSet.moveToFirst();
//        ArrayList<Entry> accelX =  new ArrayList<Entry>();
//        ArrayList<Entry> accelY =  new ArrayList<Entry>();
//        ArrayList<Entry> accelZ =  new ArrayList<Entry>();
//        ArrayList<String> xVals = new ArrayList<String>();
//        int j=0;
////        while(!resultSet.isAfterLast()){
////            Log.d("Time", sdf.format(new Date(resultSet.getLong(0))));
////
////            for(int i=1; i<4; i++){
////                Log.d("Accel",Float.toString(resultSet.getFloat(i)));
////            }
////
////            accelX.add(new Entry(resultSet.getFloat(1),j));
////            accelY.add(new Entry(resultSet.getFloat(2),j));
////            accelZ.add(new Entry(resultSet.getFloat(3),j));
////            xVals.add(Integer.toString(j));
////            j++;
////            resultSet.moveToNext();
////        }
//        LineDataSet Xset = new LineDataSet(accelX,"X Axis");
//        Xset.setAxisDependency(YAxis.AxisDependency.LEFT);
//        Xset.setColor(r.getColor(R.color.blue));
//        Xset.setLineWidth(3.0f);
//        LineDataSet Yset = new LineDataSet(accelY,"Y Axis");
//        Yset.setAxisDependency(YAxis.AxisDependency.LEFT);
//        Yset.setColor(r.getColor(R.color.pinkDark));
//        Yset.setLineWidth(3.0f);
//        LineDataSet Zset = new LineDataSet(accelZ,"Z Axis");
//        Zset.setAxisDependency(YAxis.AxisDependency.LEFT);
//        Zset.setColor(r.getColor(R.color.colorAccent));
//        Zset.setLineWidth(3.0f);
//
//        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
//        dataSets.add(Xset);
//        dataSets.add(Yset);
//        dataSets.add(Zset);
//        LineData data = new LineData(xVals, dataSets);
//        chart.setData(data);
//        chart.invalidate();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        // data
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(12);
        viewport.setScrollable(true);
     }


    @Override
    protected void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 100; i+=4) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }
            }
        }).start();
    }

    // add random data to graph
    private void addEntry() {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        series.appendData(new DataPoint(lastX++, RANDOM.nextDouble() * 10d), true, 10);
    }
}
