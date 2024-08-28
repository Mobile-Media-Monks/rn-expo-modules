const getBundleId = () => {
  if (process.env.APP_VARIANT === "production") {
    return "com.ebwave.plantly";
  }

  if (process.env.APP_VARIANT === "preview") {
    return "com.ebwave.plantly.preview";
  }

  if (process.env.APP_VARIANT === "development") {
    return "com.ebwave.plantly.dev";
  }

  return "com.ebwave.plantly.simulator";
};

const bundleID = getBundleId();

const getAppName = () => {
  if (process.env.APP_VARIANT === "production") {
    return "Planty";
  }

  if (process.env.APP_VARIANT === "preview") {
    return "Planty ( Preview )";
  }

  if (process.env.APP_VARIANT === "development") {
    return "Planty ( Dev )";
  }

  return "Planty ( Emu )";
};

const nameApp = getAppName();

const getAndroidIcon = () => {
  if (process.env.APP_VARIANT === "production") {
    return "./assets/adaptive-icon.png";
  }

  if (process.env.APP_VARIANT === "preview") {
    return "./assets/android-icon/adaptive-icon-prev.png";
  }

  if (process.env.APP_VARIANT === "development") {
    return "./assets/android-icon/adaptive-icon-dev.png";
  }

  return "./assets/android-icon/adaptive-icon-emu.png";
};

const androidIcon = getAndroidIcon();

export default {
  expo: {
    name: nameApp,
    slug: "plantly",
    scheme: "plantly",
    version: "1.0.0",
    orientation: "portrait",
    icon: "./assets/icon.png",
    userInterfaceStyle: "light",
    splash: {
      image: "./assets/splash.png",
      resizeMode: "contain",
      backgroundColor: "#ffffff",
    },
    ios: {
      supportsTablet: true,
      bundleIdentifier: bundleID,
    },
    android: {
      adaptiveIcon: {
        foregroundImage: androidIcon,
        backgroundColor: "#ffffff",
      },
      package: bundleID,
      permissions: ["android.permission.ACCESS_WIFI_STATE"],
    },
    web: {
      favicon: "./assets/favicon.png",
      bundler: "metro",
    },
    plugins: ["expo-router"],
    extra: {
      router: {
        origin: false,
      },
      eas: {
        projectId: "606149f6-7bc1-4343-9c1b-738455247b8f",
      },
    },
  },
};
