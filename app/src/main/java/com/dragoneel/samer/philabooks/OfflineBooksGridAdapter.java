package com.dragoneel.samer.philabooks;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SamerGigaByte on 12/12/2015.
 */
public class OfflineBooksGridAdapter implements ListAdapter {
    ArrayList<OfflineBook> mBooks;
    Context context;
    public OfflineBooksGridAdapter(ArrayList<OfflineBook>Books, Context context){
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
    public OfflineBook getItem(int position) {
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
        if(mBooks.size()>0) {
            final OfflineBook book = mBooks.get(position);

            holder.title.setText(book.title);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent o = new Intent(context, PdfViewActivity.class);
                    o.putExtra("path", book.path);
                    context.startActivity(o);
                }
            });
        }
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
