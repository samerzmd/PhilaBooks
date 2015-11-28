package com.dragoneel.samer.philabooks;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SamerGigaByte on 27/11/2015.
 */
public class OnlineBooksGridAdapter implements ListAdapter {
    ArrayList<OnlineBook>mBooks;
    Context context;
    public OnlineBooksGridAdapter(ArrayList<OnlineBook>Books, Context context){
        mBooks=Books;
        this.context=context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mBooks.size();
    }

    @Override
    public OnlineBook getItem(int position) {
        return mBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mBooks.get(position).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            rowView = inflater.inflate(R.layout.grid_item_book,null,false);
            // configure view holder
            holder = new ViewHolder();
            holder.title = (TextView) rowView.findViewById(R.id.title);
            rowView.setTag(holder);
        }

        // fill data
        else
        holder = (ViewHolder) rowView.getTag();
        final OnlineBook book=mBooks.get(position);

        holder.title.setText(book.title);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o=new Intent(context,PdfWebViewer.class);
                if (book.bookUrl==null)
                o.putExtra("url","www.esilicon.com/wp-content/uploads/lesson2.pdf");
                else
                    o.putExtra("url",book.bookUrl);
                context.startActivity(o);
            }
        });

        return rowView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
    class ViewHolder{
        TextView title;
    }
}
