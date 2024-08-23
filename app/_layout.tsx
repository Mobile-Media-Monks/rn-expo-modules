import { Entypo, Feather } from "@expo/vector-icons";
import { Stack, Tabs } from "expo-router";
import { theme } from "@/constants/theme";

export default function Layout() {
  return (
    <Stack>
      <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
      <Stack.Screen
        name="onboarding"
        options={{
          presentation: "modal",
          headerShown: false,
        }}
      />
    </Stack>
  );
}
