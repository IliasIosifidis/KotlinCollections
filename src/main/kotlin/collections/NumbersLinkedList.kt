package collections

class NumbersLinkedList<T> : MyMutableList<T> {

    private var first: Node? = null
    private var last: Node? = null

    override var size: Int = 0
        private set

    override fun add(element: T) {
        val prevLast = last
        last = Node(prevLast, element)
        if (prevLast == null){
            first = last
        } else {
            prevLast.next = last
        }
        size++
    }

    override fun add(index: Int, element: T) {
        checkIndexForAdding(index)
        if (index == size){
            add(element)
            return
        }
        if (index == 0){
            val node = Node(null,element, first)
            first?.prev = node
            first = node
            size++
            return
        }
        val before = getNode(index - 1)
        val after = before.next
        val newNode = Node(before, element, after)
        before.next = newNode
        after?.prev = newNode
        size++
    }

    override fun plus(element: T) {
        add(element)
    }

    override fun minus(element: T) {
        remove(element)
    }

    override fun get(index: Int): T {
        checkIndexForAdding(index)
        return getNode(index).item
    }

    private fun getNode(index: Int): Node {
        if (index == 0) return first!!
        if (index == size - 1) return last!!

        if (index < size /2){
            var node = first
            repeat(index){
                node = node?.next
            }
            return node!!
        } else {
            var node = last
            repeat(size - index - 1){
                node = node?.next
            }
            return node!!
        }
    }

    override fun removeAt(index: Int) {
        checkIndexForAdding(index)
        val node = getNode(index)
        unlink(node)
    }

    private fun unlink(node: Node){
        val before = node.prev
        val after = node.next
        before?.next = after
        after?.prev = before
        if (after == null){
            last = before
        }
        if (before == null){
            first = after
        }
        size--
    }

    override fun remove(element: T) {
        var node = first
        repeat(size){
            if (node?.item == element){
                node?.let { unlink(it) }
                return
            } else {
                node = node?.next
            }
        }
    }

    override fun clear() {
        first = null
        last = null
        size = 0
    }

    override fun contains(element: T): Boolean {
        var node = first
        repeat(size){
            if (node?.item == element){
                return true
            } else {
                node = node?.next
            }
        }
        return false
    }

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

    inner class Node (
        var prev: Node? = null,
        val item: T,
        var next: Node? = null
    )
}