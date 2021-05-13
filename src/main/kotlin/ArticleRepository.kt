import java.io.File

class ArticleRepository{

    fun getArticles() : List<Article>{
        val articles = mutableListOf<Article>()

        val lastArticleId = getLastId()
        for(i in 1..lastArticleId){
            val article = articleFromFile("data/article/$i.json")

            if(article != null){
                articles.add(article)
            }
        }
        return articles
    }

    fun articleFromFile(filePath: String): Article? {

        val jsonStr = readStrFromFile(filePath)
        if(jsonStr == ""){
            return null
        }

        val jsonMap = mapFromJson(jsonStr)

        val id = jsonMap["id"].toString().toInt()
        val title = jsonMap["title"].toString()
        val body = jsonMap["body"].toString()
        val memberId = jsonMap["memberId"].toString().toInt()
        val boardId = jsonMap["boardId"].toString().toInt()
        val regDate = jsonMap["regDate"].toString()
        val updateDate = jsonMap["updateDate"].toString()

        return Article(id, title, body, memberId, boardId, regDate, updateDate)
    }

    fun getLastId(): Int{
        val lastId = File("data/article/lastArticleId.txt").readText(Charsets.UTF_8).toInt()
        return lastId
    }

    fun addArticle(title: String, body: String, memberId : Int, boardId : Int): Int {
        val id = getLastId() + 1
        val regDate = Util.getDateNowStr()
        val updateDate = Util.getDateNowStr()

        val article = Article(id, title, body, memberId, boardId, regDate, updateDate)
        val jsonStr = article.toJson()
        writeStrFile("data/article/${article.id}.json", jsonStr)
        writeIntFile("data/article/lastArticleId.txt", id)
        return id
    }

    fun makeTestArticles(){
        for(i in 1..30){
            addArticle("제목$i","내용$i", i % 9 + 1, i % 2 + 1)
        }
    }

    fun getArticleById(id: Int): Article? {
        for(article in getArticles()){
            if(article.id == id){
                return article
            }
        }
        return null
    }

    fun deleteArticle(article : Article){
        File("data/article/${article.id}.json").delete()
    }

    fun articlesFilter(keyword: String, boardId : Int, page: Int): List<Article> {
        val filtered1Articles = articlesFilterByKey(keyword)
        val filtered2Articles = articlesFilterByBoardId(filtered1Articles, boardId)
        val filtered3Articles = articlesFilterByPage(filtered2Articles, page, 5)
        return filtered3Articles
    }

    private fun articlesFilterByPage(filtered2Articles: List<Article>, page: Int, pageCount: Int): List<Article> {
        val filtered3Articles = mutableListOf<Article>()

        val startIndex = filtered2Articles.lastIndex - ((page - 1) * pageCount)
        var endIndex = startIndex - pageCount + 1
        if(endIndex < 0){
            endIndex = 0
        }
        for(i in startIndex downTo endIndex){
            filtered3Articles.add(filtered2Articles[i])
        }
        return filtered3Articles
    }

    private fun articlesFilterByBoardId(filtered1Articles: List<Article>, boardId: Int): List<Article> {
        if(boardId == 0){
            return filtered1Articles
        }
        val filtered2Articles = mutableListOf<Article>()
        for(article in filtered1Articles){
            if(article.boardId == boardId){
                filtered2Articles.add(article)
            }
        }
        return filtered2Articles
    }

    private fun articlesFilterByKey(keyword: String): List<Article> {
        if(keyword.isEmpty()){
            return getArticles()
        }
        val filtered1Articles = mutableListOf<Article>()
        for(article in getArticles()){
            if(article.title.contains(keyword)){
                filtered1Articles.add(article)
            }
        }
        return filtered1Articles
    }

}
