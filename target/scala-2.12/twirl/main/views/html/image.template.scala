
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object image extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[String,List[PicsumImage],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(imageUrl: String, imagesData: List[PicsumImage]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.51*/("""

"""),_display_(/*3.2*/main("Disneyland Paris - Java Technical Test")/*3.48*/ {_display_(Seq[Any](format.raw/*3.50*/("""
  """),format.raw/*4.3*/("""<h1>Welcome!</h1>
  """),_display_(/*5.4*/if(imageUrl != null && !"".equals(imageUrl.trim()))/*5.55*/ {_display_(Seq[Any](format.raw/*5.57*/("""
	  """),format.raw/*6.4*/("""<h2>Simple Image<h2>
	  <img src=""""),_display_(/*7.15*/imageUrl),format.raw/*7.23*/("""" alt="Image from picsum" height="300" width="200">
  """)))}),format.raw/*8.4*/("""
  
  """),_display_(/*10.4*/if(imagesData != null && !imagesData.isEmpty())/*10.51*/ {_display_(Seq[Any](format.raw/*10.53*/("""
	  """),format.raw/*11.4*/("""<h2>Multiple Images</h2>
	  """),_display_(/*12.5*/for(imageData <- imagesData) yield /*12.33*/ {_display_(Seq[Any](format.raw/*12.35*/("""
		"""),format.raw/*13.3*/("""<img src=""""),_display_(/*13.14*/imageData/*13.23*/.getDownloadUrl()),format.raw/*13.40*/("""" alt=""""),_display_(/*13.48*/imageData/*13.57*/.getAuthor()),format.raw/*13.69*/("""" height="300" width="200">
	  """)))}),format.raw/*14.5*/("""
  """)))}),format.raw/*15.4*/("""
""")))}),format.raw/*16.2*/("""
"""))
      }
    }
  }

  def render(imageUrl:String,imagesData:List[PicsumImage]): play.twirl.api.HtmlFormat.Appendable = apply(imageUrl,imagesData)

  def f:((String,List[PicsumImage]) => play.twirl.api.HtmlFormat.Appendable) = (imageUrl,imagesData) => apply(imageUrl,imagesData)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Dec 11 18:59:51 CET 2019
                  SOURCE: E:/Programmation/testTechnique/dlp/dlp-test/app/views/image.scala.html
                  HASH: b55bef145d1fab02fc399ad91c21b45bdf615ea6
                  MATRIX: 966->1|1110->50|1140->55|1194->101|1233->103|1263->107|1310->129|1369->180|1408->182|1439->187|1501->223|1529->231|1614->287|1649->296|1705->343|1745->345|1777->350|1833->380|1877->408|1917->410|1948->414|1986->425|2004->434|2042->451|2077->459|2095->468|2128->480|2191->513|2226->518|2259->521
                  LINES: 28->1|33->1|35->3|35->3|35->3|36->4|37->5|37->5|37->5|38->6|39->7|39->7|40->8|42->10|42->10|42->10|43->11|44->12|44->12|44->12|45->13|45->13|45->13|45->13|45->13|45->13|45->13|46->14|47->15|48->16
                  -- GENERATED --
              */
          