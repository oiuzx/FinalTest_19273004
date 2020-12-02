import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    String baseUrl = "http://www.kobis.or.kr";
    String API_KEY = "bf8e561b64453cd2b124534d75787abb";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.rv_recyclerview);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofitInterface= retrofit.create(RetrofitInterface.class);

        retrofitInterface.getBoxOffice(API_KEY,"20201201").enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {

                Map<String,Object> boxOfficeResult= (Map<String, Object>) response.body().get("boxOfficeResult");
                ArrayList<Map<String, Object>> jsonList = (ArrayList) boxOfficeResult.get("dailyBoxOfficeList");
                mAdapter=new MyAdapter(jsonList);

            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {

            }
        });

    }// onCreate()..

    public void click_btn(View view) {
        recyclerView.setAdapter(mAdapter);
    }

    public void OnClickedGoogle(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://movie.naver.com/"));
        startActivity(myIntent);
    }

}// MainActivity class..