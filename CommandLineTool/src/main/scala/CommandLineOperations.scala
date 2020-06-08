import com.typesafe.config.ConfigFactory
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
object CommandLineOperations {
  def main(args:Array[String]){
    val column = args(1)
    if(args(0)=="Mean") {
      val location = ConfigFactory.load().getString("my.file.location")
      val bufferedSource = Source.fromFile(location)
      var columnValue = ArrayBuffer[Int]()
      for (header <-  bufferedSource.getLines().take(1)) {
        def GetHeader:Array[String] = header.split(",").map(_.trim)
        for(i <- 0 until GetHeader.length) {
          if(GetHeader(i)==column)
            for (line <- bufferedSource.getLines) {
              def cols:Array[String] = line.split(",")
              println(cols(i))
              columnValue += cols(i).toInt  }     } }
        val mean = (columnValue.sum/columnValue.length)
      println(columnValue.sum,"sum")
      println(columnValue.length,"length")
      println(s"mean of column $column is:  $mean")
      bufferedSource.close
    }

  }}



























