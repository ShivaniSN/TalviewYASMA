package apps.com.talviewsoundcast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_RecyclerPhotos extends RecyclerView.Adapter<Adapter_RecyclerPhotos.MyViewHolder>{

    private List<List_PostsAlbums> channelsList;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public ImageView imageViewPhoto;

        public MyViewHolder(View view) {
            super(view);
            textViewTitle = (TextView) view.findViewById(R.id.tv_title);
            imageViewPhoto = (ImageView)view.findViewById(R.id.iv_photo);
        }
    }

    public Adapter_RecyclerPhotos(List<List_PostsAlbums> moviesList, Context context) {
        this.channelsList = moviesList;
        this.context = context;
    }

    @Override
    public Adapter_RecyclerPhotos.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleritem_photos, parent, false);

        return new Adapter_RecyclerPhotos.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapter_RecyclerPhotos.MyViewHolder holder, int position) {
        final List_PostsAlbums table = channelsList.get(position);
        holder.textViewTitle.setText(table.getStringTitle());
        Picasso.with(context).load(table.getStringThumbnailURL()).into(holder.imageViewPhoto);
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public List<List_PostsAlbums> getChannelsList() {
        return channelsList;
    }
}



