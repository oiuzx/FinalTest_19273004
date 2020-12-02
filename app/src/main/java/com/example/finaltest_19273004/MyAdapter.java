package com.example.finaltest_19273004;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Map<String, Object>> items= new ArrayList<Map<String, Object>>();

    public MyAdapter(ArrayList<Map<String, Object>> resultList){
        this.items=resultList;
    }

    @NonNull
    @Override
    public com.example.finaltest_19273004.MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview , parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Map<String, Object> item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRank, tvMovieNm, tvOpenDt;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvRank=itemView.findViewById(R.id.tv_rank);
            tvMovieNm=itemView.findViewById(R.id.tv_movieNm);
            tvOpenDt=itemView.findViewById(R.id.tv_openDt);

        }
        public void setItem(Map<String, Object> item){

            //"rank", "movieNm", "openDt"은 Json파일에 저장되어 있던 key값
            tvRank.setText(item.get("rank").toString());
            tvMovieNm.setText(item.get("movieNm").toString());
            tvOpenDt.setText(item.get("openDt").toString());

        }
    }
}