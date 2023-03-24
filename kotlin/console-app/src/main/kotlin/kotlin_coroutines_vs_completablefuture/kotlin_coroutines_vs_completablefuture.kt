package kotlin_coroutines_vs_completablefuture

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CompletableFuture

// combining two asynchronous operations using Kotlin coroutines
/*
fun main() = runBlocking {
    val job1 = async {
        delay(1000)
        "Hello"
    }
    val job2 = async {
        delay(1000)
        "World"
    }
    println("${job1.await()} ${job2.await()}")
    // Output: Hello World
}
 */

// combining two asynchronous operations using CompletableFuture in Kotlin
/*
fun main() {
    val future1 = CompletableFuture.supplyAsync {
        Thread.sleep(1000)
        "Hello"
    }

    val future2 =CompletableFuture.supplyAsync {
        Thread.sleep(2000)
        "World"
    }

    future1.thenCombine(future2) { s1, s2 -> "$s1 $s2" }
        .thenAccept { println(it) }
        .join()
    // Output: Hello World
}
 */

// combining two asynchronous operations using CompletableFuture and Kotlin coroutines
fun main(): Unit = runBlocking {


    val future1 = async {
        CompletableFuture.supplyAsync {
            Thread.sleep(1000)
            "Hello"
        }
    }

    val future2 = async {
        CompletableFuture.supplyAsync {
            Thread.sleep(2000)
            "World"
        }
    }

    future1.await().thenCombine(future2.await()) { s1, s2 -> "$s1 $s2" }
        .thenAccept { println(it) }
        .join()

    // Output: Hello World
}
