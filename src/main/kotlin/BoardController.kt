class BoardController{
    fun add() {
        print("게시판 이름 입력 : ")
        val name = readLineTrim()
        print("게시판 코드 입력 : ")
        val code = readLineTrim()
        boardRepository.addBoard(name, code)
        println("${name} 게시판이 생성되었습니다.")
    }

    fun list() {
        for(board in boardRepository.boards){
            println("번호 : ${board.id} / 게시판이름 : ${board.name} / 코드 : ${board.code}")
        }
    }

}
