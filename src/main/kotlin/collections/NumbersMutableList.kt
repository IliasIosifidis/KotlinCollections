package collections

interface NumbersMutableList {

    val size: Int

    operator fun plus(number: Int)

    operator fun minus(number: Int)

    operator fun get(index: Int): Int

    fun add(number: Int)

    fun add(index: Int, number: Int)

    fun removeAt(index: Int)

    fun remove(number: Int)

    fun clear()

    fun contains(number: Int): Boolean
}