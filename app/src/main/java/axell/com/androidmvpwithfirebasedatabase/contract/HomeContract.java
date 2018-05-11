package axell.com.androidmvpwithfirebasedatabase.contract;

import java.util.List;

import axell.com.androidmvpwithfirebasedatabase.model.User;

public interface HomeContract {
    interface View{
        void showDialogAddUser();
        void showUsers(List<User> userList);
        void clearDialogAddUserInput();
        void dismissDialogAddUser();
    }
    interface Interactor{
        void saveUserToFirebaseDatabase(User user);
        void getAllUsersFromFirebaseDatabase();
    }
    interface Presenter{
        User buildUser(String name);
        void saveUser(User user);
        void showUsers(List<User> userList);
    }
}
