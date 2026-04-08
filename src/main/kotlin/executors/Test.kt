package executors

import java.util.concurrent.Executors

fun main() {
    val executorService = Executors.newCachedThreadPool()
    repeat(10_000){
        executorService.execute {
            processImage(Image(it))
        }
    }
}

private fun processImage(image: Image){
    println("The image $image is processing")
    Thread.sleep(1000)
    println("The image has been processed")
}

data class Image(val id: Int)