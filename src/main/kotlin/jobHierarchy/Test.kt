package jobHierarchy

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

private val dispatcherIO = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val parent = Job()
private val scope = CoroutineScope(dispatcherIO + parent)

fun main() {
        scope.launch {
            coroutineContext.job.parent?.let { println(it) }
            printNumber(1)
        }
        scope.launch {
            coroutineContext.job.parent?.let { println(it) }
            printNumber(2)
    }
    Thread.sleep(3000)
    parent.cancel()
    println("d")
}

private suspend fun printNumber(number: Int){
    while (true){
        println(number)
        delay(1000)
    }
}