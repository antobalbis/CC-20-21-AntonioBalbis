import mill._
import mill.define.Target
import scalalib._

object eWarehouse extends Cross[eWarehouseModule]("3.0.1", "2.13.6","2.12.8", "2.12.1")
class eWarehouseModule(val crossScalaVersion: String) extends CrossScalaModule{

  def ivyDeps = Agg(ivy"com.lihaoyi::cask:0.7.11")

  object test extends Tests{
    def ivyDeps = Agg(
      ivy"org.scalatest::scalatest:3.2.9"
      //ivy"com.lihaoyi::requests:0.6.5"
    )

    def testFramework = "org.scalatest.tools.Framework"
  }
}
