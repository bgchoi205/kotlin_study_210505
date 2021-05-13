import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun readLineTrim() = readLine()!!.trim()


fun ArticleFromJson(jsonStr: String): Article {
    val jsonMap = mapFromJson(jsonStr)
    val id = jsonMap["id"].toString().toInt()
    val title = jsonMap["title"].toString()
    val body = jsonMap["body"].toString()

    return Article(1, "aa", "bb", 1, 1, "2021-05-13 12:08", "2021-05-13 12:12")
}

fun mapFromJson(jsonStr: String): Map<String, Any> {
    val map = mutableMapOf<String, Any>()

    var jsonStr = jsonStr.drop(1).dropLast(1)
    val jsonItems = jsonStr.split(",\r\n")

    for(jsonItem in jsonItems){
        val jsonItemBits = jsonItem.trim().split(":", limit = 2)

        val key = jsonItemBits[0].drop(1).dropLast(1)
        var value = jsonItemBits[1]

        if(value == "true"){
            map[key] = true
        }
        else if(value == "false"){
            map[key] = false
        }
        else if(value.startsWith("\"")){
            map[key] = value.drop(1).dropLast(1)
        }
        else if(value.contains(".")){
            map[key] = value.toDouble()
        }
        else{
            map[key] = value.toInt()
        }
    }

    return map.toMap()
}


object Util{
    fun getDateNowStr() : String{
        var now = LocalDateTime.now()
        var getNowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분 ss초"))
        return getNowStr
    }
}