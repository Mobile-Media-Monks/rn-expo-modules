import ExpoSettingsModule from "./src/ExpoSettingsModule";

export async function getBatteryAsync(): Promise<string> {
  return await ExpoSettingsModule.asyncFunction();
}

export async function getWifiAsync(): Promise<string> {
  return await ExpoSettingsModule.getWifi();
}
