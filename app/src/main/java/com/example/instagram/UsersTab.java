package com.example.instagram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersTab extends Fragment {

    private ListView listView;
    private ArrayList arraylist;
    private ArrayAdapter arrayadapter;

    public UsersTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_tab, container, false);

        listView = view.findViewById(R.id.listviewusertab);
        arraylist = new ArrayList();
        arrayadapter = new ArrayAdapter(getContext() , android.R.layout.simple_list_item_1 , arraylist);
        final TextView loadingusers = view.findViewById(R.id.loadingusers);

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

        parseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {

                if(e == null){

                    if(users.size() > 0){

                        for(ParseUser user : users){

                            arraylist.add(user.getUsername());

                        }

                        listView.setAdapter(arrayadapter);
                        loadingusers.animate().alpha(0).setDuration(1000);
                        listView.setVisibility(View.VISIBLE);
                    }

                }
            }
        });


        return view;
    }
}
