package training

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver._
import scala.util.Random

/** Streams random words from the supplied dictionary.
  *
  * @param dictionary      An array of words constituting a dictionary,
  *                        presumably loaded from a file.
  * @param wordsPerCycle   Number of words to emit per cycle. A cycle is
  *                        just an internal wait interval in this class. (It
  *                        has nothing to do with Spark Streaming.)
  * @param interCycleSleep How many milliseconds to sleep between cycles
  *
  */
class RandomWordSource(dictionary:      Array[String],
                       wordsPerCycle:   Int,
                       interCycleSleep: Int)
  extends Receiver[String](StorageLevel.MEMORY_AND_DISK_2) {

  def onStart(): Unit = {
    new Thread("Streaming word source") {
      override def run(): Unit = {
        receive()
      }
    }.start()
  }

  def onStop(): Unit = {
    // Nothing to do. The thread calling receive() should stop itself.
  }

  private def receive(): Unit = {
    while(! isStopped()) {
      // Choose wordsPerCycle words from the file.
      for (i <- 1 to wordsPerCycle) store(randomWord(dictionary))

      // Sleep for interCycleSleep milliseconds
      Thread.sleep(interCycleSleep)
    }
  }

  private def randomWord(words: Array[String]) = words(Random.nextInt(words.length))
}
