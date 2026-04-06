package collections

class MyArayList<T>(initialCapacity: Int = INITIAL_CAPACITY) : MyMutableList<T> {

    private var elements = arrayOfNulls<Any>(INITIAL_CAPACITY)
    private var modCount = 0

    private fun checkIndex(index: Int){
        if (index < 0 || index >= size){
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    private fun checkIndexForAdding(index: Int){
        if (index < 0 || index > size){
            throw IndexOutOfBoundsException("Index: $index Size: $size")
        }
    }

    private fun growIfNeeded(){
        if (elements.size == size){
            val newArray = arrayOfNulls<Any>((elements.size.toDouble() * 1.5).toInt())
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
    }

    override var size: Int = 0
        private set

    override fun plus(element: T) {
        add(element)
    }

    override fun minus(element: T) {
        remove(element)
    }

    override fun add(element: T): Boolean {
        modCount++
        growIfNeeded()
        elements[size] = element
        size++
        return true
    }

    override fun add(index: Int, element: T) {
        modCount++
        checkIndexForAdding(index)
        System.arraycopy(elements, index, elements, index + 1, size - index)
        elements[index] = element
        size++
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return elements[index] as T
    }

    override fun removeAt(index: Int) {
        modCount++
        checkIndex(index)
        System.arraycopy(elements, index + 1, elements, index, size - index - 1)
        size--
        elements[size] = null
    }

    override fun remove(element: T) {
        modCount++
        for (i in 0..size){
            if (elements[i] == element){
                removeAt(i)
                return
            }
        }
    }

    override fun clear() {
        modCount++
        elements = arrayOfNulls<Any>(10)
        size = 0
    }

    override fun contains(element: T): Boolean {
        for (i in 0..size){
            if (elements[i] == element) {
                return true
            }
        }
        return false
    }

    override fun iterator(): MutableIterator<T> {
        return object : MutableIterator<T>{

            private val currentModCount = modCount
            private var nextIndex = 0

            override fun next(): T {
                if (currentModCount != modCount) throw ConcurrentModificationException()
                return elements[nextIndex++] as T
            }

            override fun hasNext(): Boolean {
                return nextIndex < size
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }
    }

    companion object{
        private const val INITIAL_CAPACITY = 10
    }
}