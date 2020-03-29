package ca.jent.fun_with_cast

/**
 * Why should we know about casting in kotlin?
 * Many reason but one that I came across is during reading "Joy of Kotlin" by Pierre-Yves Saumont
 * Essentially, this is encountered when defining sealed class which have a class representing Nothing i.e. the
 * concept of Nothing like empty set, Nil, absence of "something".
 * With the help of few simple classes, we will see how the casting from supertype down to subtype and vice versa and
 * why Nothing is "special".
 *
 * What we will have is the following:
 *
 *     Any
 *     |                                  Casting down (from supertype to subtype) requires explicit cast
 *     Fruit
 *     |
 *     Apple
 *     |                                  Casting up (from subtype to supertype) does not require explicit cast
 *     Gala
 *     |
 *     Nothing                            Cannot cast up or down!  Special!
 *
 * In few words (which simply use common sense) is that we cannot cast Nothing into Something and we cannot cast
 * Something into Nothing.  So when we deal with types such as List<T> and List<Nothing> we need to be aware of that.
 * Observation: You can cast Gala "up" but you cannot cast Nothing "up"
 */

open class Fruit {}

open class Apple: Fruit() {}

class Gala: Apple() {}

fun funWithCast() {

    // Casting from subtype to supertype does not require explicit casting
    val gala: Gala = Gala()
    val apple: Apple = gala
    val fruit: Fruit = apple
    val anyObj: Any = fruit

    // Casting from supertype to subtype requires explict casting
    val fruit2: Fruit = anyObj as Fruit
    val apple2: Apple = fruit2 as Apple
    val gala2: Gala = apple2 as Gala
    // val nothing: Nothing = gala2 as Nothing  // Compiles but get ClassCastException at runtime.

    // Special about Nothing
    // val nothing: Nothing = Nothing()  // We cannot create Nothing
    // val nothing: Nothing = gala       // Cannot automatically cast an instance of Gala to Nothing
    // val nothing: Nothing = gala as Nothing // Compiles but get ClassCastException at runtime.

    println("$gala | $apple | $fruit | $anyObj | $fruit2 | $apple2 | $gala2 ")
}

fun main() {
    funWithCast()
}