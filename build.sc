import mill._, scalalib._

object eWarehouse extends ScalaModule{
  def scalaVersion = "2.13.2"
  object test extends Tests{
    def ivyDeps = Agg(ivy"org.scalatest::scalatest:3.1.1")
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
}
