// @GENERATOR:play-routes-compiler
// @SOURCE:E:/Programmation/testTechnique/dlp/dlp-test/conf/routes
// @DATE:Wed Dec 11 18:06:51 CET 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
