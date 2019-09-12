package ir.kukuapp.mobile.internal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ir.kukuapp.mobile.R;
import ir.kukuapp.mobile.model.PostModel;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {

    private Context context; //context
    private ArrayList<PostModel> items; //data source of the list adapter

    //public constructor
    public HomeAdapter(Context context, ArrayList<PostModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            convertView = inflater.inflate(R.layout.listitem_home, container, false);
        }

        ((TextView) convertView.findViewById(R.id.tv_status))
                .setText(getItem(position).toString());
        return convertView;
    }
}
