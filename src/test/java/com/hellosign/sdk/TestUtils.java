package com.hellosign.sdk;

import java.io.File;
import java.net.URL;

public class TestUtils {

    public static File getTestFixture(Class<?> testClass, String name) {
        String path = System.getProperty("file.separator") + testClass.getSimpleName()
            + System.getProperty("file.separator") + "Fixtures" + System
            .getProperty("file.separator") + name;
        URL resource = testClass.getResource(path);
        return new File(resource.getFile());
    }
}
