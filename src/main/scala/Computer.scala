import io.Source._

object Computer extends App {

  val lines: Seq[Seq[String]] = fromResource("instructions.txt")
    .getLines
    .toSeq
    .map(_.split(" +"))


  val board = new MyBoard
  lines.foreach(board.executeInstruction)
}