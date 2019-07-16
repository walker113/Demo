package stay.walker.com.handler

import android.os.Handler
import android.util.Log
import com.socks.library.KLog
import java.util.*


class MyMessageQueue {
    companion object{
        public val TAG = "Handler"
    }

    private val messageList = LinkedList<CusMessage>()

    private val condition = Object()

    fun addMessage(message: CusMessage) {
        synchronized(condition) {
            Log.w(TAG, "add")
            messageList.add(message)
            if (messageList.size == 1) {
                Log.w(TAG, "notify")
                condition.notify()
            }
        }
    }


    fun getMessage(): CusMessage {
        synchronized(condition){
            if (messageList.size == 0) {
                Log.e(TAG, "线程 - wait")
                condition.wait()
            }
            return messageList.removeAt(0)
        }
    }

}