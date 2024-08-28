import { Text, View, StyleSheet, Button } from "react-native";
import { theme } from "@/constants/theme";
import { useUserStore } from "@/store/userStore";
import { PlantlyButton } from "@/components/PlantlyButton";
import { useEffect, useState } from "react";
import { getBatteryAsync, getWifiAsync } from "@/modules/expo-settings";

export default function ProfileScreen() {
  const toggleHasOnboarded = useUserStore((store) => store.toggleHasOnboarded);
  const [battery, setBattery] = useState("");

  useEffect(() => {
    getBatteryAsync()
      .then(setBattery)
      .catch((error) => {
        console.log("error", error);
      });

    getWifiAsync()
      .then((res) => console.log("res", res))
      .catch((error) => {
        console.log("error", error);
      });
  }, []);

  return (
    <View style={styles.container}>
      {battery && <Text style={styles.text}>Battery Level: {battery}%</Text>}
      <PlantlyButton title="Back to onboarding" onPress={toggleHasOnboarded} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: theme.colorWhite,
  },
  text: {
    fontSize: 19,
    marginBottom: 15,
  },
});
