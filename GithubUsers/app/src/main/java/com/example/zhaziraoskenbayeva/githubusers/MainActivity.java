package com.example.zhaziraoskenbayeva.githubusers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Disposable githubDisposable;
    List<User> curUsersList = new ArrayList<>();
    GithubUsersAdapter gua;


    @BindView(R.id.rvGIthubUsers)
    RecyclerView rvGithubUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        GridLayoutManager glm = new GridLayoutManager(this,1);
        rvGithubUsers.setLayoutManager(glm);
        gua = new GithubUsersAdapter(curUsersList);
        rvGithubUsers.setAdapter(gua);

        getUsers();
    }

    private void getUsers() {
        GithubUtils githubObj = new GithubUtils(this);
        Observable<List<User>> placesObservable =
                Observable.fromCallable(() -> {
                    return githubObj.requestToGithub();
                });
        githubDisposable = placesObservable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(strings -> setUsers(strings));
    }


    private void setUsers(List<User> users) {
        gua.swapItems(users);
        Log.d("guthubusers", users.toString());
    }
}
