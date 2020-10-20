package com.java.LuXingyu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    //点击
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    private List<PlagueEntity> myNewsMyList;
    private Context context;
    private int haved;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView enwike;
        TextView baidu;
        TextView zhwiki;
        TextView p;
        TextView f;
        TextView hot;
        LinearLayout entry;
        ImageView imageView;
        LinearLayout lin_entry;
        int haveImage=0;
        int haveporper=0;
        int haveRela=0;

        public ViewHolder(View view) {
            super(view);
            entry=(LinearLayout)view.findViewById(R.id.entry);
            name = (TextView) view.findViewById(R.id.name);
            //enwike = (TextView) view.findViewById(R.id.enwiki);
            baidu = (TextView) view.findViewById(R.id.baidu);
            //zhwiki= (TextView) view.findViewById(R.id.zhiwiki);
            p = (TextView) view.findViewById(R.id.porperty);
            f = (TextView) view.findViewById(R.id.father);
            hot = (TextView) view.findViewById(R.id.hot);
            imageView=(ImageView)view.findViewById(R.id.imageView_entry);
            lin_entry=(LinearLayout)view.findViewById(R.id.lin_entry);
        }
    }


    public EntryAdapter(List<PlagueEntity> newsMyList,Context c) {
        myNewsMyList = newsMyList;
        context=c;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_entry, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder) {
            //Introduction newsMy = myNewsMyList.get(myNewsMyList.size()-1-position);
            PlagueEntity newsMy = myNewsMyList.get(position);

            ((ViewHolder)holder).name.setText(newsMy.getLabel());
            ((ViewHolder)holder).baidu.setText(newsMy.getInfo());

            if(((ViewHolder) holder).haveporper==0) {
                String s="";
                for (int i = 0; i < newsMy.properties.length; i++) {
                    s+=newsMy.properties[i];
                    s+="\n";
                }
                ((ViewHolder) holder).p.setText(s);
                ((ViewHolder) holder).haveporper=0;
                if(newsMy.properties.length==0)
                    ((ViewHolder) holder).p.setText("");
            }



            ((ViewHolder)holder).hot.setText("hot:"+newsMy.getHot().toString());
            if(((ViewHolder) holder).haveRela==0) {
                String s="";
                for (int i = 0; i < newsMy.forward.length; i++) {
                    if (newsMy.forward[i])
                        s += newsMy.relationships[i] + " -> " + newsMy.related_entities[i];
                    else s += newsMy.relationships[i] + " <- " + newsMy.related_entities[i];
                    s+="\n";
                }
                ((ViewHolder) holder).f.setText(s);
                ((ViewHolder) holder).haveRela=0;
                if(newsMy.forward.length==0)
                    ((ViewHolder) holder).p.setText("");
            }




            if(!newsMy.img.equals("null")){
                if(((ViewHolder) holder).haveImage==0) {
                    //ImageView imageView = new ImageView(context);
                    Glide.with(context).load(newsMy.img).into( ((ViewHolder) holder).imageView);
                    //((ViewHolder) holder).lin_entry.addView( ((ViewHolder) holder).imageView);
                    ((ViewHolder) holder).haveImage=1;
                }

            }
            else  ((ViewHolder) holder).imageView.setVisibility(View.INVISIBLE);
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