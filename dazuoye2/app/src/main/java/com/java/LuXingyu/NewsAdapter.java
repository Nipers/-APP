package com.java.LuXingyu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //点击
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    private List<Introduction> myNewsMyList;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView from;
        TextView date;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_text_title);
            from = (TextView) view.findViewById(R.id.item_text_from);
            date = (TextView) view.findViewById(R.id.item_text_date);
        }
    }


    public NewsAdapter(List<Introduction> newsMyList) {
        myNewsMyList = newsMyList;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            ViewHolder holder = new ViewHolder(view);
            //holder.date.setTextColor(R.color.color_hui);
            return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder) {
            //Introduction newsMy = myNewsMyList.get(myNewsMyList.size()-1-position);
            Introduction newsMy = myNewsMyList.get(position);
            String id=newsMy.getID();
            ReadIDManager readIDManager=AppManager.getReadIDManager();
            ReadID[] readID=readIDManager.getAllReadID();
            int flag=1;
            for(int i=0;i<readID.length;i++){
                if(readID[i].ID.equals(id))flag=0;
            }
            if(flag==0){
                ((ViewHolder)holder).itemView.setBackgroundColor(0xFFD3D3D3);
            }
            ((ViewHolder)holder).title.setText(newsMy.getTitle());
            ((ViewHolder)holder).from.setText(newsMy.getSource());
            ((ViewHolder)holder).date.setText(newsMy.getDate());



        }

        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return myNewsMyList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}