import java.io.File

fun main(){

    testWriteFile()

    testWriteFile2()

}

data class TestArticle(
    val id : Int,
    val title : String,
    val body : String
) {
    fun toJson(): String {
        var jsonStr = ""

        jsonStr += "{"
        jsonStr += "\r\n"
        jsonStr += "\t" + """ "id":$id """.trim() + ","
        jsonStr += "\r\n"
        jsonStr += "\t" + """ "title":"$title" """.trim() + ","  // 값이 문자열이면 "로 다시 감싸준다
        jsonStr += "\r\n"
        jsonStr += "\t" + """ "body":"$body" """.trim()
        jsonStr += "\r\n"
        jsonStr += "}"

        return jsonStr
    }
}

fun testWriteFile2() {
    val testArticle = TestArticle(1, "제목1", "내용1")

    File("test/3.json").writeText(testArticle.toJson())
}

fun testWriteFile(){
    val id = 1
    val title = "제목"
    val body = "내용"

    var fileContent = "{"
    fileContent += "\r\n"
    fileContent += "\t" + """ "id":$id, """.trim()
    fileContent += "\r\n"
    fileContent += "\t" + """ "title":$title, """.trim()
    fileContent += "\r\n"
    fileContent += "\t" + """ "body":$body """.trim()
    fileContent += "\r\n"
    fileContent += "}"

    File("test1.txt").writeText(fileContent)
}