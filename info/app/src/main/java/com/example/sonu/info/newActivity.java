package com.example.sonu.info;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.sonu.info.R.id.parent;
import static com.example.sonu.info.R.styleable.View;


public class newActivity extends AppCompatActivity {
        private String TAG = com.example.sonu.info.MainActivity.class.getSimpleName();

        private ProgressDialog pDialog;
        private ListView lv;


        // URL to get contacts JSON
        private static  String url  = null;

        ArrayList<HashMap<String, String>> contactList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new);
            Bundle bundle = getIntent().getExtras();
            String source1 = bundle.getString("source1");
            String url1 = "https://newsapi.org/v1/articles?source="+source1+"&apiKey=61da6aef60b44ea8b70b54028e529174";
            url = url1;

            setTitle(source1.toUpperCase());

            contactList = new ArrayList<>();
            lv = (ListView) findViewById(R.id.list);
            new com.example.sonu.info.newActivity.GetContacts().execute();


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                    String url =((TextView)view.findViewById(R.id.url)).getText().toString();
                    String heading =((TextView)view.findViewById(R.id.name)).getText().toString();
//                    Toast.makeText(getApplicationContext() , textview , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                    intent.putExtra("url" , url);
                    intent.putExtra("heading" , heading);
                    startActivity(intent);

                }


            });

        }

        /**
         * Async task class to get json by making HTTP call
         */
        private class GetContacts extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(com.example.sonu.info.newActivity.this);
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();

            }

            @Override
            protected Void doInBackground(Void... arg0) {
                HttpHandler sh = new HttpHandler();

                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url: " + jsonStr);

                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("articles");

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            String title = c.getString("title");
                            String description = c.getString("description");
                            String publishedAt = c.getString("publishedAt");
                            String urlToImage = c.getString("urlToImage");
                            String url = c.getString("url");



                            // tmp hash map for single contact
                            HashMap<String, String> contact = new HashMap<>();

                            // adding each child node to HashMap key => value
                            contact.put("title", title);
                            contact.put("description", description);
                            contact.put("publishedAt", publishedAt);
                            contact.put("urlToImage", urlToImage);
                            contact.put("url", url);

                            // adding contact to contact list
                            contactList.add(contact);
                        }
                    } catch (final JSONException e) {
                        Log.e(TAG, "Check Internet Connection " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Check Internet Connection  " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {
                    Log.e(TAG, "Check Internet Connection ");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Check Internet Connection ",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                // Dismiss the progress dialog
                if (pDialog.isShowing())
                    pDialog.dismiss();
                /**
                 * Updating parsed JSON data into ListView
                 * */
                ListAdapter adapter = new SimpleAdapter(
                        com.example.sonu.info.newActivity.this, contactList,
                        R.layout.list_item, new String[]{"title", "description",
                        "publishedAt" , "url"}, new int[]{R.id.name,
                        R.id.email, R.id.mobile , R.id.url});

                lv.setAdapter(adapter);
            }

        }



    }