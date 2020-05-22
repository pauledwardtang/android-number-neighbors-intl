package io.phatcat.numberneighborsinternational.network.di

import dagger.Component
import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.input.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.data.di.DataModule
import io.phatcat.numberneighborsinternational.di.DomainModule
import io.phatcat.numberneighborsinternational.persistence.di.PersistenceModule
import io.phatcat.numberneighborsinternational.serialization.di.SerializationModule
import javax.inject.Singleton

@Component(
  modules = [
    DomainModule::class,
    DataModule::class,
    NetworkModule::class,
    PersistenceModule::class,
    SerializationModule::class
  ]
)
@Singleton
interface ApplicationComponent {

  // Next step is to inject these use cases directly to the classes that need them
  val getCountriesUseCase: GetCountriesUseCase
  val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase

}
