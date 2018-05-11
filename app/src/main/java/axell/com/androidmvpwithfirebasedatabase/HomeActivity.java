package axell.com.androidmvpwithfirebasedatabase;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.View, Button.OnClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private HomeContract.Presenter homePresenter;

    private FloatingActionButton btnShowDialogAddUser;
    private Dialog dialogAddUser;
    private EditText txtUserName;
    private Button btnAddUser;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShowDialogAddUser:
                homePresenter.showDialogAddUser();
                break;
            case R.id.btnAddUser:
                User user = homePresenter.buildUser(txtUserName.getText().toString());
                homePresenter.addUserToFirebaseDatabase(user);
                break;
        }
    }

    @Override
    public void showDialogAddUser() {
        dialogAddUser.show();
    }

    @Override
    public void showUsers(List<User> userList) {
        Log.i(TAG, "USER LIST!");
        for (User user:userList
             ) {
            Log.i(TAG, user.getId() + " | " + user.getName());
        }
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
