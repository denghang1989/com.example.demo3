package com.example.demo3.ioc.component;

import com.example.demo3.fragment.HomeFragment;
import com.example.demo3.fragment.PdfFragment;
import com.example.demo3.ioc.scope.PreFragment;

import dagger.Component;

@PreFragment
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {

    void inject(HomeFragment fragment);

    void inject(PdfFragment fragment);
}
