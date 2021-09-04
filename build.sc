import mill._
import scalalib._

object eWarehouse extends Cross[eWarehouseModule]("3.0.1", "2.13.6","2.13.1")
class eWarehouseModule(val crossScalaVersion: String) extends CrossScalaModule{
  var cask_version = "0.7.8"
  if(crossScalaVersion.contains("3.0.")) cask_version = "0.7.11"

  var upickle_version = "1.4.0"

  def ivyDeps = Agg(ivy"com.lihaoyi::cask:$cask_version", ivy"com.lihaoyi::upickle:$upickle_version")

  object test extends Tests{
    var requests = "0.6.9"

    def ivyDeps = Agg(ivy"org.scalatest::scalatest:3.2.9", ivy"com.lihaoyi::requests:$requests")
    def testFramework = "org.scalatest.tools.Framework"
  }
}
