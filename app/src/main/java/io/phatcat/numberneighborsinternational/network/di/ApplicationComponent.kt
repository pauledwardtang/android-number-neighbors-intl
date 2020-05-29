package io.phatcat.numberneighborsinternational.network.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import io.phatcat.numberneighborsinternational.AppApplication
import io.phatcat.numberneighborsinternational.MainActivity
import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.input.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.data.di.DataModule
import io.phatcat.numberneighborsinternational.di.DomainModule
import io.phatcat.numberneighborsinternational.network.di.ApplicationComponent.AppModule
import io.phatcat.numberneighborsinternational.persistence.di.PersistenceModule
import io.phatcat.numberneighborsinternational.serialization.di.SerializationModule
import javax.inject.Singleton

@Component(
  modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    DomainModule::class,
    DataModule::class,
    NetworkModule::class,
    PersistenceModule::class,
    SerializationModule::class
  ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<AppApplication> {

  // Next step is to inject these use cases directly to the classes that need them
  val getCountriesUseCase: GetCountriesUseCase
  val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase

  override fun inject(daggerApplication: AppApplication)

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): ApplicationComponent
  }

  @Module
  interface AppModule {
    @ContributesAndroidInjector
    fun contributesMainActivity(): MainActivity
  }

}
