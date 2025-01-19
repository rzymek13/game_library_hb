package com.prtech.game_library_hb.handballTest;

import com.prtech.game_library_hb.handballTest.rest_assured.CreateTeamTest;
import com.prtech.game_library_hb.handballTest.rest_assured.GetTeamsTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.engine.discovery.DiscoverySelectors;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestRunnerBuilder {

    private final List<Class<?>> testClasses = new ArrayList<>();
    private final List<Method> testMethods = new ArrayList<>();

    public TestRunnerBuilder addTestClass(Class<?> testClass) {
        testClasses.add(testClass);
        return this;
    }

    public TestRunnerBuilder addTestMethod(Class<?> testClass, String methodName) {
        try {
            Method method = testClass.getDeclaredMethod(methodName);
            testMethods.add(method);
        } catch (NoSuchMethodException e) {
            log.error("Test method not found: " + methodName, e);
        }
        return this;
    }

    public void run() {
        Launcher launcher = LauncherFactory.create();

        for (Class<?> testClass : testClasses) {
            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectClass(testClass))
                    .build();
            launcher.execute(request);
        }

        for (Method method : testMethods) {
            LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                    .selectors(DiscoverySelectors.selectMethod(method.getDeclaringClass(), method))
                    .build();
            launcher.execute(request);
        }
    }

    public static void main(String[] args) {
        new TestRunnerBuilder()
                .addTestClass(GetTeamsTest.class)
                .addTestClass(CreateTeamTest.class)
//                .addTestMethod(GetTeamsTest.class, "getAllteamsTest")
                .run();
    }
}