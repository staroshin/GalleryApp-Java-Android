package edu.ewubd.galleryapp;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;


import java.util.ArrayList;


public class MainActivity extends Activity {

    private GridView gridView;
    AdapterClass AdapterClass;
    ArrayList<ImageList> list;

    private String URL = "https://muthosoft.com/univ/photos/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        list = new ArrayList<>();

        httpRequest();
    }
    @SuppressLint("StaticFieldLeak")
    private void httpRequest(){
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... param){
                try{
                    String data = JSONParser.getInstance().makeHttpRequest(URL, "POST");
                    return data;
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String data) {
                super.onPostExecute(data);

                String[] splitByComma = data.split(",");
                ArrayList<ImageList> arrayList = new ArrayList<>();
                for (String a : splitByComma){
                    String[] splitByColon = a.split(":");

                    ImageList ImageList = new ImageList(splitByColon[0], splitByColon[1]);
                    arrayList.add(ImageList);
                }

                loadData(arrayList);
            }
        }.execute();
    }
    void loadData(ArrayList list)
    {

        AdapterClass = new AdapterClass(this,list);

        gridView.setAdapter(AdapterClass);

        AdapterClass.notifyDataSetChanged();

    }
}