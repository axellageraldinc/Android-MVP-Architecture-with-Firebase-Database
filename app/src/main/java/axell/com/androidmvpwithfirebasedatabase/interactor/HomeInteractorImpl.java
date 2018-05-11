package axell.com.androidmvpwithfirebasedatabase.interactor;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import axell.com.androidmvpwithfirebasedatabase.contract.HomeContract;
import axell.com.androidmvpwithfirebasedatabase.model.User;

public class HomeInteractorImpl implements HomeContract.Interactor {

    private List<User> userList = new ArrayList<>();
    private HomeContract.Presenter homePresenter;
    private DatabaseReference databaseReference;
    private static final String USERS = "users";

    public HomeInteractorImpl(HomeContract.Presenter homePresenter) {
        this.homePresenter = homePresenter;
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void saveUserToFirebaseDatabase(User user) {
        databaseReference.child(USERS).child(user.getId()).setValue(user);
    }

    @Override
    public void getAllUsersFromFirebaseDatabase() {
        databaseReference.child(USERS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()
                     ) {
                    User user = data.getValue(User.class);
                    userList.add(user);
                }
                homePresenter.showUsers(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
