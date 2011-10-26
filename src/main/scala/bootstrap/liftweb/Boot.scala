package bootstrap.liftweb

import net.liftweb._
import http.{LiftRules, NotFoundAsTemplate, ParsePath, Req, XmlResponse}
import http.rest.RestContinuation
import sitemap.{SiteMap, Menu, Loc}
import util.{ NamedPF }


class Boot {
  def boot {
  
  
    // where to search snippet
    LiftRules.addToPackages("my")

    // build sitemap
    val entries = List(Menu("Home") / "index") :::
                  Nil
    
    LiftRules.uriNotFound.prepend(NamedPF("404handler"){
      case (req,failure) => NotFoundAsTemplate(
        ParsePath(List("exceptions","404"),"html",false,false))
    })
    
    LiftRules.setSiteMap(SiteMap(entries:_*))
    
    // set character encoding
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    

    LiftRules.dispatch.append{
      case Req("test_cont" :: _,_,_) =>
      RestContinuation.async {
        satisfy => {
          satisfy(XmlResponse(<tag>ok</tag>))
        }
      }
    }
  }
}