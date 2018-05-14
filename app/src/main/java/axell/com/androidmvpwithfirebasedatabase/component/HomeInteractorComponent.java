package axell.com.androidmvpwithfirebasedatabase.component;

import axell.com.androidmvpwithfirebasedatabase.interactor.HomeInteractorImpl;
import axell.com.androidmvpwithfirebasedatabase.module.HomeInteractorModule;
import dagger.Component;

@Component(modules = {HomeInteractorModule.class})
public interface HomeInteractorComponent {
    HomeInteractorImpl provideHomeInteractor();
}
