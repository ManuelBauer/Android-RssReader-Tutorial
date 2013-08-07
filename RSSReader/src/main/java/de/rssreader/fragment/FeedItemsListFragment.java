package de.rssreader.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import at.theengine.android.simple_rss2_android.RSSItem;
import at.theengine.android.simple_rss2_android.SimpleRss2Parser;
import at.theengine.android.simple_rss2_android.SimpleRss2ParserCallback;
import de.rssreader.MainActivity;
import de.rssreader.adapter.FeedItemListAdapter;

public class FeedItemsListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loadFeed("http://feeds.feedburner.com/mobiFlip/");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void loadFeed(String url) {

        final ProgressDialog dlg = new ProgressDialog(getActivity());
        dlg.setMessage("Lade Feed...");

        SimpleRss2Parser parser = new SimpleRss2Parser(url, new SimpleRss2ParserCallback() {
            @Override
            public void onFeedParsed(List<RSSItem> rssItems) {

                // RSS-Elemente der Liste zuweisen
                FeedItemsListFragment.this.setListAdapter(new FeedItemListAdapter(getActivity(), rssItems));

                // Ladedialog ausblenden
                dlg.dismiss();
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getActivity(), "Fehler beim Laden des Feeds aufgetreten: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Ladedialog anzeigen
        dlg.show();

        // Laden des Feeds starten
        parser.parseAsync();
    }
}



