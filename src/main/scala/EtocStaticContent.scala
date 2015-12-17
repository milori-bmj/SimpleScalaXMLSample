import scala.io.Source
import scala.xml.{XML,Elem,Node}
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

object EtocStaticContent extends App
{
  val xml = scala.xml.XML.load("C:\\html\\etoc-static.html")

  val lines = Source.fromFile("C:\\html\\etoc-static.html").getLines
  val output = lines map({ case s if s contains """<div id="etocs"/>""" =>  s replace ("""<div id="etocs"/>""","<yo/>") case st => st case _ =>})

  //val etocPlaceHolder = xml \\ "table" filter ( z => {z \ ("@class") text == "contenttable"})

  output.foreach(println (_))
  //val etocPlaceHolder = xml \\ "div" filter ( x => {println ((x \ "@id") text); true})


}