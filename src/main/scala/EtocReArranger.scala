import scala.io.Source
import scala.xml.{XML,Elem,Node}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

object EtonReArranger extends App
{

  val etocGroups = Map[String,ListBuffer[Node]]()
  val xml = scala.xml.XML.load("C:\\data\\etocs\\output.xml")
  val etocEntries = xml \\ "etocsEntriesContainer" \"etocsEntry"

  etocEntries.foreach( etocEntry =>
  {
    val articleType = etocEntry \ "articleType" text

    if(etocGroups.contains(articleType)) etocGroups(articleType) += etocEntry else etocGroups += (articleType->ListBuffer(etocEntry))

  }
  )

  def buildEtocHtml (node:Node): Node =
  {
    val authors = node \\ "authors" map {_ text}
    val firstParagraph = node \\ "firstParagraph" text
    val title = node \\ "title" text
    val uri = node \\ "bmjUri" text

    <li>
      <p>{firstParagraph}</p>
      <a href={uri}>{title}</a>
      <p>{authors mkString(",")}</p>
    </li>
  }

  def buildEtocSection (section:String,sectionEtocs :ListBuffer[Node]): Node =
  {
   <div>
    <h1>{section}</h1>
    <ul>
    {sectionEtocs.map ( node =>
        buildEtocHtml(node)
    )}
    </ul>
   </div>
  }

  def buildAllEtocSections: Elem =
  {
    <html>
      <body>
        {etocGroups.toList.map( z => buildEtocSection(z._1,z._2))}
        </body>
      </html>
  }
 //etocGroups.foreach(println(_))

  println(buildAllEtocSections)


}