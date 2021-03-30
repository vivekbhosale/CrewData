package com.example.juststream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.juststream.Networks.Api;
import com.example.juststream.Room.Crew;
import com.example.juststream.Room.CrewDatabase;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Initialise variables
    Button refresh, delete;
    GridView gridView;
    CustomAdapter custom_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        refresh = findViewById(R.id.refresh);
        delete = findViewById(R.id.delete);

        //Set data from room database
        CrewDatabase crewDatabase = CrewDatabase.getINSTANCE(MainActivity.this);
        List<Crew> crew = crewDatabase.crewDao().getdata();
        custom_adapter = new CustomAdapter(crew, MainActivity.this);
        gridView.setAdapter(custom_adapter);

    }

    public void setRefresh(View view) {
        //Create background thread
        Background background = new Background();
        background.execute();
    }

    public void delete(View view){
        //delete room database
        CrewDatabase crewDatabase = CrewDatabase.getINSTANCE(MainActivity.this);
        List<Crew> crewList = crewDatabase.crewDao().getdata();
        crewDatabase.crewDao().delete(crewList);

        //update MainUI data
        List<Crew> crew = crewDatabase.crewDao().getdata();
        custom_adapter = new CustomAdapter(crew, MainActivity.this);
        gridView.setAdapter(custom_adapter);

    }

    public class Background extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            Call<List<Crew>> call = Api.getRetrofit().getdata();

            call.enqueue(new Callback<List<Crew>>() {
                @Override
                public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
                    for(Crew c: response.body()){
                        CrewDatabase crewDatabase = CrewDatabase.getINSTANCE(MainActivity.this);
                        Crew crew = new Crew();
                        crew.name=c.getNames();
                        crew.status=c.getStatus();
                        crew.wikipedia=c.getWikipedia();
                        crew.agency = c.getAgency();
                        crew.image = c.getImage();
                        crewDatabase.crewDao().insert(crew);
                    }

                    //pass response to adapter
                    custom_adapter = new CustomAdapter(response.body(), MainActivity.this);
                    //set adapter
                    gridView.setAdapter(custom_adapter);
                }

                @Override
                public void onFailure(Call<List<Crew>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Check internet connectivity",Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }
    }

    public class CustomAdapter extends BaseAdapter{
        private List<Crew> data;
        private Context context;

        public CustomAdapter(List<Crew> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context).inflate(R.layout.setcrew,null);
            TextView name = view.findViewById(R.id.name);
            TextView agency = view.findViewById(R.id.agency);
            TextView wikipedia = view.findViewById(R.id.wikipedia);
            TextView status = view.findViewById(R.id.status);
            ImageView image = view.findViewById(R.id.image);

            //add text to textviews
            name.setText("Name: "+data.get(position).getNames());
            agency.setText("Agency: "+data.get(position).getAgency());
            wikipedia.setText(data.get(position).getWikipedia());
            status.setText("Status: "+data.get(position).getStatus());

            //add image using glide
            Glide.with(context).load(data.get(position).getImage()).into(image);

            return view;
        }
    }
}
