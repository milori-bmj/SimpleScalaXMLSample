import scala.collection.mutable.ListBuffer

object DoiArticle extends App{

  import scala.collection.mutable.Map
  import io.Source
  var mapDois:Map[String, ListBuffer[String]] = Map[String,ListBuffer[String]]()
  Source.fromFile("C:\\bmj-journals\\bmjj.source.xml.listing\\sortedByDoi.csv").getLines.filter(!_.startsWith(",")).foreach(line => {
    //Source.fromFile("C:\\bmj-journals\\bmjj.source.xml.listing\\sortedByDoi.csv").getLines.take(100).filter(!_.startsWith(",")).foreach(line => {

      val keyVal = line.split(",")
    val key = keyVal(0)
    val value = keyVal(1)
    if(mapDois.contains(key)) mapDois(key) += value else mapDois += (key->ListBuffer(value))
  })
  ;
  //mapDois.foreach({ case (key,value) if value.length > 1 => println(key + "->" +value)})
  println("records :=" + mapDois.keySet.size)
  println("dois with > 1 residence :=" + mapDois.filter({ case (key,value) => value.size > 1}).size)
  mapDois.filter({ case (key,value) => value.size > 1}).take(100).foreach({ case (key,value) => println(key + "->" +value)})


}