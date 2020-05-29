package io.phatcat.numberneighborsinternational

import dagger.android.DaggerApplication
import io.phatcat.numberneighborsinternational.network.di.DaggerApplicationComponent

class AppApplication : DaggerApplication() {

  override fun applicationInjector() = DaggerApplicationComponent.factory().create(this)

}
