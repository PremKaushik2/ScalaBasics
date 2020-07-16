package spark.rdd

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
object CombineByKey extends App {
  System.setProperty("hadoop.home.dir", "C:/winutils");
  val logFile = "D:/Spark_VM/emp.txt" // Should be some file on your system
  val spark = new SparkConf().setAppName("Simple Application").
    setMaster(("local[*]")).
    set("spark.executor.memory", "1g") // 4 workers
    .set("spark.executor.instances", "1")
    // 5 cores on each workers
    .set("spark.executor.cores", "5");
  val sparksession: SparkSession = SparkSession.builder.master("local").config(spark).getOrCreate()
  val sparkContext = sparksession.sparkContext
  type ScoreCollector = (Int, Double)
  type PersonScores = (String, (Int, Double))

  val initialScores = Array(("Fred", 88.0), ("Fred", 95.0), ("Fred", 91.0), ("Wilma", 93.0), ("Wilma", 95.0), ("Wilma", 98.0))

  val wilmaAndFredScores = sparkContext.parallelize(initialScores).cache()

  val createScoreCombiner = (score: Double) => (1, score)

  val scoreCombiner: ((Int, Double), Double) => (Int, Double) = (collector: (Int, Double), score: Double) => {

    (collector._1 + 1, collector._2 + score)
  }

  val scoreMerger = (collector1: ScoreCollector, collector2: ScoreCollector) => {
    val (numScores1, totalScore1) = collector1
    val (numScores2, totalScore2) = collector2
    (numScores1 + numScores2, totalScore1 + totalScore2)
  }
  val scores = wilmaAndFredScores.combineByKey(createScoreCombiner, scoreCombiner, scoreMerger)

  val averagingFunction = (personScore: (String, (Int, Double))) => {
    (personScore._1, personScore._2._2 / personScore._2._1)
  }

  val averageScores = scores.map(averagingFunction).collectAsMap()

  println("Average Scores using CombingByKey")
  averageScores.foreach((ps) => {
    val (name, average) = ps
    println(name + "'s average score : " + average)
  })

}