#include "cpp_app/src/main/hello.h"
#include <nlohmann/json.hpp>
using nlohmann::json;
std::string hello_json(const std::string& name){ json j={{"greet","Hello"},{"target",name}}; return j["greet"].get<std::string>()+", "+j["target"].get<std::string>()+"!"; }
