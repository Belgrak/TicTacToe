package bot

fun easyBot(xPositions: MutableList<Pair<Int, Int>>, oPositions: MutableList<Pair<Int, Int>>): Pair<Int, Int> {
    require(xPositions.size + oPositions.size < 9) { "No empty cells" }
    var row = (0 until 3).random()
    var column = (0 until 3).random()
    while (Pair(row, column) in (xPositions + oPositions)) {
        row = (0 until 3).random()
        column = (0 until 3).random()
    }
    return Pair(row, column)
}
