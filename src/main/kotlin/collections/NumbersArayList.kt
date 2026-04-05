package collections

class NumbersArayList<T>(initialCapacity: Int = INITIAL_CAPACITY) : MyMutableList<T> {

    private var numbers = arrayOfNulls<Any>(INITIAL_CAPACITY)

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
        if (numbers.size == size){
            val newArray = arrayOfNulls<Any>((numbers.size.toDouble() * 1.5).toInt())
            System.arraycopy(numbers, 0, newArray, 0, size)
            numbers = newArray
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

    override fun add(element: T) {
        growIfNeeded()
        numbers[size] = element
        size++
    }

    override fun add(index: Int, element: T) {
        checkIndexForAdding(index)
        System.arraycopy(numbers, index, numbers, index + 1, size - index)
        numbers[index] = element
        size++
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return numbers[index] as T
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(numbers, index + 1, numbers, index, size - index - 1)
        size--
        numbers[size] = null
    }

    override fun remove(element: T) {
        for (i in 0..size){
            if (numbers[i] == element){
                removeAt(i)
                return
            }
        }
    }

    override fun clear() {
        numbers = arrayOfNulls<Any>(10)
        size = 0
    }

    override fun contains(element: T): Boolean {
        for (i in 0..size){
            if (numbers[i] == element) {
                return true
            }
        }
        return false
    }

    companion object{
        private const val INITIAL_CAPACITY = 10
    }
}