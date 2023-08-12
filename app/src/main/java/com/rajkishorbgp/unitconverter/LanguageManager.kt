import android.content.Context
import android.content.SharedPreferences
import java.util.Locale

@Suppress("DEPRECATION")
class LanguageManager(private val context: Context) {
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("LanguagePrefs", Context.MODE_PRIVATE)

    private fun getSelectedLanguage(): String {
        return sharedPrefs.getString("selectedLanguage", "en") ?: "en"
    }

    fun setSelectedLanguage(languageCode: String) {
        sharedPrefs.edit().putString("selectedLanguage", languageCode).apply()
    }

    fun applyLanguage() {
        val languageCode = getSelectedLanguage()
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }
}
