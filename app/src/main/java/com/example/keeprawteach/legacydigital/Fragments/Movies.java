package com.example.keeprawteach.legacydigital.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.legacydigital.Address.Ip;
import com.example.keeprawteach.legacydigital.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Movies extends Fragment {


    SwipeRefreshLayout swipeRefreshLayout;

    ListView listView;

    public Movies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_movies, container, false);

        listView=(ListView)view.findViewById(R.id.list);

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.Swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });

        load();
        return view;
    }

    private void load() {

        DownloadBookings downloader = new DownloadBookings(getContext(), listView);
        downloader.execute();
    }



    public class DownloadBookings extends AsyncTask<Void, Void, String> {
        Context context;
        ListView listView;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;

        public DownloadBookings(Context context, ListView listView) {
            this.context = context;
            this.listView = listView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(Void... params) {
            return DownloadData();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                ParserData parserData = new ParserData(context, listView,s);
                parserData.execute(s);
            } else {
                Toast.makeText(context, "Error Encountered\nCan't reach the Server", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(context, "Cancelled by user", Toast.LENGTH_LONG).show();

        }

        public String DownloadData() {
            stringBuilder = new StringBuilder();
            String line;
            httpURLConnection = new GetConnected().GetConnected();
            try {
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setConnectTimeout(120000);
                httpURLConnection.connect();

                bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(httpURLConnection.getInputStream())));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                httpURLConnection.disconnect();
            } catch (ProtocolException e) {
                return null;
            } catch (IOException e) {
                return null;
            }

            return stringBuilder.toString();
        }

        protected class GetConnected {
            URL url;
            HttpURLConnection httpU;
            String sign_url = new Ip(context).movies();

            public HttpURLConnection GetConnected() {
                if (sign_url != null) {
                    try {
                        url = new URL(sign_url);
                        httpU = (HttpURLConnection) url.openConnection();
                    } catch (MalformedURLException e) {
                        return null;
                    } catch (IOException e) {
                        return null;
                    }
                    return httpU;
                }
                return null;
            }


        }

        private class ParserData extends AsyncTask<String, Void, Boolean> {
            Context context;
            ListView listView;
            String jsondata;
            AdapterClass adapterClass;
            ArrayList<Spacecraft> arrayList = new ArrayList<>();

            public ParserData(Context context, ListView listView, String jsondata) {
                this.context = context;
                this.listView = listView;
                this.jsondata = jsondata;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Boolean doInBackground(String... params) {
                jsondata = params[0];
                return ParseJsonDt();
            }


            @Override
            protected void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);
                swipeRefreshLayout.setRefreshing(false);

                if (bool) {
                    adapterClass = new AdapterClass(arrayList, context, listView);
                    listView.setAdapter(adapterClass);
                } else {
                    Toast.makeText(context, "Encountered Error During processing", Toast.LENGTH_LONG).show();
                }
            }


            protected Boolean ParseJsonDt() {
                // jsondata=data;
                boolean vall = false;
                try {
                    JSONArray jsonArray = new JSONArray(jsondata);
                    JSONObject jsonObject = null;
                    int i = 0;
                    Spacecraft spacecraft;
                    while (i < jsonArray.length()) {

                        jsonObject = jsonArray.getJSONObject(i);
                        spacecraft = new Spacecraft();
                        spacecraft.setName(jsonObject.getString("name"));
                        spacecraft.setId(jsonObject.getString("movieid"));
                        spacecraft.setLocation(jsonObject.getString("initial"));
                        arrayList.add(spacecraft);
                        i++;
                    }
                    vall = true;
                } catch (JSONException e) {

                }
                return vall;
            }

        }

        private class Spacecraft {
            public String name, id, contact, code, county, subcounty, location;

            public Spacecraft() {
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }
        }

        private class AdapterClass extends BaseAdapter {
            ArrayList<Spacecraft> spacecraftArrayList;
            Context context;
            ListView listView;

            public AdapterClass(ArrayList<Spacecraft> spacecraftArrayList, Context context, ListView listView) {
                this.spacecraftArrayList = spacecraftArrayList;
                this.context = context;
                this.listView = listView;
            }

            @Override
            public int getCount() {
                return spacecraftArrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return spacecraftArrayList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder viewHolder = null;
                if (viewHolder == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies, null);
                    viewHolder = new ViewHolder(convertView);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }

                Spacecraft spacecraft = spacecraftArrayList.get(position);

                viewHolder.nameTxt.setText(spacecraft.getName());

                viewHolder.idTxt.setText(spacecraft.getId());


                String sign_url = new Ip(context).getimage();

                if (sign_url != null) {

                    String image = sign_url + spacecraft.getLocation();

                    Picasso.with(context)
                            .load(image)
                            .into(viewHolder.imageView);
                }

                final String name = viewHolder.nameTxt.getText().toString();

                viewHolder.viewbooking.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        openspecificlocation(name);
                    }
                });

                return convertView;
            }


        }

        public class ViewHolder {

            TextView nameTxt, idTxt;

            View view;

            ImageView imageView;

            TextView viewbooking;

            public ViewHolder(View view) {

                this.view = view;

                nameTxt = (TextView) view.findViewById(R.id.txtName);

                idTxt = (TextView) view.findViewById(R.id.txtId);

                viewbooking = (TextView) view.findViewById(R.id.textViewPrice);

                imageView = (ImageView) view.findViewById(R.id.imageView);


            }

        }

        private void openspecificlocation(String name) {

//            Intent jeff = new Intent(context, ViewSlots.class);
//
//            Bundle bundle = new Bundle();
//
//            bundle.putString("email_key", name);
//
//            jeff.putExtras(bundle);
//
//            context.startActivity(jeff);

        }
    }
}
