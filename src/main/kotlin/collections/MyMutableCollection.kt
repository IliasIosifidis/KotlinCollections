package collections

interface MyMutableCollection<T> {

    val size: Int

    operator fun get(index: Int): T

    fun add(element: T)

    fun remove(element: T)

    fun clear()

    fun contains(element: T): Boolean
}