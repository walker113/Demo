package stay.walker.com.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import stay.walker.com.kotlin.view.CodeView
import stay.walker.com.retrofitdemo.R
import stay.walker.com.web.WebPhotoActivity


class KotlinActivity : Activity(), View.OnClickListener {


    private val userNameKey: String = "username"
    private val userPassword = "userPassword"

    private lateinit var et_username: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_jd)

        et_username.setText("可空类型 - 非可空类型")

        // !!.  依旧会抛出空指针异常
        // ?.   相当于先对 et_username 做非空判断，然后再调用
        if (et_username != null) {
            et_username!!.setText("")
        }

        // 平台类型， 类型后跟着一个！号，表示这个类型不是Kotlin类型，而是别的平台类型
        var btn = findViewById<Button>(R.id.open)
        // 别的平台类型，Kotlin是不能做限制的，所以并不是说Kotlin就没有空指针了
        btn.setText("")
        btn.setOnClickListener(this)
        // 我自己去申请了个证书，SSL


    }


    override fun onClick(v: View?) {

        if (v is CodeView) { // is 代替 instance
            // as 表示强转
//            var codeView = v as CodeView
//            codeView.updateCode()
            v.updateCode()
        } else if (v is Button) {
            login()
        }

    }

    private fun login() {
        val username = et_username.text.toString()
        val user = User(username)

        if (verify(user)) {
            // WebPhotoActivity::class 获取的是Koltin的对象，WebPhotoActivity::class.java获取的是java对象
            startActivity(Intent(this, WebPhotoActivity::class.java))
        }

    }

    private fun verify(user: User): Boolean {
        if (user.username != null && user.username!!.length < 4) {
            return false
        }
        return true
    }


}