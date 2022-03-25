import scala.sys.exit

class MyBoard {

  // variables yet to be adjusted
  var position = (0, 0)
  var arrow: Char = ' '
  var size = (0, 0)


  def move(a: Char, steps: Int, size: (Int, Int), pos: (Int, Int)): (Int, Int) = {
    // ^ ( )(-)
    // v ( )(+)
    // < (-)( )
    // > (+)( )

    val (m, n) = size
    var (i, j) = pos

    a match {
      case '^' =>
        val s = m - (steps % m)
        i = (i + s) % m
      case 'v' => i = (i+steps) % m
      case '<' =>
        val s = n - (steps % n)
        j = (j+s) % n
      case '>' => j = (j+steps) % n
    }

    (i, j)
  }


  def rotate(a: Char, degree: Int): Char = {

    if (degree % 90 != 0) {
      println("Rotation is only possible with degree being multiple of 90.")
      exit(-1)
    }

    val arrows = List('>', 'v', '<', '^')
    def positive(degree: Int): Int = degree match {
      case pos if pos >= 0 => pos
      case neg => neg + 360
    }

    // go n steps to the right
    val n = positive(degree) / 90
    arrows((arrows.indexOf(a) + n) % 4)
  }


  def printState(size: (Int, Int), position: (Int, Int), arrow: Char): Unit = {
    // n - width
    // m - height
    val (n, m) = size
    for (i <- 0 until n) {
      for (j <- 0 until m) {
        // if arrow in current position -> print box with arrow
        // else print empty box
        if ((i, j) == position) print(s"|$arrow|")
        else print("| |")
      }
      println()
    }
    println()
  }


  def executeInstruction(l: Seq[String]): Unit = {
    println(s"current instruction: ${l.mkString(" ")}")

    l(0).toUpperCase match {
      case "CREATE" =>
        size = (l(2).toInt, l(3).toInt)
        arrow = l(4)(0)
      case "MOVE" => position = move(arrow, l(2).toInt, size, position)
      case "ROTATE" => arrow = rotate(arrow, l(2).toInt % 360)
    }

    printState(size, position, arrow)
  }
}