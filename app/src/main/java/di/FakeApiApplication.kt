package di

import android.app.Application
import di.components.DaggerFakeApiComponent

class FakeApiApplication: Application() {
    val component by lazy {
        DaggerFakeApiComponent.factory().create(this)
    }
}