scalaVersion := "2.12.12"
Compile / scalaSource := baseDirectory.value / "src"
Test / scalaSource := baseDirectory.value / "test"

lazy val proyecto = (project in file(".")).settings(
    name := "Easy Warehouse")


