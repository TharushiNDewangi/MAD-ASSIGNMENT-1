package com.example.elearningsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link c#newInstance} factory method to
 * create an instance of this fragment.
 */
public class c extends Fragment {
    Button b1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recycleropt;
    FirebaseRecyclerAdapter<course,Jadpter> adapter;
    FirebaseDatabase database;
    DatabaseReference reference;

    public c() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment c.
     */
    // TODO: Rename and change types and number of parameters
    public static c newInstance(String param1, String param2) {
        c fragment = new c();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ArrayList<course> list;
    Jadpter jadapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_java, container, false);

        database = FirebaseDatabase.getInstance();
        reference=database.getReference("course");

        recycleropt = view.findViewById(R.id.jadpter);



        dbhandler();

        return view;
    }
    private void dbhandler()
    {
        Query qury = reference.orderByChild("coursename").equalTo("C");
        FirebaseRecyclerOptions<course> options = new FirebaseRecyclerOptions.Builder<course>().setQuery(qury,course.class).build();
        adapter=new FirebaseRecyclerAdapter<course, Jadpter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Jadpter holder, int position, @NonNull final course model) {
                holder.t1.setText(model.getTitle());
                holder.b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getActivity(),"@@1123",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(),viewcourse.class);
                        intent.putExtra("title",model.getTitle());
                        getContext().startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public Jadpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.javaadpter,parent,false);
                return new Jadpter(view);
            }

        };
        recycleropt.setHasFixedSize(true);
        recycleropt.setLayoutManager(new GridLayoutManager(getContext(),1));
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recycleropt.setAdapter(adapter);
    }

}