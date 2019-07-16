package stay.walker.com.kotlin.entity

class Lesson {

    var date: String? = null
    var content: String? = null
    var state: State? = null

    constructor(date: String?, content: String?, state: State?){
        this.date = date
        this.content = content
        this.state = state
    }

    enum class State {
        PLAYBACK {
            override fun stateName(): String {
                return "PLAYBACK"
            }
        },
        LIVE {
            override fun stateName(): String {
                return "LIVE"
            }
        },
        WAIT {
            override fun stateName(): String {
                return "WAITING"
            }
        };

        abstract fun stateName(): String
    }

}

