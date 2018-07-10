package apps.com.talviewsoundcast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter_RecyclerComments extends RecyclerView.Adapter<Adapter_RecyclerComments.MyViewHolder>{

    private List<List_PostsAlbums> channelsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBody,textViewName;

        public MyViewHolder(View view) {
            super(view);
            textViewName = (TextView) view.findViewById(R.id.tv_name);
            textViewBody = (TextView) view.findViewById(R.id.tv_body);
        }
    }

    public Adapter_RecyclerComments(List<List_PostsAlbums> moviesList) {
        this.channelsList = moviesList;
    }

    @Override
    public Adapter_RecyclerComments.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_comments, parent, false);

        return new Adapter_RecyclerComments.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_RecyclerComments.MyViewHolder holder, int position) {
        final List_PostsAlbums table = channelsList.get(position);
        holder.textViewName.setText(table.getStringName());
        holder.textViewBody.setText(table.getStringBody());
        // holder.textViewUserName.setText(table.getStringUserName());
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public List<List_PostsAlbums> getChannelsList() {
        return channelsList;
    }
}


