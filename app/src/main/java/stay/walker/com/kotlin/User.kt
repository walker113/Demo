package stay.walker.com.kotlin


class User: Any{

    @JvmField
    public var username: String? = null


    constructor()

    constructor(username: String?){
        this.username = username
    }

}