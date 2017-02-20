package com.shixin.finalfigher.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shixin.finalfigher.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创意
 */
public class MsgIdeasFragment extends Fragment {

    View view;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    public MsgIdeasFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_msg_ideas, container, false);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_MsgIdeas);
            linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

            mRecyclerView.setLayoutManager(linearLayoutManager);
        }
        return view;
    }

}
