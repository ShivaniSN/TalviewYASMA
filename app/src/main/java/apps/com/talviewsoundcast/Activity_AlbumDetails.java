package apps.com.talviewsoundcast;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// class that displays all the photos in a particular album in grid view
public class Activity_AlbumDetails extends AppCompatActivity{

    TextView textViewTitle;
    RecyclerView recyclerViewPhotos;
    ImageButton imageButtonBack;

    String stringId = "";
    List<List_PostsAlbums> photosLists = new ArrayList<>();
    private Adapter_RecyclerPhotos photosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albumdetails);

        textViewTitle = (TextView)findViewById(R.id.textViewAlbumTitle);
        recyclerViewPhotos = (RecyclerView)findViewById(R.id.recycler_viewPhotos);
        imageButtonBack = (ImageButton)findViewById(R.id.ib_back);
        photosAdapter = new Adapter_RecyclerPhotos(photosLists,Activity_AlbumDetails.this);

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_AlbumDetails.this, Activity_Main.class);
                startActivity(intent);
            }
        });

        stringId = getIntent().getStringExtra("id");
        textViewTitle.setText(getIntent().getStringExtra("title"));

        recyclerViewPhotos.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        recyclerViewPhotos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPhotos.setAdapter(photosAdapter);

        getPhotos(stringId);

        recyclerViewPhotos.addOnItemTouchListener(new RecyclerTouchListener(Activity_AlbumDetails.this, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Dialog dialog = new Dialog(Activity_AlbumDetails.this);
                dialog.setContentView(R.layout.dialog_photo);

                TextView textViewTitle = (TextView)dialog.findViewById(R.id.tv_title);
                ImageButton imageButtonBack = (ImageButton)dialog.findViewById(R.id.ib_back);
                ImageView imageViewPhoto = (ImageView) dialog.findViewById(R.id.iv_photo);

                textViewTitle.setText(photosLists.get(position).getStringTitle());
                Picasso.with(Activity_AlbumDetails.this).load(photosLists.get(position).getStringThumbnailURL()).into(imageViewPhoto);
                dialog.show();

                imageButtonBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });
            }
        }));
    }

    public void getPhotos(String stringId){
        String stringID = stringId;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIClient service = retrofit.create(APIClient.class);
        Call<List<List_PostsAlbums>> call=   service.getPhotos(stringID);
        call.enqueue(new Callback<List<List_PostsAlbums>>() {
            @Override
            public void onResponse(Call<List<List_PostsAlbums>> call, Response<List<List_PostsAlbums>> response) {

                List<List_PostsAlbums> list = response.body();
                List_PostsAlbums postsList = null;
                for (int i =0 ;i<list.size();i++){
                    postsList = new List_PostsAlbums();
                    postsList.setStringId(list.get(i).getStringId());
                    postsList.setStringTitle(list.get(i).getStringTitle());
                    postsList.setStringURL(list.get(i).getStringURL());
                    postsList.setStringThumbnailURL(list.get(i).getStringThumbnailURL());
                    photosLists.add(postsList);
                }
                photosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<List_PostsAlbums>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_AlbumDetails.this, Activity_Main.class);
        startActivity(intent);
    }
}
