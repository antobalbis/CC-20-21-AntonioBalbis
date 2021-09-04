import mill._
import scalalib._

object eWarehouse extends Cross[eWarehouseModule]("3.0.1", "2.13.6","2.12.8", "2.12.1")
class eWarehouseModule(val crossScalaVersion: String) extends CrossScalaModule{
  var cask_version = "0.2.9"
  if(crossScalaVersion.equals("3.0.1")) cask_version = "0.7.11"
  if(crossScalaVersion.equals("2.13.6")) cask_version = "0.7.8"


  def ivyDeps = Agg(ivy"com.lihaoyi::cask:$cask_version", ivy"com.lihaoyi::upickle:1.4.0")

  object test extends Tests{
    def ivyDeps = Agg(
      ivy"org.scalatest::scalatest:3.2.9",
      ivy"com.lihaoyi::requests:0.6.9"
    )

    def testFramework = "org.scalatest.tools.Framework"
  }
}
