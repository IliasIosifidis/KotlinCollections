package collections

interface MyMutableList<T>: MyMutableCollection<T> {

    override val size: Int

    operator fun plus(element: T)

    operator fun minus(element: T)

    operator fun get(index: Int): T

    override fun add(element: T): Boolean

    fun add(index: Int, element: T)

    fun removeAt(index: Int)

    override fun remove(element: T)

    override fun clear()

    override fun contains(element: T): Boolean
}