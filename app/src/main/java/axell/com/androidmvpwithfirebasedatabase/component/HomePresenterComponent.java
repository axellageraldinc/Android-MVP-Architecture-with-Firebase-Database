package axell.com.androidmvpwithfirebasedatabase.component;

import axell.com.androidmvpwithfirebasedatabase.module.HomePresenterModule;
import axell.com.androidmvpwithfirebasedatabase.presenter.HomePresenterImpl;
import axell.com.androidmvpwithfirebasedatabase.view.HomeActivity;
import dagger.Component;

@Component(modules = {HomePresenterModule.class})
public interface HomePresenterComponent {
    HomePresenterImpl provideHomePresenter();
}
