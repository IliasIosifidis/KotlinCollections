package collections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class NumbersMutableListTest<T> {

    companion object{
        @JvmStatic
        fun mutableListSource() = listOf(MyArayList<T>(), MyLinkedList<T>())
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to first position, it is in first position`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        list.add(0, 1000)
        assertEquals(1000, list[0])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When element added to last position, it is in the last position`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        list.add(100, 1000)
        assertEquals(1000, list[100])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 1 element then size = 1`(list: MyMutableList){
        list.add(0)
        assertEquals(1, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When list is clear the size becomes 0 elements`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        list.clear()
        assertEquals(0, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When the list contains the element, return true`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        assertTrue(list.contains(50))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When the list does not contain the element, return false`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        assertTrue(list.contains(100))
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When get the 5th element then result is correct`(list: MyMutableList){
        repeat(10){
            list.add(it)
        }
        assertEquals(5, list[5])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 10 elements then size = 10`(list: MyMutableList){
        repeat(10){
            list + it
        }
        assertEquals(10, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 100 element then size is 100`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        assertEquals(100, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When add 50 element then size is 50`(list: MyMutableList){
        repeat(50){
            list.add(it)
        }
        assertEquals(50, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removing an element, the size decreases`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        list.removeAt(50)
        assertEquals(99, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removing a value 50, next value at this position`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        list - 50
        assertEquals(51, list.size)
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When removing 50th element, next value takes it position`(list: MyMutableList){
        repeat(100){
            list.add(it)
        }
        list.removeAt(50)
        assertEquals(51, list[50])
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method get invoke wrong index, exceptions is thrown`(list: MyMutableList){
        repeat(10){
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException>{
            list[10]
        }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method add invoke wrong index, exceptions is thrown`(list: MyMutableList){
        repeat(10){
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException>{
            list.add(11, 1000)
        }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method removeAt invoke wrong index, exceptions is thrown`(list: MyMutableList){
        repeat(10){
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException>{
            list.removeAt(10)
        }
    }

    @ParameterizedTest
    @MethodSource("mutableListSource")
    fun `When method get invoke negative index, exceptions is thrown`(list: MyMutableList){
        repeat(10){
            list.add(it)
        }
        assertThrows<IndexOutOfBoundsException>{
            list[-1]
        }
    }
}