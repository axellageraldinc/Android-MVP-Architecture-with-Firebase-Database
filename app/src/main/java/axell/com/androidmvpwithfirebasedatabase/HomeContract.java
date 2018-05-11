package axell.com.androidmvpwithfirebasedatabase;

import java.util.List;

public interface HomeContract {
    interface View{
        void showDialogAddUser();
        void showUsers(List<User> userList);
        void clearDialogAddUserInput();
        void dismissDialogAddUser();
    }
    interface Interactor{
        void addUserToFirebaseDatabase(User user);
        void showUsers();
    }
    interface Presenter{
        void showDialogAddUser();
        User buildUser(String name);
        void addUserToFirebaseDatabase(User user);
        void showUsers(List<User> userList);
    }
}
