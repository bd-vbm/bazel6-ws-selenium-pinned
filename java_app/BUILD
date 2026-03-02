
package(default_visibility = ["//visibility:public"]) 

java_library(
    name = "lib",
    srcs = ["src/main/java/com/example/App.java"],
    deps = [
        "@maven//:org_testng_testng",
        "@maven//:org_seleniumhq_selenium_selenium_java",
    ],
)

java_binary(
    name = "app",
    main_class = "com.example.App",
    runtime_deps = [":lib"],
)

java_test(
    name = "AppTestNg",
    srcs = ["src/test/java/com/example/AppTestNg.java"],
    deps = [
        ":lib",
        "@maven//:org_testng_testng",
    ],
    use_testrunner = False,
    main_class = "org.testng.TestNG",
    args = ["-testclass", "com.example.AppTestNg"],
)

java_test(
    name = "SeleniumSmokeTest",
    srcs = ["src/test/java/com/example/SeleniumSmokeTest.java"],
    deps = [
        ":lib",
        "@maven//:org_testng_testng",
        "@maven//:org_seleniumhq_selenium_selenium_java",
    ],
    use_testrunner = False,
    main_class = "org.testng.TestNG",
    args = ["-testclass", "com.example.SeleniumSmokeTest"],
)
