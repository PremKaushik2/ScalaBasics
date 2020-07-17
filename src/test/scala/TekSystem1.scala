object TekSystem1 {


  def main(args: Array[String]): Unit = {
    // read the capacity and number of stops
    val line = scala.io.StdIn.readLine()
    val Array(totalCapacityOfTrain, nStops) = line.split(" ").map(_.toLong)
    if(totalCapacityOfTrain <= 0 || totalCapacityOfTrain > Math.pow(10,9)) {
      Console.err.println("please enter capacity between 1 and 10 ^ 9")
      System.exit(1)
    } else if(nStops < 2 || nStops > 100) {
      Console.err.println("please enter number of stops between 2 and 100")
      System.exit(1)
    } else {
      // read the other input
      var arrBuffer = scala.collection.mutable.ArrayBuffer[(Int,Int,Int)]()
      var counter = 0
      while(counter < nStops) {
        val input = scala.io.StdIn.readLine()
        val arr = input.split(" ")
        val t = (arr(0).toInt, arr(1).toInt, arr(2).toInt)
        arrBuffer += t
        counter += 1
      }
      if(arrBuffer.size > nStops) {
        Console.err.println("Number of stop entries should be same as number of stops")
        System.exit(1)
      } else {
        var possible = true
        var currentStateOfTrainInTermsOfCapacity = 0
        for(i <- 0 until arrBuffer.size) {
          val (passengerVacatedTheTrain,passengerEntered,passengerInWaiting) = arrBuffer(i.toInt)
          // check for left
          if(passengerVacatedTheTrain < 0 || passengerVacatedTheTrain > currentStateOfTrainInTermsOfCapacity) possible = false
          currentStateOfTrainInTermsOfCapacity -= passengerVacatedTheTrain
          if(passengerEntered < 0 || passengerEntered > totalCapacityOfTrain - currentStateOfTrainInTermsOfCapacity) possible = false
          currentStateOfTrainInTermsOfCapacity += passengerEntered
          if(passengerInWaiting < 0 || (totalCapacityOfTrain > currentStateOfTrainInTermsOfCapacity && passengerInWaiting > 0)) possible = false
        }
        if(possible && currentStateOfTrainInTermsOfCapacity == 0) {
          Console.out.println("possible")
        } else {
          Console.out.println("impossible")
        }
      }
    }
  }

}
