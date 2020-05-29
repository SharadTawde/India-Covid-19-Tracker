package com.sharad.covid.alert;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView confirmText, activeText, recoverText, deathText, confirmDeltaText, recoverDeltaText, deathDeltaText, timeStampText;
    private RequestQueue mQueue;
    private List<ViewModel> list;
    private SwipeRefreshLayout swipeRefresh;

    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        swipeRefresh.setRefreshing(true);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void initView() {
        confirmText = findViewById(R.id.conformText);
        activeText = findViewById(R.id.activeText);
        recoverText = findViewById(R.id.recoverText);
        deathText = findViewById(R.id.deathText);
        timeStampText = findViewById(R.id.time_stamp);
        confirmDeltaText = findViewById(R.id.confirmDelta);
        recoverDeltaText = findViewById(R.id.recoverDelta);
        deathDeltaText = findViewById(R.id.deathDelta);

        swipeRefresh = findViewById(R.id.swipe);

        mList = findViewById(R.id.Event_RecyclerView);
        list = new ArrayList<>();
        adapter = new ViewAdapter(getApplicationContext(), list);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);
    }

    private void initData() {
        list.clear();
        mQueue = Volley.newRequestQueue(this);
        String url = "https://api.covid19india.org/data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("statewise");

                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            confirmText.setText(jsonObject.getString("confirmed"));
                            activeText.setText(jsonObject.getString("active"));
                            recoverText.setText(jsonObject.getString("recovered"));
                            deathText.setText(jsonObject.getString("deaths"));
                            timeStampText.setText(TimeAgo.getTimeAgo(Objects.requireNonNull(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                                    .parse(jsonObject.getString("lastupdatedtime")))));

                            confirmDeltaText.setText("[+" + jsonObject.getString("deltaconfirmed") + "]");
                            deathDeltaText.setText("[+" + jsonObject.getString("deltadeaths") + "]");
                            recoverDeltaText.setText("[+" + jsonObject.getString("deltarecovered") + "]");


                            for (int i = 1; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                ViewModel viewModel = new ViewModel();

                                viewModel.setActive(jsonObject1.getString("active"));
                                viewModel.setConfirmed(jsonObject1.getString("confirmed"));
                                viewModel.setDeaths(jsonObject1.getString("deaths"));
                                viewModel.setRecovered(jsonObject1.getString("recovered"));

                                viewModel.setState(jsonObject1.getString("state"));
                                viewModel.setLastupdatedtime(jsonObject1.getString("lastupdatedtime"));
                                viewModel.setDeltaconfirmed(jsonObject1.getString("deltaconfirmed"));
                                viewModel.setDeltadeaths(jsonObject1.getString("deltadeaths"));
                                viewModel.setDeltarecovered(jsonObject1.getString("deltarecovered"));
                                list.add(viewModel);
                            }
                            swipeRefresh.setRefreshing(false);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefresh.setRefreshing(false);
            }
        });
        mQueue.add(request);
    }

}
