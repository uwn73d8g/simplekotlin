// Explore a simple class
println("UW Homework: Simple Kotlin")

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any) = when (arg) {
    "Hello" -> "world"
    "Howdy", "Bonjour" -> "Say what?"
    is String -> "I don't understand"
    0 -> "zero"
    1 -> "one"
    in 2..10 -> "low number"
    else -> "I don't understand"
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(c: Int, d: Int): Int {
    return c + d
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(e: Int, f: Int, g: (e: Int, f: Int) -> Int): Int {
    return g(e, f)
}

// write a class "Person" with first name, last name and age
class Person(var firstName: String, var lastName: String, var age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"

    fun equals(other: Person): Boolean {
        return other.debugString == debugString
    }

    override fun hashCode(): Int {
        val length = lastName.length + firstName.length - 1
        var code = 0
        val all = lastName.plus(firstName)
        for (i in 0..length) {
            code += all[i].toInt()
        }
        return code
    }
}

// write a class "Money"
class Money(var amount: Int, var currency: String) {
    fun convert(cur: String): Money {

        if (cur == currency) {
            return this
        }

        return when (currency) {
            "USD" -> when (cur) {
                "CAN" -> Money((amount * 1.25).toInt(), cur)
                "EUR" -> Money((amount * 1.5).toInt(), cur)
                "GBP" -> Money((amount / 2.0).toInt(), cur)
                else -> throw IllegalArgumentException("hello")
            }

            "EUR" -> when (cur) {
                "CAN" -> Money(((amount * 5.0) / 6).toInt(), cur)
                "GBP" -> Money((amount / 3.0).toInt(), cur)
                "USD" -> Money(((amount * 2.0) / 3).toInt(), cur)
                else -> throw IllegalArgumentException()

            }

            "CAN" -> when (cur) {
                "USD" -> Money((amount * 0.8).toInt(), cur)
                "EUR" -> Money((amount * 1.2).toInt(), cur)
                "GBP" -> Money((amount * 0.4).toInt(), cur)
                else -> throw IllegalArgumentException()
            }

            "GBP" -> when (cur) {
                "EUR" -> Money((amount * 3.0).toInt(), cur)
                "CAN" -> Money((amount * 2.5).toInt(), cur)
                "USD" -> Money((amount * 2.0).toInt(), cur)
                else -> throw IllegalArgumentException()

            }

            else -> throw IllegalArgumentException()
        }

    }

    operator fun plus(other: Money): Money {
        return if (this.currency != other.currency) {
            Money(other.convert(this.currency).amount + this.amount, this.currency)
        } else {
            Money(other.amount + this.amount, this.currency)
        }
    }

}

// ============ DO NOT EDIT BELOW THIS LINE =============

print("When tests: ")
val when_tests = listOf(
        "Hello" to "world",
        "Howdy" to "Say what?",
        "Bonjour" to "Say what?",
        0 to "zero",
        1 to "one",
        5 to "low number",
        9 to "low number",
        17.0 to "I don't understand"
)
for ((k, v) in when_tests) {
    print(if (whenFn(k) == v) "." else "!")
}
println("")

print("Add tests: ")
val add_tests = listOf(
        Pair(0, 0) to 0,
        Pair(1, 2) to 3,
        Pair(-2, 2) to 0,
        Pair(123, 456) to 579
)
for ((k, v) in add_tests) {
    print(if (add(k.first, k.second) == v) "." else "!")
}
println("")

print("Sub tests: ")
val sub_tests = listOf(
        Pair(0, 0) to 0,
        Pair(2, 1) to 1,
        Pair(-2, 2) to -4,
        Pair(456, 123) to 333
)
for ((k, v) in sub_tests) {
    print(if (sub(k.first, k.second) == v) "." else "!")
}
println("")

print("Op tests: ")
print(if (mathOp(2, 2, { l, r -> l + r }) == 4) "." else "!")
print(if (mathOp(2, 2, ::add) == 4) "." else "!")
print(if (mathOp(2, 2, ::sub) == 0) "." else "!")
print(if (mathOp(2, 2, { l, r -> l * r }) == 4) "." else "!")
println("")


print("Person tests: ")
val p1 = Person("Ted", "Neward", 47)
print(if (p1.firstName == "Ted") "." else "!")
p1.age = 48
print(if (p1.debugString == "[Person firstName:Ted lastName:Neward age:48]") "." else "!")
println("")

print("Money tests: ")
val tenUSD = Money(10, "USD")
val twelveUSD = Money(12, "USD")
val fiveGBP = Money(5, "GBP")
val fifteenEUR = Money(15, "EUR")
val fifteenCAN = Money(15, "CAN")
val convert_tests = listOf(
        Pair(tenUSD, tenUSD),
        Pair(tenUSD, fiveGBP),
        Pair(tenUSD, fifteenEUR),
        Pair(twelveUSD, fifteenCAN),
        Pair(fiveGBP, tenUSD),
        Pair(fiveGBP, fifteenEUR)
)
for ((from, to) in convert_tests) {
    print(if (from.convert(to.currency).amount == to.amount) "." else "!")
}
val moneyadd_tests = listOf(
        Pair(tenUSD, tenUSD) to Money(20, "USD"),
        Pair(tenUSD, fiveGBP) to Money(20, "USD"),
        Pair(fiveGBP, tenUSD) to Money(10, "GBP")
)
for ((pair, result) in moneyadd_tests) {
    print(if ((pair.first + pair.second).amount == result.amount &&
            (pair.first + pair.second).currency == result.currency) "." else "!")
}
println("")
