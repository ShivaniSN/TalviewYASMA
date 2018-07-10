package apps.com.talviewsoundcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Main screen displaying the list of posts and albums
public class Activity_Main extends AppCompatActivity {

    RecyclerView recyclerViewPosts,recyclerViewAlbums;

    List<List_PostsAlbums> postsLists = new ArrayList<>();
    List<List_PostsAlbums> albumsLists = new ArrayList<>();
    private Adapter_RecyclerPosts postsAdapter;
    private Adapter_RecyclerAlbums albumsAdapter;
    String stringUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPosts = (RecyclerView)findViewById(R.id.recycler_viewPosts);
        recyclerViewAlbums = (RecyclerView)findViewById(R.id.recycler_viewAlbums);
        postsAdapter = new Adapter_RecyclerPosts(postsLists);
        albumsAdapter = new Adapter_RecyclerAlbums(albumsLists);

        recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewPosts.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerViewPosts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPosts.setAdapter(postsAdapter);

        recyclerViewAlbums.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerViewAlbums.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAlbums.setAdapter(albumsAdapter);

        getPosts();
        getAlbums();

        recyclerViewPosts.addOnItemTouchListener(new RecyclerTouchListener(Activity_Main.this, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Activity_Main.this, Activity_PostDetails.class);
                intent.putExtra("id",postsLists.get(position).getStringId());
                intent.putExtra("userid",postsLists.get(position).getStringUserId());
                intent.putExtra("title",postsLists.get(position).getStringTitle());
                intent.putExtra("body",postsLists.get(position).getStringBody());
                startActivity(intent);
            }
        }));

        recyclerViewAlbums.addOnItemTouchListener(new RecyclerTouchListener(Activity_Main.this, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Activity_Main.this, Activity_AlbumDetails.class);
                intent.putExtra("id",albumsLists.get(position).getStringId());
                intent.putExtra("title",albumsLists.get(position).getStringTitle());
                startActivity(intent);
            }
        }));
    }

    public void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIClient service = retrofit.create(APIClient.class);
        Call<List<List_PostsAlbums>> call=   service.getPosts();
        call.enqueue(new Callback<List<List_PostsAlbums>>() {
            @Override
            public void onResponse(Call<List<List_PostsAlbums>> call, Response<List<List_PostsAlbums>> response) {

                List<List_PostsAlbums> list = response.body();
                List_PostsAlbums postsList = null;
                for (int i =0 ;i<list.size();i++){
                    postsList = new List_PostsAlbums();
                    postsList.setStringId(list.get(i).getStringId());
                    postsList.setStringTitle(list.get(i).getStringTitle());
                    postsList.setStringUserId(list.get(i).getStringUserId());
                    postsList.setStringBody(list.get(i).getStringBody());
                    postsLists.add(postsList);
                }
                postsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<List_PostsAlbums>> call, Throwable t) {

            }
        });
    }

    public void getAlbums(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIClient service = retrofit.create(APIClient.class);
        Call<List<List_PostsAlbums>> call=   service.getAlbums();
        call.enqueue(new Callback<List<List_PostsAlbums>>() {
            @Override
            public void onResponse(Call<List<List_PostsAlbums>> call, Response<List<List_PostsAlbums>> response) {

                List<List_PostsAlbums> list = response.body();
                List_PostsAlbums albumsList = null;
                for (int i =0 ;i<list.size();i++){
                    albumsList = new List_PostsAlbums();
                    albumsList.setStringId(list.get(i).getStringId());
                    albumsList.setStringTitle(list.get(i).getStringTitle());
                    albumsList.setStringUserId(list.get(i).getStringUserId());
                    albumsLists.add(albumsList);
                }
                albumsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<List_PostsAlbums>> call, Throwable t) {

            }
        });
    }
}
