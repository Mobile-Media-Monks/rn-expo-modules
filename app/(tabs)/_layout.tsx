import { theme } from "@/constants/theme";
import { Entypo, Feather } from "@expo/vector-icons";
import { Redirect, Tabs } from "expo-router";
import { useUserStore } from "@/store/userStore";

const ROUTES: {
  name: string;
  title: "Home" | "Profile" | "NativeView";
}[] = [
  {
    name: "(home)",
    title: "Home",
  },
  {
    name: "profile",
    title: "Profile",
  },
  {
    name: "native",
    title: "NativeView",
  },
];

function getComponentByRouteName({
  routeName,
  ...props
}: {
  routeName: (typeof ROUTES)["0"]["title"];
  color: string;
  size: number;
}) {
  switch (routeName) {
    case "Home":
      return <Entypo name="leaf" {...props} />;
    case "Profile":
      return <Feather name="activity" {...props} />;
    case "NativeView":
      return <Feather name="monitor" {...props} />;
  }
}

export default function Layout() {
  const hasFinishedOnboarding = useUserStore(
    (state) => state.hasFinishedOnboarding
  );

  if (!hasFinishedOnboarding) {
    return <Redirect href="/onboarding" />;
  }

  return (
    <Tabs screenOptions={{ tabBarActiveTintColor: theme.colorGreen }}>
      {ROUTES.map((item, index) => {
        return (
          <Tabs.Screen
            name={item.name}
            options={{
              title: item.title,
              tabBarShowLabel: false,
              headerShown: false,
              tabBarIcon: ({ size, color }) =>
                getComponentByRouteName({ routeName: item.title, size, color }),
            }}
          />
        );
      })}
    </Tabs>
  );
}
