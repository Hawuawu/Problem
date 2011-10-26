package my.snippet



import xml.NodeSeq
import net.liftweb._
import http.RequestVar
import net.liftweb.util._
import Helpers._
import scala.util.Random


/**
 * Created by IntelliJ IDEA.
 * User: Dan
 * Date: 5/31/11
 * Time: 10:27 PM
 */
class Transform{
  val random = new Random(12345)

    case class Calls(number: String, state: String)
    case class Projects(projectName: String, runningTime: Int, called:Int, connected: Int,
                        notavaible:Int,bsy:Int,dnt:Int,others:Int,successful: Int, calls:List[Calls])

    val projects = for(i <- 0 to 5) yield{
      val calls = for(p <- 0 to (1 + random.nextInt(10)))yield Calls(p.toString,"someState")
      Projects("Project" + i, random.nextInt(10),random.nextInt(10),random.nextInt(10),random.nextInt(10),random.nextInt(10),random.nextInt(10),
      random.nextInt(10),random.nextInt(10),calls.toList)
    }

    def render = "#Projects " #> projects.map(project =>
      "#ProjectName *" #> project.projectName &
      "#RunningTime *" #> project.runningTime &
      "#Called *" #> project.called      &
      "#Cnt *" #> project.connected   &
      "#NA *" #> project.notavaible  &
      "#Bsy *" #> project.bsy         &
      "#Dnt *" #> project.dnt         &
      "#Others *" #> project.others      &
      "#Successful *" #> project.successful   &
      "#ImageState [href]" #> "" &
      "#History [href]" #> "" &
      "#Setting [href]" #> "" &
      "#Calls " #> project.calls.map(call =>
          "#Number *" #> call.number &
          "#State *"  #> call.state
      )
    )
}


