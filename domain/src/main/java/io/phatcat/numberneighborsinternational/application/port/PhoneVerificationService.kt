package io.phatcat.numberneighborsinternational.application.port

import io.phatcat.numberneighborsinternational.application.port.input.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.application.port.output.GetPhoneNumberVerificationPort
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.Phone
import io.phatcat.numberneighborsinternational.entity.PhoneNumber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhoneVerificationService @Inject constructor(
  private val getPhoneNumberVerificationPort: GetPhoneNumberVerificationPort
) : GetPhoneNumberResultsUseCase {

  override suspend fun getPhoneNumbers(phoneNumber: String, countries: List<Country>): List<Phone> {
    return try {
      val results = mutableListOf<Phone>()

      // If processing any of the countries fails, abort the entire procedure
      coroutineScope {
        countries.forEach { country ->
          launch(Dispatchers.IO) {
            val result =
              getPhoneNumberVerificationPort.getPhoneVerification(PhoneNumber(phoneNumber), country)
            results += result
          }
        }
      }
      results
    } catch (e: Exception) {
      e.printStackTrace()
      throw e
    }
  }

}
