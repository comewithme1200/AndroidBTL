package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Qlkho extends AppCompatActivity {
    FloatingActionButton addbtn;
    ListView lvNguyenLieu;
    ArrayList<ingredients> arr;
    NLadapter adapter;
    String urlxoa = "http://192.168.212.2/BTL/deleteNL.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlkho);
        lvNguyenLieu = (ListView) findViewById(R.id.lvnguyenlieu);
        addbtn = (FloatingActionButton) findViewById(R.id.floatbtn);
        arr = new ArrayList<>();
        adapter = new NLadapter(Qlkho.this, R.layout.dongnl, arr);
        lvNguyenLieu.setAdapter(adapter);
        getData("http://192.168.212.2/BTL/getingredientsdata.php");
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Qlkho.this, addNL.class);
                startActivity(intent);
            }
        });
    }

    private void getData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(Qlkho.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                arr.clear();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject object = null;
                    try {
                        object = response.getJSONObject(i);
                        arr.add(new ingredients(object.getInt("maNL"), object.getString("tenNL"), object.getInt("giaNL"), object.getString("donvi"), object.getDouble("soluong")));
                        //Toast.makeText(MainActivity.this,object.getInt("ID")+"",Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Qlkho.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void xoaNL(int idnl) {
        RequestQueue requestQueue = Volley.newRequestQueue(Qlkho.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlxoa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    if(response.trim().equals("success")){
                        Toast.makeText(Qlkho.this,"Xoá thành công",Toast.LENGTH_LONG).show();
                        getData("http://192.168.212.2/BTL/getingredientsdata.php");
                    }
                    else{
                        Toast.makeText(Qlkho.this,"Lỗi xoá",Toast.LENGTH_LONG).show();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Qlkho.this,"Xoá lỗi",Toast.LENGTH_LONG);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("maNL",String.valueOf(idnl));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Qlkho.this,Scscreen.class);
        startActivity(intent);
        //super.onBackPressed();
    }
}