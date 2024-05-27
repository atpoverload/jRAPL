java_library(
    name = "emissions",
    visibility = ["//visibility:public"],
    srcs = glob(["*.java"]),
    deps = [
        "//src/jcarbon/src/main/java/jcarbon/cpu/eflect",
        "//src/jcarbon/src/main/java/jcarbon/cpu/rapl",
        "//src/jcarbon/src/main/java/jcarbon/data",
        "//src/jcarbon/src/main/java/jcarbon/util",
    ],
    resources = ["//src/jcarbon/src/main/resources/emissions:emissions_data"],
)

java_binary(
    name = "carbon_locale_generator",
    main_class = "jcarbon.emissions.CarbonLocaleGenerator",
    runtime_deps = [":emissions"],
)

java_binary(
    name = "generated_locale",
    main_class = "jcarbon.emissions.CarbonLocale",
    runtime_deps = [":emissions"],
)

java_binary(
    name = "test_loc",
    main_class = "jcarbon.emissions.Testing",
    runtime_deps = [":emissions"],
)