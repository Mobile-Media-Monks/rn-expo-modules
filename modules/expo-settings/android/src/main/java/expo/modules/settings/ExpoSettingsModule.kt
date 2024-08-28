package expo.modules.settings

import android.content.Context
import android.net.wifi.WifiManager
import android.os.BatteryManager
import expo.modules.kotlin.Promise
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoSettingsModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("ExpoSettings")

    Function("hello") {
      "Hello world! 👋"
    }

    AsyncFunction("asyncFunction") { promise: Promise ->
      try {
        val bm = appContext.reactContext?.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        promise.resolve(batLevel)
      } catch (e: Throwable) {
        promise.reject("Error", e.message, null)
      }
    }

    AsyncFunction("getWifi") { promise: Promise ->
      try {
        val wifi = appContext.reactContext?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiEnable = wifi.isWifiEnabled
        promise.resolve(wifiEnable)
      } catch (e: Throwable) {
        promise.reject("Error", e.message, null)
      }
    }
  }
}
