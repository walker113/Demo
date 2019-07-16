package stay.walker.com.handler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.socks.library.KLog
import stay.walker.com.retrofitdemo.R

class HandlerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_handler)

        val btn_Add = findViewById<Button>(R.id.btn_add)


        val queue = MyMessageQueue()

        val handler = object : MyHandler() {
            override fun handlerMessage(message: CusMessage) {
                Log.e(MyMessageQueue.TAG, "deal messge ${message.what}")
                Thread.sleep(1000)
            }
        }

        val looper = Thread(Runnable {
            while (true) {
                handler.handlerMessage(queue.getMessage())
            }
        })

        looper.start()

        var i = 0
        btn_Add.setOnClickListener {
            for (j in 1..10) {
                Log.e(MyMessageQueue.TAG, "--- add message ${(i * 10) + j}")
                queue.addMessage(CusMessage((i * 10) + j))
            }

            i++
        }


    }

}