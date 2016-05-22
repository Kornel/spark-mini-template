package org.kornel.spark.mini

import com.databricks.spark.avro._
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{Logging, SparkConf, SparkContext}


object SparkMini extends Logging {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Adv Attribution")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val df = sqlContext.read.avro("/test/something")

    val select = df.select($"a.b", $"a.c", $"x")

    select.write.format("parquet").save("/tmp/test-df")

    sc.stop()
  }
}
