package tictactoe

val board = mutableListOf(MutableList<Char>(3){' '}, MutableList<Char>(3){' '}, MutableList<Char>(3){' '})
var status = ""
var countEmptySpaces = 9
var winnerNotDefined = true

fun main() {
    var posRow: Int
    var posCol: Int

    printTicTacToe()

    while(countEmptySpaces > 0 && winnerNotDefined){
        var move = readln().split(" ")
    	if (isNotNumeric(move[0]) || isNotNumeric(move[1])){
            println("You should enter numbers!")
            continue
        }
        
        posRow = move[0].toInt() - 1
        posCol = move[1].toInt() - 1
        if(posRow > 2 || posCol > 2) {
            println("Coordinates should be from 1 to 3")
            continue
        }
        
        if(!board[posRow][posCol].equals(' ')){
            println("This cell is occupied! Choose another one!")
            continue
        }
        
        makeTheMove(posRow,posCol)
        printTicTacToe()
        countEmptySpaces--
        checkGameStatus()
    }

    println(status)
}

fun printTicTacToe() {
    println("---------")
    for (i in 0 until 3) {
        print("| ")
        for (j in 0 until 3) {
            print("${board[i][j]} ")
        }
        println("|")
    }
    println("---------")
}

fun isNotNumeric(toCheck: String): Boolean {
    return toCheck.toIntOrNull() == null
}

fun isWinner(ch: Char): Boolean {
    return checkRows(ch) || checkColumns(ch) || checkMainDiagonal(ch) || checkSecondDiagonal(ch)
}

fun checkRows(ch: Char): Boolean {
    for (row in board) {
        if(row.all { it.equals(ch) }){
            return true
        }
    }
    return false
}

fun checkColumns(ch: Char): Boolean {
    var count = 0
    for (i in board.indices) {
        for (j in board.indices) {
            if (board[j][i].equals(ch)) {
                count++
            }
        }
        if (count == 3) {
            return true
        }
        count = 0
    }
    return false
}

fun checkMainDiagonal(ch: Char): Boolean {
    for (i in board.indices) {
        if (board[i][i] != ch) {
            return false
        }
    }
    return true
}

fun checkSecondDiagonal(ch: Char): Boolean {
    for (i in board.indices) {
        for (j in board.indices) {
            if ((i + j == board.size - 1) && board[i][j] != ch) {
                return false
            }
        }
    }
    return true
}

fun isDraw(): Boolean{
    return countEmptySpaces == 0
}

fun makeTheMove(posRow: Int, posCol: Int) {
    val countX = countOccurences('X')
    val countO = countOccurences('O')
    if(countX == countO || countX - countO > 0){
        board[posRow][posCol] = 'X'
    } else {
        board[posRow][posCol] = 'O'
    }
}

fun countOccurences(ch: Char): Int {
    var count = 0
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if(board[i][j].equals(ch)){
                count++
            }
        }
    }
    return count
}

fun checkGameStatus() {
    winnerNotDefined = false
    if(isWinner('X')){
        status = "X wins"
    } else if(isWinner('O')){
        status = "O wins"
    } else if(isDraw()){
        status = "Draw"
    } else {
        status = "Game not finished"
        winnerNotDefined = true
    }
}
