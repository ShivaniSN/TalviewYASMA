package apps.com.talviewsoundcast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter_RecyclerAlbums extends RecyclerView.Adapter<Adapter_RecyclerAlbums.MyViewHolder>{

    private List<List_PostsAlbums> channelsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;

        public MyViewHolder(View view) {
            super(view);
            textViewTitle = (TextView) view.findViewById(R.id.tv_title);
        }
    }

    public Adapter_RecyclerAlbums(List<List_PostsAlbums> moviesList) {
        this.channelsList = moviesList;
    }

    @Override
    public Adapter_RecyclerAlbums.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_albums, parent, false);

        return new Adapter_RecyclerAlbums.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_RecyclerAlbums.MyViewHolder holder, int position) {
        final List_PostsAlbums table = channelsList.get(position);
        holder.textViewTitle.setText(table.getStringTitle());
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public List<List_PostsAlbums> getChannelsList() {
        return channelsList;
    }
}


