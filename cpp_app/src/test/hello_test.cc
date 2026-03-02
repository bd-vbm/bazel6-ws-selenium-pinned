#include <gtest/gtest.h>
#include "cpp_app/src/main/hello.h"
TEST(HelloJsonTest,Basic){ EXPECT_EQ(hello_json("World"), std::string("Hello, World!")); }
