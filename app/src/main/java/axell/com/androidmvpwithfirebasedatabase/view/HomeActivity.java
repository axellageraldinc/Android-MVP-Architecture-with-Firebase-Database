package axell.com.androidmvpwithfirebasedatabase.view;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import axell.com.androidmvpwithfirebasedatabase.contract.HomeContract;
import axell.com.androidmvpwithfirebasedatabase.presenter.HomePresenterImpl;
import axell.com.androidmvpwithfirebasedatabase.R;
import axell.com.androidmvpwithfirebasedatabase.model.User;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, Button.OnClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private HomeContract.Presenter homePresenter;

    private FloatingActionButton btnShowDialogAddUser;
    private Dialog dialogAddUser;
    private EditText txtUserName;
    private Button btnAddUser;

    private RecyclerView recyclerViewUser;
    private RecyclerViewUserAdapter recyclerViewUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homePresenter = new HomePresenterImpl(this);
        initViews();
    }

    private void initViews(){
        btnShowDialogAddUser = findViewById(R.id.btnShowDialogAddUser);
        btnShowDialogAddUser.setOnClickListener(this);
        dialogAddUser = new Dialog(this);
        dialogAddUser.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAddUser.setContentView(R.layout.dialog_add_user);
        txtUserName = dialogAddUser.findViewById(R.id.txtUserName);
        btnAddUser = dialogAddUser.findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(this);
        recyclerViewUser = findViewById(R.id.recyclerViewUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        homePresenter.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowDialogAddUser:
                showDialogAddUser();
                break;
            case R.id.btnAddUser:
                User user = homePresenter.buildUser(txtUserName.getText().toString());
                homePresenter.saveUser(user);
                break;
        }
    }

    @Override
    public void showDialogAddUser() {
        dialogAddUser.show();
    }

    @Override
    public void showUsers(List<User> userList) {
        recyclerViewUserAdapter = new RecyclerViewUserAdapter(userList, this);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUser.setAdapter(recyclerViewUserAdapter);
        recyclerViewUserAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearDialogAddUserInput() {
        txtUserName.setText("");
    }

    @Override
    public void dismissDialogAddUser() {
        dialogAddUser.dismiss();
    }
}
