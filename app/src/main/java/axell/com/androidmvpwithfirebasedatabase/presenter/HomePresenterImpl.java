package axell.com.androidmvpwithfirebasedatabase.presenter;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import axell.com.androidmvpwithfirebasedatabase.component.DaggerHomeInteractorComponent;
import axell.com.androidmvpwithfirebasedatabase.contract.HomeContract;
import axell.com.androidmvpwithfirebasedatabase.interactor.HomeInteractorImpl;
import axell.com.androidmvpwithfirebasedatabase.model.User;
import axell.com.androidmvpwithfirebasedatabase.module.HomeInteractorModule;

public class HomePresenterImpl implements HomeContract.Presenter {

    private HomeContract.View homeView;
    private HomeContract.Interactor homeInteractor;

    @Inject
    public HomePresenterImpl(HomeContract.View homeView) {
        this.homeView = homeView;
        homeInteractor = DaggerHomeInteractorComponent.builder()
                .homeInteractorModule(new HomeInteractorModule(this))
                .build()
                .provideHomeInteractor();
    }

    @Override
    public User buildUser(String name) {
        return new User(UUID.randomUUID().toString(), name);
    }

    @Override
    public void saveUser(User user) {
        homeInteractor.saveUserToFirebaseDatabase(user);
        homeView.clearDialogAddUserInput();
        homeView.dismissDialogAddUser();
        homeInteractor.getAllUsersFromFirebaseDatabase();
    }

    @Override
    public void showUsers(List<User> userList) {
        homeView.showUsers(userList);
    }

    @Override
    public void onResume() {
        homeInteractor.getAllUsersFromFirebaseDatabase();
    }
}
