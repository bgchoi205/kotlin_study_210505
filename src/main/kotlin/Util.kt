import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun readLineTrim() = readLine()!!.trim()




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

fun readStrFromFile(filePath:String) : String{
    if(!File(filePath).isFile){
        return ""
    }
    return File(filePath).readText(Charsets.UTF_8)
}

fun writeStrFile(filePath : String, fileContent : String){
    File(filePath).parentFile.mkdirs()
    File(filePath).writeText(fileContent)
}

fun readIntFromFile(filePath:String) : Int{
    return readStrFromFile(filePath).toInt()
}

fun writeIntFile(filePath : String, fileContent : Int){
    writeStrFile(filePath, fileContent.toString())
}



object Util{
    fun getDateNowStr() : String{
        var now = LocalDateTime.now()
        var getNowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH시 mm분 ss초"))
        return getNowStr
    }
}