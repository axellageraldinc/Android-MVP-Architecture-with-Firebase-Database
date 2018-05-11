package axell.com.androidmvpwithfirebasedatabase;

import java.util.List;
import java.util.UUID;

public class HomePresenterImpl implements HomeContract.Presenter {

    private HomeContract.View homeView;
    private HomeContract.Interactor homeInteractor;

    public HomePresenterImpl(HomeContract.View homeView) {
        this.homeView = homeView;
        homeInteractor = new HomeInteractorImpl(this);
    }

    @Override
    public void showDialogAddUser() {
        homeView.showDialogAddUser();
    }

    @Override
    public User buildUser(String name) {
        return new User(UUID.randomUUID().toString(), name);
    }

    @Override
    public void addUserToFirebaseDatabase(User user) {
        homeInteractor.addUserToFirebaseDatabase(user);
        homeView.clearDialogAddUserInput();
        homeView.dismissDialogAddUser();
        homeInteractor.showUsers();
    }

    @Override
    public void showUsers(List<User> userList) {
        homeView.showUsers(userList);
    }
}
