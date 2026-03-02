
package(default_visibility = ["//visibility:public"]) 

cc_library(
    name = "lib",
    srcs = ["src/main/hello.cc"],
    hdrs = ["src/main/hello.h"],
    deps = ["@nlohmann_json//:json"],
)

cc_binary(
    name = "hello",
    srcs = ["src/main/main.cc"],
    deps = [":lib"],
)

cc_test(
    name = "hello_test",
    srcs = ["src/test/hello_test.cc"],
    deps = [":lib","@googletest//:gtest_main"],
)
