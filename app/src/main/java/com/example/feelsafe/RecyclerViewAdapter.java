package com.example.feelsafe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "abcd";

    private ArrayList<String> Names ;
    private ArrayList<String> Numbers;
    private Context mContext;
    DatabaseHelper myDb;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> numbers ) {
        Names = names;
        Numbers = numbers;
        mContext = context;
        myDb = new DatabaseHelper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.name.setText(Names.get(position));
        holder.number.setText(Numbers.get(position));

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + Names.get(position));
                Log.d("abcd", "here");

                Integer a = myDb.deleteData(Names.get(position));
                Toast.makeText(mContext, Names.get(position), Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return Names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView name;
        TextView number;
        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.tvNumber);
            name = itemView.findViewById(R.id.tvName);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
