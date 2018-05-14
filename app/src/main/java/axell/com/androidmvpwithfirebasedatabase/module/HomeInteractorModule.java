package axell.com.androidmvpwithfirebasedatabase.module;

import axell.com.androidmvpwithfirebasedatabase.contract.HomeContract;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeInteractorModule {

    private HomeContract.Presenter homePresenter;

    public HomeInteractorModule(HomeContract.Presenter homePresenter) {
        this.homePresenter = homePresenter;
    }

    @Provides
    public HomeContract.Presenter provideHomePresenter(){
        return homePresenter;
    }

}
