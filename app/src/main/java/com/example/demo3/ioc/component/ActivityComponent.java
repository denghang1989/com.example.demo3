package com.example.demo3.ioc.component;

import com.example.demo3.activity.MainActivity;
import com.example.demo3.ioc.scope.PreActivity;

import dagger.Component;

@PreActivity
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

}
