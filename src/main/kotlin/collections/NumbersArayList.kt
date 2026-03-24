package collections

class NumbersArayList(initialCapacity: Int = INITIAL_CAPACITY) : NumbersMutableList {

    private var numbers = arrayOfNulls<Int>(INITIAL_CAPACITY)

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
            val newArray = arrayOfNulls<Int>((numbers.size.toDouble() * 1.5).toInt())
            System.arraycopy(numbers, 0, newArray, 0, size)
            numbers = newArray
        }
    }

    override var size: Int = 0
        private set

    override fun plus(number: Int) {
        add(number)
    }

    override fun minus(number: Int) {
        remove(number)
    }

    override fun add(number: Int) {
        growIfNeeded()
        numbers[size] = number
        size++
    }

    override fun add(index: Int, number: Int) {
        checkIndexForAdding(index)
        System.arraycopy(numbers, index, numbers, index + 1, size - index)
        numbers[index] = number
        size++
    }

    override fun get(index: Int): Int {
        checkIndex(index)
        return numbers[index]!!
    }

    override fun removeAt(index: Int) {
        checkIndex(index)
        System.arraycopy(numbers, index + 1, numbers, index, size - index - 1)
        size--
        numbers[size] = null
    }

    override fun remove(number: Int) {
        for (i in 0..size){
            if (numbers[i] == number){
                removeAt(i)
                return
            }
        }
    }

    override fun clear() {
        numbers = arrayOfNulls<Int>(10)
        size = 0
    }

    override fun contains(number: Int): Boolean {
        for (i in 0..size){
            if (numbers[i] == number) {
                return true
            }
        }
        return false
    }

    companion object{
        private const val INITIAL_CAPACITY = 10
    }
}