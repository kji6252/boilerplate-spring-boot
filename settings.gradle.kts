rootProject.name = "boilerplate-spring-boot"

include("domain")
include("application")
include("port-in", "port-out")
include("sample-app")
include("adapters:web-adapter")
findProject(":adapters:web-adapter")?.name = "web-adapter"
include("adapters:persistence-adapter")
findProject(":adapters:persistence-adapter")?.name = "persistence-adapter"
