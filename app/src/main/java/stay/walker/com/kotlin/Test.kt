package stay.walker.com.kotlin

fun main() {
    println("Koltin是静态类型语言，Koltin有类型推断")
}

fun doubleX(x: Int): Int{
    var desc : String = "冒号后面跟着类型，冒号后面跟着返回值"
    return x * 2
}

var age:Int = 18
var age2 = 18
val name: String = "Koltin,val 相当于 final，只读变量的声明"

val name2 = "Kotlin 如果表达啥右边的类型是可以推断的，则可以省略类型"

var java: Java = Java()
