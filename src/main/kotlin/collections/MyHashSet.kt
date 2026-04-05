package collections

import kotlin.math.abs

class MyHashSet<T> : MyMutableSet<T> {

    private var elements = arrayOfNulls<Node<T>>(INITIAL_CAPACITY)

    override var size: Int = 0
        private set

    override fun add(element: T): Boolean {
        if (size >= elements.size * LOAD_FACTOR){
            increaseArray()
        }
        val added = add(element, elements)
        if (added){
            size++
        }
        return added
    }

    private fun add(element: T, array: Array<Node<T>?>): Boolean {
        val newElement = Node(element)
        val position = getElementPosition(element, array.size)
        var existedElement = elements[position]
        if (existedElement == null) {
            elements[position] = newElement
            return true
        } else {
            while (true) {
                if (existedElement?.item == element) {
                    return false
                } else {
                    if (existedElement?.next == null) {
                        existedElement?.next = newElement
                        return true
                    } else {
                        existedElement = existedElement.next
                    }
                }
            }
        }
    }

    private fun increaseArray(){
        val newArray = arrayOfNulls<Node<T>>(elements.size * 2)
        for (node in elements){
            var currentElement = node
            while (currentElement != null){
                add(currentElement.item, newArray)
                currentElement = currentElement.next
            }
        }
        elements = newArray
    }

    override fun remove(element: T) {
        val position = getElementPosition(element, elements.size)
        val existedElement = elements[position] ?: return
        if (existedElement.item == element){
            elements[position] = existedElement
            size--
            return
        }
        var before: Node<T>? = existedElement
        while (before?.next != null){
            val reomvingElement = before.next
            if (reomvingElement?.item == element){
                before.next = reomvingElement.next
                size--
                return
            } else {
                before = before.next
            }
        }
    }

    override fun clear() {
        elements = arrayOfNulls(INITIAL_CAPACITY)
        size = 0
    }

    override fun contains(element: T): Boolean {
        val position = getElementPosition(element, elements.size)
        var existedElement = elements[position]
        while (existedElement != null){
            if (existedElement.item == element){
                return true
            } else {
                existedElement = existedElement.next
            }
        }
        return false
    }

    private fun getElementPosition(element: T, arraySize: Int): Int {
        return abs(element.hashCode() % arraySize)
    }

    data class Node<T>(
        val item: T,
        var next: Node<T>? = null
    )

    companion object {
        private const val INITIAL_CAPACITY = 16
        private const val LOAD_FACTOR = 0.75
    }
}