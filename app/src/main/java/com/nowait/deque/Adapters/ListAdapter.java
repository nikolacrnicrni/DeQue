package com.nowait.deque.Adapters;

/**
 * Created by user on 31.5.2018..
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nowait.deque.Model.AllUstanoveModel;
import com.nowait.deque.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<AllUstanoveModel> articles;
    private Context context;

    public OnLoadMoreListener loadMoreListener;

    private boolean isLoading = false;
    private boolean isMoreDataAvailable = true;



    public ListAdapter(Context context, List<AllUstanoveModel> articles) {
        this.articles = articles;
        this.context = context;
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new ViewHolder(inflater.inflate(R.layout.card_view_layout, parent, false));

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position >= (getItemCount() - 1) && isMoreDataAvailable && !isLoading && loadMoreListener != null)
        {
            isLoading = true;
            loadMoreListener.onLoadMore();
        }


        holder.datum.setText("");


    }


    @Override
    public int getItemCount() {
        return articles.size();
    }


    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public void  SetOnItemClickListener(final OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView naslov, datum;
        private ImageView slika;
        private RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            super(view);

//            datum = (TextView)view.findViewById(R.id.datum);

            //Podesava mogucnost klika na Card
            relativeLayout.setClickable(true);
            relativeLayout.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null)
            {
                onItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }



    public void setMoreDataAvailable(boolean moreDataAvailable)
    {
        isMoreDataAvailable = moreDataAvailable;
    }

    public void  notifyDataChanged()
    {
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener
    {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener)
    {
        this.loadMoreListener = loadMoreListener;
    }


    // BRISE STARIJE VIJESTI ZA swipeRefreshLayout, ODNOSNO REFRESH STRANICE
    public void clear() {
        articles.clear();
        notifyDataSetChanged();
    }





}
