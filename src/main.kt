import java.util.*

/**
 * Created by Samer on 29/12/2017.
 */

fun validate(number: String): Int {
    var i = 0
    var j = number.length - 1
    var res = 0
    while (i < j) {
        if (number[i] != number[j]) {
            res++
        }
        i++
        j--
    }

    return res
}

fun biggerSymetric(number: String): String {
    var i = 0
    var j = number.length - 1
    val res = StringBuilder()
    while (j >= 0) {
        res.append(maxOf(number[i], number[j]))
        i++
        j--
    }
    return res.toString()
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    sc.nextInt()
    val allowToChange = sc.nextInt()
    val number = sc.next()
    val differencesNumber = validate(number)
    if (allowToChange >= number.length) {
        println(number.map { '9' }.joinToString(""))
    } else if (allowToChange == differencesNumber) {
        println(biggerSymetric(number))
    } else if (allowToChange > differencesNumber) {
        var i = 0
        var j = number.length - 1
        val res = StringBuilder()
        var allowToChangeHolder = allowToChange
        var differencesNumberHolder = differencesNumber
        while (i < j) {
            if (number[i] != number[j]) {
                var maxOf = maxOf(number[i], number[j])
                if (maxOf != '9') {
                    if (allowToChangeHolder - 1 >= differencesNumberHolder) {
                        allowToChangeHolder--
                        maxOf = '9'
                    }
                }
                allowToChangeHolder--
                differencesNumberHolder--
                res.append(maxOf)
            } else if (allowToChangeHolder - 2 >= differencesNumberHolder && number[i] != '9') {
                allowToChangeHolder -= 2
                res.append('9')
            } else
                res.append(number[i])

            i++
            j--
        }

        if (number.length % 2 == 0) {
            val s = res.toString() + res.reverse().toString()
            println(s)
        } else {
            val mid = if (allowToChangeHolder > 0)
                '9'
            else
                number[number.length / 2]
            val s = res.toString() + mid + res.reverse().toString()
            println(s)
        }

    } else
        println(-1)
}