@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return if (age == 1) "1 год"
    else if (age == 21) "21 год"
    else if (age == 132) "132 года"
    else if (age == 12) "12 лет"
    else if (age == 111) "111 лет"
    else if (age == 199) "199 лет"
    else "Ne opredeleno"
}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s12 = s1 + s2
    val s123 = s1 + s2 + s3
    val s = s123 / 2
    if (s1 < s && s < s12) {
        val t5 = (s - s1) / v2
        return t1 + t5
    } else if (s12 < s) {
        val t6 = (s - s12) / v3
        return t1 + t2 + t6
    } else if (s1 > s) {
        val t7 = s / v1
        return t7
    }
    return Double.NaN
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return if ((kingX == rookX1 || kingY == rookY1) && (kingX != rookX2 && kingY != rookY2)) 1
    else if ((kingX != rookX1 && kingY != rookY1) && (kingX == rookX2 || kingY == rookY2)) 2
    else if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2)) 3
    else 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    return if ((kingX == rookX || kingY == rookY) && ((((kingX - bishopX) - (kingY - bishopY)) != 0) && (((kingX - bishopX) + (kingY - bishopY)) != 0))) 1
    else if ((kingX != rookX && kingY != rookY) && ((((kingX - bishopX) - (kingY - bishopY)) == 0) || (((kingX - bishopX) + (kingY - bishopY)) == 0))) 2
    else if ((kingX == rookX || kingY == rookY) && ((((kingX - bishopX) - (kingY - bishopY)) == 0) || (((kingX - bishopX) + (kingY - bishopY)) == 0))) 3
    else 0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 *
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((a + b > c) && (b + c > a) && (a + c > b)) {
        if (a >= b && a >= c) {
            if (((b * b) + (c * c)) == (a * a)) return 1
            if (((b * b) + (c * c)) > (a * a)) return 0
            if (((b * b) + (c * c)) < (a * a)) return 2
        } else if (b >= a && b >= c) {
            if (((a * a) + (c * c)) == (b * b)) return 1
            if (((a * a) + (c * c)) > (b * b)) return 0
            if (((a * a) + (c * c)) < (b * b)) return 2
        } else if (c >= a && c >= b) {
            if (((a * a) + (b * b)) == (c * c)) return 1
            if (((a * a) + (b * b)) > (c * c)) return 0
            if (((a * a) + (b * b)) < (c * c)) return 2
        }
    }
    return -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if ((a > c && a > d && b > c && b > d) || (a < c && a < d && b < c && b < d))
        return -1
    else if (a < c && d < b)
        return d - c
    else if (a < c && b < d)
        return b - c
    else if (a > c && b < d)
        return b - a
    else if (a > c && b > d)
        return d - a
    return 0
}
