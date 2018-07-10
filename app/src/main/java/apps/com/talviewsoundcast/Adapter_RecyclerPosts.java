package apps.com.talviewsoundcast;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter_RecyclerPosts extends RecyclerView.Adapter<Adapter_RecyclerPosts.MyViewHolder>{

    private List<List_PostsAlbums> channelsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle,textViewBody;

        public MyViewHolder(View view) {
            super(view);
            textViewTitle = (TextView) view.findViewById(R.id.tv_title);
            textViewBody = (TextView) view.findViewById(R.id.tv_body);
        }
    }

    public Adapter_RecyclerPosts(List<List_PostsAlbums> moviesList) {
        this.channelsList = moviesList;
    }

    @Override
    public Adapter_RecyclerPosts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_posts, parent, false);

        return new Adapter_RecyclerPosts.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_RecyclerPosts.MyViewHolder holder, int position) {
        final List_PostsAlbums table = channelsList.get(position);
        holder.textViewTitle.setText(table.getStringTitle());
        holder.textViewBody.setText(table.getStringBody());
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public List<List_PostsAlbums> getChannelsList() {
        return channelsList;
    }
}

