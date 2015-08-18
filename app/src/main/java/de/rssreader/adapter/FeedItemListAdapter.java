package de.rssreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import at.theengine.android.simple_rss2_android.RSSItem;
import de.rssreader.R;

public class FeedItemListAdapter extends BaseAdapter {

    private Context mContext;
    private List<RSSItem> mItems;

    public FeedItemListAdapter(Context c, List<RSSItem> items) {
        mContext = c;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RSSItem rssItem = mItems.get(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listitem_feeditem, parent, false);

        if(view == null) return null;

        TextView tvFeedItemHeader = (TextView) view.findViewById(R.id.tvFeedItemHeader);
        TextView tvFeedItemDescription = (TextView) view.findViewById(R.id.tvFeedItemDescription);

        if(tvFeedItemHeader != null)
            tvFeedItemHeader.setText(rssItem.getTitle());

        if(tvFeedItemDescription != null)
            tvFeedItemDescription.setText(rssItem.getDescription());

        return view;
    }
}
