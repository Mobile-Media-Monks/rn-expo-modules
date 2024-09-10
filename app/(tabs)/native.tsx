import { Text, View, StyleSheet, Platform } from "react-native";
import { theme } from "@/constants/theme";
import { ExpoViewDeclarativeView } from "@/modules/expo-view";

export default function ProfileScreen() {
  return (
    <View style={styles.container}>
      {Platform.OS === "android" ? (
        <ExpoViewDeclarativeView style={{ flex: 1, width: "100%" }} />
      ) : (
        <Text>Available on Android</Text>
      )}
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
});
