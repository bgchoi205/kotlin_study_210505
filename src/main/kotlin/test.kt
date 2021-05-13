import java.io.File

fun main(){

    //testWriteFile()

    //testWriteFile2()
    //println(testReadFile())

    testReadFile2()

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

fun testReadFile (): String =
    File("test/3.txt").readText(Charsets.UTF_8)




fun testReadFile2() {
    testWriteFile2()

    val jsonStr = File("test/3.json").readText(Charsets.UTF_8)
    val testArticle = testArticleFromJson(jsonStr)
    println("id : ${testArticle.id} / title : ${testArticle.title} / body : ${testArticle.body}")
}

fun testArticleFromJson(jsonStr: String): TestArticle {
    val jsonMap = mapFromJson(jsonStr)
    val id = jsonMap["id"].toString().toInt()
    val title = jsonMap["title"].toString()
    val body = jsonMap["body"].toString()
    
    return TestArticle(id, title, body)
}



fun testWriteFile2() {
    val testArticle = TestArticle(2, "제목2", "내용2")

    File("test/3.json").writeText(testArticle.toJson())
}

fun testWriteFile(){
    val id = 1
    val title = "제목"
    val body = "내용"

    var fileContent = ""
    fileContent += "{"
    fileContent += "\r\n"
    fileContent += "\t" + """ "id":"$id", """.trim()
    fileContent += "\r\n"
    fileContent += "\t" + """ "title":"$title", """.trim()
    fileContent += "\r\n"
    fileContent += "\t" + """ "body":"$body" """.trim()
    fileContent += "\r\n"
    fileContent += "}"

    File("test/1.txt").writeText(fileContent)
}