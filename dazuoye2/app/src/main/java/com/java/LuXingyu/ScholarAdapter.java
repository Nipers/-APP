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

public class ScholarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //点击
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    private List<ScholarIntroduction> myNewsMyList;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView position;
        TextView aff;
        TextView h;
        TextView a;
        TextView s;
        TextView c;
        TextView p;
        LinearLayout lin;
        ImageView image;
        int haveImage;
        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.item_xz_name);
            aff = (TextView) view.findViewById(R.id.item_xz_school);
            position = (TextView) view.findViewById(R.id.item_xz_work);
            h= (TextView) view.findViewById(R.id.textViewH);
            a = (TextView) view.findViewById(R.id.textViewA);
            s = (TextView) view.findViewById(R.id.textViewS);
            c = (TextView) view.findViewById(R.id.textViewC);
            p = (TextView) view.findViewById(R.id.textViewP);
            lin=(LinearLayout)view.findViewById(R.id.introduction_avtr);
            image=(ImageView)view.findViewById(R.id.imageView_av);


            haveImage=0;
        }
    }


    public ScholarAdapter(List<ScholarIntroduction> newsMyList,Context c) {
        myNewsMyList = newsMyList;
        context=c;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_xz, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder==null)
            return;
        if(holder instanceof ViewHolder) {
            //Introduction newsMy = myNewsMyList.get(myNewsMyList.size()-1-position);
            ScholarIntroduction newsMy = myNewsMyList.get(position);
            if(newsMy.getName_zh().length()<2)
                ((ViewHolder)holder).name.setText(newsMy.getName());
            else ((ViewHolder)holder).name.setText(newsMy.getName_zh());
            ((ViewHolder)holder).aff.setText(newsMy.getAffiliation());
            ((ViewHolder)holder).position.setText(newsMy.getPosition());
            ((ViewHolder)holder).h.setText("H:"+newsMy.getIndice().getHindex().toString());
            ((ViewHolder)holder).a.setText("A:"+newsMy.getIndice().getActivity().toString());
            ((ViewHolder)holder).s.setText("S:"+newsMy.getIndice().getSociability().toString());
            ((ViewHolder)holder).c.setText("C:"+newsMy.getIndice().getCitations().toString());
            ((ViewHolder)holder).p.setText("P:"+newsMy.getIndice().getPubs().toString());

            String url=newsMy.getAvatar();
            Glide.with(context)
                    .load(url)
                    .into(((ViewHolder) holder).image);
            //((ViewHolder)holder).lin.addView(((ViewHolder)holder).image);


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