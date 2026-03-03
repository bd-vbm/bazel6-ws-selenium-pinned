
workspace(name = "bazel6_ws_example")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# --- rules_jvm_external (for Maven/TestNG + Selenium) ---
RULES_JVM_EXTERNAL_TAG = "6.6"
RULES_JVM_EXTERNAL_SHA = "3afe5195069bd379373528899c03a3072f568d33bd96fe037bd43b1f590535e7"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    urls = [
        "https://github.com/bazel-contrib/rules_jvm_external/releases/download/%s/rules_jvm_external-%s.tar.gz"
        % (RULES_JVM_EXTERNAL_TAG, RULES_JVM_EXTERNAL_TAG),
    ],
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")
rules_jvm_external_deps()
load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")
rules_jvm_external_setup()
load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    name = "maven",
    artifacts = [
        "org.testng:testng:7.12.0",
        "org.seleniumhq.selenium:selenium-java:4.18.0",
    ],
    repositories = ["https://repo1.maven.org/maven2"],
    maven_install_json = "@//:maven_install.json",
)


load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()

# --- C++ Dependencies ---
http_archive(
    name = "googletest",
    urls = ["https://github.com/google/googletest/archive/refs/tags/v1.15.2.tar.gz"],
    strip_prefix = "googletest-1.15.2",
    sha256 = "<FILL_ME_GTEST_SHA256>",
)

http_archive(
    name = "nlohmann_json",
    urls = ["https://github.com/nlohmann/json/releases/download/v3.12.0/include.zip"],
    sha256 = "b8cb0ef2dd7f57f18933997c9934bb1fa962594f701cd5a8d3c2c80541559372",
    build_file_content = """
package(default_visibility=["//visibility:public"])

filegroup(
    name = "headers",
    srcs = glob(["include/**/*.hpp", "include/**/*.h"]),
)

cc_library(
    name = "json",
    hdrs = [":headers"],
    includes = ["include"],
)
""",
)


# Shell rules (rules_sh) — provides @rules_sh//sh:posix.bzl toolchain
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_sh",
    urls = ["https://github.com/tweag/rules_sh/archive/refs/tags/v0.5.0.tar.gz"],
    strip_prefix = "rules_sh-0.5.0",
    # Replace with the real SHA256 for the chosen release:
    sha256 = "f96b39c5a07d38424b48b5cc2e5f4b820f2877682a0488faa43f0948def95e28",
)

# Initialize rules_sh’s deps/toolchains for WORKSPACE mode
load("@rules_sh//sh:repositories.bzl", "rules_sh_dependencies")
rules_sh_dependencies()



# --- rules_python (Python core rules + toolchains) ---
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# Pick a released version; example shown with 0.36.0 from rules_python docs
# (Replace sha256 with the real value for the tar you're using.)
http_archive(
    name = "rules_python",
    url = "https://github.com/bazel-contrib/rules_python/releases/download/0.36.0/rules_python-0.36.0.tar.gz",
    strip_prefix = "rules_python-0.36.0",
    sha256 = "ca77768989a7f311186a29747e3e95c936a41dffac779aff6b443db22290d913",
)

# Initialize repositories + register a hermetic Python toolchain
load("@rules_python//python:repositories.bzl", "py_repositories", "python_register_toolchains")
py_repositories()

python_register_toolchains(
    name = "python_3_11",
    python_version = "3.11",  # use a version you want (e.g., 3.10/3.11/3.12)
    ignore_root_user_error = True,
)


# --- Protocol Buffers repo for WORKSPACE mode ---
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# Example: Protobuf v3.20.3 (choose any compatible version you prefer)
http_archive(
    name = "com_google_protobuf",
    urls = ["https://github.com/protocolbuffers/protobuf/releases/download/v3.20.3/protobuf-all-3.20.3.tar.gz"],
    strip_prefix = "protobuf-3.20.3",
    sha256 = "acb71ce46502683c31d4f15bafb611b9e7b858b6024804d6fb84b85750884208",
)

# Pull Protobuf's own WORKSPACE dependencies (rules_cc, rules_proto, etc.)
load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")
protobuf_deps()


# --- rules_go (required by Gazelle) ---
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "io_bazel_rules_go",
    urls = [
        "https://github.com/bazel-contrib/rules_go/releases/download/v0.51.0/rules_go-v0.51.0.zip",
        "https://mirror.bazel.build/github.com/bazel-contrib/rules_go/releases/download/v0.51.0/rules_go-v0.51.0.zip",
    ],
    sha256 = "0936c9bc3c4321ee372cb8f66dd972d368cb940ed01a9ba9fd7debcf0093f09b",
)

# --- bazel_gazelle (build file generator; rules_python gazelle examples load this) ---
http_archive(
    name = "bazel_gazelle",
    # choose a release; example below shows 0.30.0 (works on Bazel 6/7).
    urls = [
        "https://github.com/bazelbuild/bazel-gazelle/releases/download/v0.30.0/bazel-gazelle-v0.30.0.tar.gz",
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-gazelle/releases/download/v0.30.0/bazel-gazelle-v0.30.0.tar.gz",
    ],
    sha256 = "727f3e4edd96ea20c29e8c2ca9e8d2af724d8c7778e7923a854b2c80952bc405",
)

# Register Go toolchains & Gazelle deps (exactly as Gazelle’s examples show)
load("@io_bazel_rules_go//go:deps.bzl", "go_register_toolchains", "go_rules_dependencies")
load("@bazel_gazelle//:deps.bzl", "gazelle_dependencies")

go_rules_dependencies()
go_register_toolchains(version = "host")  # (you may pass version=... if you want a specific Go SDK)

gazelle_dependencies()


# --- rules_haskell (Haskell/Cabal support) ---
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# Choose a released commit/archive you’re comfortable with.
# (Example pins here; update to a version you want.)
http_archive(
    name = "rules_haskell",
    urls = ["https://github.com/tweag/rules_haskell/archive/refs/tags/v1.0.tar.gz"],
    strip_prefix = "rules_haskell-1.0",
    sha256 = "90720459a60f419618f7c9b283cac3c5c2d337de1f58b01633aaac706795a4bf",  # example
)

# Register Haskell toolchains from binary distributions (no Nix required).
# You can select a GHC version; rules_haskell supports multiple.
load("@rules_haskell//haskell:toolchain.bzl", "rules_haskell_toolchains")

rules_haskell_toolchains(
    # If you omit 'dist' and 'variant', a default is chosen per-host.
    version = "9.6.5",  # pick a GHC version appropriate for you
)


load("@rules_haskell//haskell:cabal.bzl", "stack_snapshot")

stack_snapshot(
    name = "stackage",
    snapshot = "lts-21.25",
    packages = [
        "text",
    ],
)


