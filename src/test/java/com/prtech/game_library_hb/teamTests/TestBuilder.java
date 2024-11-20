package com.prtech.game_library_hb.teamTests;
import com.prtech.game_library_hb.teamTests.api_test.TeamTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.engine.discovery.DiscoverySelectors;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class TestBuilder {

    private static Class<?> testClass;

    public TestBuilder(Class<?> testClass) {
        this.testClass = testClass;
    }

    public void runAllTests() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(testClass))
                .build();

        Launcher launcher = LauncherFactory.create();
        launcher.execute(request);
    }

    public void runSelectedTest(String testMethodName) {
        try {
            Method method = testClass.getDeclaredMethod(testMethodName);

            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectMethod(testClass, method))
                    .build();

            Launcher launcher = LauncherFactory.create();
            launcher.execute(request);
        } catch (NoSuchMethodException e) {
            System.out.println("Nie znaleziono metody testowej: " + testMethodName);
        }
    }

    public static void main(String[] args) {
        TestBuilder testBuilder = new TestBuilder(TeamTest.class);
        testBuilder.runAllTests();
        log.info(Arrays.toString(testClass.getDeclaredMethods()));

        testBuilder.runSelectedTest("getAllTeams");
    }
}
