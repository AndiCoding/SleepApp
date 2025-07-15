import cache.Alarm
import cache.Database
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper: KoinComponent {
    private val sdk: Database by inject<Database>()

    fun getAllAlarms(): List<org.sleepapp.data.model.Alarm> {
        return sdk.getAllAlarms()
    }


}