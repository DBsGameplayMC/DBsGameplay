rootProject.name = "DBsGameplayPaper"

include(":DBsGameplayPaperCore")
include(":DBsGameplayBlockBreaker")
include(":DBsGameplayDungeonCrusher")
include(":DBsGameplaySkyBlock")

include(":DBsGameplayCore")
project(":DBsGameplayCore").projectDir = File("../DBsGameplayCore")
include("DBsGameplayAdminTool")
