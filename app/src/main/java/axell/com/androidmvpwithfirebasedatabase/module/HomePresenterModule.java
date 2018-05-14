package axell.com.androidmvpwithfirebasedatabase.module;

import javax.inject.Singleton;

import axell.com.androidmvpwithfirebasedatabase.contract.HomeContract;
import dagger.Module;
import dagger.Provides;

@Module
public class HomePresenterModule {

    private HomeContract.View homeView;

    public HomePresenterModule(HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @Provides
    public HomeContract.View provideHomeView(){
        return homeView;
    }

}
