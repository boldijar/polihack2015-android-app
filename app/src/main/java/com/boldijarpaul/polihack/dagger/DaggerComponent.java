package com.boldijarpaul.polihack.dagger;


import com.boldijarpaul.polihack.prefs.PrefsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DaggerAppModule.class, DebugDataModule.class, PrefsModule.class})
public interface DaggerComponent extends DaggerGraph {

    final class Initializer {
        static DaggerGraph init(DaggerApp app) {
            return DaggerDaggerComponent.builder()
                    .daggerAppModule(new DaggerAppModule(app))
                    .build();
        }

        private Initializer() {
        } // No instances
    }
}

