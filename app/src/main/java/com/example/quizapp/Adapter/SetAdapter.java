package com.example.quizapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.Activitiy.QuestionActivity;
import com.example.quizapp.Model.QuestionModel;
import com.example.quizapp.Model.SetModel;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ItemSetBinding;

import java.util.ArrayList;

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.viewHolder>{

    Context context;
    ArrayList<SetModel> list;

    public SetAdapter(Context context, ArrayList<SetModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_set,parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final SetModel model = list.get(position);
        holder.binding.setName.setText(model.getSetNme());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, QuestionActivity.class);
                intent.putExtra("set", model.getSetNme());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        ItemSetBinding binding;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemSetBinding.bind(itemView);
        }
    }


}
