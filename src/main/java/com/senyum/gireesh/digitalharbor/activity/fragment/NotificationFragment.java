package com.senyum.gireesh.digitalharbor.activity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.senyum.gireesh.digitalharbor.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class NotificationFragment extends Fragment {

    private ListView listView;
    private ArrayList<HashMap<String, Object>> personList = new ArrayList<HashMap<String, Object>>();
    private HashMap<String, Object> persons = new HashMap<String, Object>();
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        listView = (ListView) rootView.findViewById(R.id.lv_notification);
        persons.put("notification_title", "Title");
        persons.put("notification_details", "Always use the getActivity() method to get the context of your attached activity, but always remember one thing");
        persons.put("notification_time", new Date().toString());
        personList.add(persons);
        personList.add(persons);
        personList.add(persons);
        personList.add(persons);

        ListAdapter adapter = new SimpleAdapter(context, personList, R.layout.list_item_notification, new String[]{"notification_title", "notification_details", "notification_time"}, new int[]{R.id.notification_title, R.id.notification_details, R.id.notification_time});
        listView.setAdapter(adapter);

        return rootView;
    }

}
