package com.andro.unityplatformplugin;

import java.lang.reflect.Method;

public class AuthProviderExtention {

    public static AndroAuthProvider GetAuthProvider(String provider) {
        try {
            String className = "com.andro.unityplatformplugin." + provider.toLowerCase() + ".AuthProviderFactory";
            Class classType = GetClass(className);
            Method createMethod = classType.getMethod("create");
            if (createMethod != null) {
                return (AndroAuthProvider)createMethod.invoke((Object)null);
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    private static Class<?> GetClass(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
