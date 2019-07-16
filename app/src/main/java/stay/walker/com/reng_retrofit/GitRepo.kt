package stay.walker.com.reng_retrofit




class GitRepo {

    var url: String = ""
    var owner: OwnerBean? = null

    companion object
    class OwnerBean {
       var login: String? = null
       var id: Int = 0
       var node_id: String? = null

    }

}



