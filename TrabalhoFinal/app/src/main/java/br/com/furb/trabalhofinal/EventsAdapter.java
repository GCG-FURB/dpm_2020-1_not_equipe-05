package br.com.furb.trabalhofinal;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.furb.trabalhofinal.model.EventPojo;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private List<EventPojo> mDataset;
    private static EventsAdapter activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public EventPojo eventPojo;
        public TextView eventNome;
        public TextView eventEndereco;

        public MyViewHolder(View v) {
            super(v);
            // Handle item click and set the selection
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), Join.class);
                    intent.putExtra("eventNome", eventPojo.getNome());
                    intent.putExtra("eventDesc", eventPojo.getDescricao());
                    intent.putExtra("eventData", eventPojo.getData());
                    intent.putExtra("eventHora", eventPojo.getHorario());
                    intent.putExtra("eventEndereco", eventPojo.getEndereco());
                    intent.putExtra("eventCep", eventPojo.getCep());
                    v.getContext().startActivity(intent);
                }
            });
            eventNome = (TextView) v.findViewById(R.id.eventName);
            eventEndereco = (TextView) v.findViewById(R.id.eventAddress);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventsAdapter(List<EventPojo> myDataset) {
        mDataset = myDataset;
        activity = this;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        EventPojo data = mDataset.get(position);

        EventPojo eventPojo = new EventPojo();
        eventPojo.setNome(data.getNome());
        eventPojo.setDescricao(data.getDescricao());
        eventPojo.setData(data.getData());
        eventPojo.setHorario(data.getHorario());
        eventPojo.setEndereco(data.getEndereco());
        eventPojo.setCep(data.getCep());

        holder.eventPojo = eventPojo;
        holder.eventNome.setText(eventPojo.getNome());
        holder.eventEndereco.setText(eventPojo.getEndereco());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
