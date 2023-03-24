package kotlin_coroutines_vs_completablefuture

import kotlinx.coroutines.*
import java.util.concurrent.CompletableFuture
import kotlin.system.measureTimeMillis

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
/*
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
 */




// Kotlin's coroutines to CompletableFuture example
fun main(): Unit = runBlocking {
    measureTimeMillis {
        val worldJob = async {
            delay(3000)
            "World"
        }

        val helloCompletableFuture = helloCompletableFuture()

        // if we combine helloCompletableFuture with worldJob, we get a compilation error because
        // worldJob is a Job and not a CompletableFuture
        /*
         helloCompletableFuture.thenCombine(worldJob.await()) { s1, s2 -> "$s1 $s2" }
             .thenAccept { println(it) }
             .join()
         */

        // convert worldJob to CompletableFuture
        val worldCompletableFuture = worldJob.asCompletableFuture()

        // now we can combine helloCompletableFuture with worldCompletableFuture
        helloCompletableFuture.thenCombine(worldCompletableFuture) { s1, s2 -> "$s1 $s2" }
            .thenAccept { println(it) }
            .join()

    }.also { println("Time: $it") }

    // Output: Hello World
    // Time: 3001
}

fun helloCompletableFuture(): CompletableFuture<String> {
    return CompletableFuture.supplyAsync {
        Thread.sleep(1000)
        "Hello"
    }
}

// Kotlin coroutines to CompletableFuture
suspend fun  <T> Deferred<T>.asCompletableFuture(): CompletableFuture<T> {
    return CompletableFuture.completedFuture(await())
}
