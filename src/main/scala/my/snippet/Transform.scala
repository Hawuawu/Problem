package my.snippet



import xml.NodeSeq
import net.liftweb._
import http.RequestVar
import net.liftweb.util._
import Helpers._

/**
 * Created by IntelliJ IDEA.
 * User: Dan
 * Date: 5/31/11
 * Time: 10:27 PM
 */
class Transform{

  case class Projects(projectName: String, runningTime: Int)

  val projects = for(i <- 0 to 10) yield Projects("Project " + i, i)

  def render = "#Projects *" #> projects.map(project => "#ProjectName *" #> project.projectName & "#RunningTime *" #> project.runningTime)

}


