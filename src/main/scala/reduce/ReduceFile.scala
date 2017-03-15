package reduce

import scala.collection.mutable
import scala.scalanative.native._
import scala.scalanative.native.stdio._
import scala.scalanative.native.stdlib._

object ReduceFile extends App {
  type Buffer = CStruct2[Ptr[CSignedChar], CInt]

  def readLines(set: mutable.Set[String])(implicit buffer: Ptr[Buffer]): Unit = {
    val nullableLine = fgets(!buffer._1, !buffer._2, stdin)

    if (nullableLine != null) {
      val line = fromCString(nullableLine)
      set -= line += line
      readLines(set)
    }
  }

  val bufLen = 2048
  val buf = malloc(bufLen)

  implicit val buffer = stackalloc[Buffer]
  !buffer._1 = buf
  !buffer._2 = bufLen

  val set = mutable.LinkedHashSet.empty[String]
  readLines(set)
  set.foreach(print)
}
