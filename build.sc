import mill._, scalalib._

object eWarehouse extends ScalaModule{
  def scalaVersion = "2.12.12"

  object test extends Tests {
    def iviDeps = Agg(ivy"org.scalatest::scalatest::3.2.9")
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
}
