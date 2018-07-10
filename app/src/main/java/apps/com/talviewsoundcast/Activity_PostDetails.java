package apps.com.talviewsoundcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Post details screen showing the list of comments
public class Activity_PostDetails extends AppCompatActivity{

    TextView textViewUserName,textViewTitle,textViewBody;
    RecyclerView recyclerViewComments;
    ImageButton imageButtonBack;

    String stringUserId = "",stringPostId = "";
    List<List_PostsAlbums> commentsLists = new ArrayList<>();
    private Adapter_RecyclerComments commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetails);

        textViewUserName = (TextView)findViewById(R.id.tv_username);
        textViewTitle = (TextView)findViewById(R.id.tv_title);
        textViewBody = (TextView)findViewById(R.id.tv_body);
        recyclerViewComments = (RecyclerView)findViewById(R.id.recycler_viewComments);
        imageButtonBack = (ImageButton)findViewById(R.id.ib_back);
        commentsAdapter = new Adapter_RecyclerComments(commentsLists);

        stringPostId = getIntent().getStringExtra("id");
        stringUserId = getIntent().getStringExtra("userid");
        textViewTitle.setText(getIntent().getStringExtra("title"));
        textViewBody.setText(getIntent().getStringExtra("body"));

        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewComments.setItemAnimator(new DefaultItemAnimator());
        recyclerViewComments.setAdapter(commentsAdapter);

        getComments(stringPostId);
        getUsers(stringUserId);

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_PostDetails.this, Activity_Main.class);
                startActivity(intent);
            }
        });
    }

    public void getComments(String stringId){
        String stringID = stringId;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIClient service = retrofit.create(APIClient.class);
        Call<List<List_PostsAlbums>> call=   service.getComments(stringID);
        call.enqueue(new Callback<List<List_PostsAlbums>>() {
            @Override
            public void onResponse(Call<List<List_PostsAlbums>> call, Response<List<List_PostsAlbums>> response) {

                List<List_PostsAlbums> list = response.body();
                List_PostsAlbums postsList = null;
                for (int i =0 ;i<list.size();i++){
                    postsList = new List_PostsAlbums();
                    postsList.setStringId(list.get(i).getStringId());
                    postsList.setStringName(list.get(i).getStringName());
                    postsList.setStringBody(list.get(i).getStringBody());
                    commentsLists.add(postsList);
                }
                commentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<List_PostsAlbums>> call, Throwable t) {

            }
        });
    }

    public void getUsers(String stringId){
        String stringID = stringId;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIClient service = retrofit.create(APIClient.class);
        Call<List<List_Users>> call=   service.getUsers(stringID);
        call.enqueue(new Callback<List<List_Users>>() {
            @Override
            public void onResponse(Call<List<List_Users>> call, Response<List<List_Users>> response) {

                List<List_Users> list = response.body();
                for (int i =0 ;i<list.size();i++) {
                    List_Users list_users = new List_Users();
                    list_users.setStringUsername(list.get(i).getStringUsername());
                    textViewUserName.setText(list.get(i).getStringUsername());
                }
            }

            @Override
            public void onFailure(Call<List<List_Users>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_PostDetails.this, Activity_Main.class);
        startActivity(intent);
    }
}
