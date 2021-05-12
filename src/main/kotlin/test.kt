import java.io.File

fun main(){

    testWriteFile()

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