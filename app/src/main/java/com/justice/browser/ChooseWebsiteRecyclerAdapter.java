package com.justice.browser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChooseWebsiteRecyclerAdapter extends RecyclerView.Adapter<ChooseWebsiteRecyclerAdapter.ViewHolder> {
    private List<WebSite> list;
    private Context context;

    public ChooseWebsiteRecyclerAdapter(List<WebSite> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_website,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ApplicationClass.choosenWebsitesList == null) {
                    ApplicationClass.choosenWebsitesList=new ArrayList<>();
                }

                ApplicationClass.choosenWebsitesList.add(list.get(position));
                ApplicationClass.writeAllData();
               Toast.makeText(context,list.get(position).getName()+ " choosen", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }

    public void setList(List<WebSite> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
