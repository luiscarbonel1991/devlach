package kotlin_coroutines_basic_approach

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

data class UserInfo(val name: String, val lastName: String) // class to represent a user
data class TrackingInfo(val webSites: List<String>)  // class to represent a tracking
data class Profile(val userinfo: UserInfo?, val trackingInfo: TrackingInfo?) // class to represent a profile

val users = mapOf(1 to UserInfo(name = "Luis", lastName = "Foo"), 2 to UserInfo(name = "Carlos", lastName = "Bar")) // contains all user in a map[idUser, UserInfo]
val tracking = mapOf(1  to TrackingInfo(listOf("vercel.com", "devlach.com")), 2  to TrackingInfo(listOf("react.com", "devlach.com"))) // contains all tracking in a map[idUser, TrackingInfo]

// Sequential approach
class SequentialApproach {
    fun getProfile(id: Int): Profile {
        val user = findUserInfo(id)
        val trackingInfo = findTrackingInfo(id)
        return Profile(user, trackingInfo)
    }

    private fun findUserInfo(id: Int): UserInfo? {
        Thread.sleep(1500)  // Block the thread for 1500 milliseconds
        return users[id]
    }
    private  fun findTrackingInfo(id: Int): TrackingInfo? {
        Thread.sleep(1500) // Block the thread for 1500 milliseconds
        return tracking[id]
    }
}

// Asynchronous approach
class ConcurrentApproach {
    suspend fun getProfile(id: Int): Profile = runBlocking {
        val user = async { findUserInfo(id) }
        val trackingInfo = async { findTrackingInfo(id) }
        return@runBlocking Profile(user.await(), trackingInfo.await())
    }

    private suspend fun findUserInfo(id: Int): UserInfo? {
        delay(1500) // Not blocking thread
        return users[id]
    }
    private suspend fun findTrackingInfo(id: Int): TrackingInfo? {
        delay(1500) // Not blocking thread
        return tracking[id]
    }
}

// Run sequential approach

/*
fun main() {
    val sequentialTime = measureTimeMillis {
        SequentialApproach().getProfile(1).apply { println(this) }
    }
    println("SequentialTime was : $sequentialTime")
}
 */

// Output
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// SequentialTime was : 3001

// Run sequential vs concurrent approach
/*
suspend fun main() {
    val sequentialTime = measureTimeMillis {
        SequentialApproach().getProfile(1).apply { println(this) }
    }
    println("SequentialTime : $sequentialTime")

    val concurrentTime = measureTimeMillis {
        ConcurrentApproach().getProfile(1).apply { println(this) }
    }

    println("ConcurrentTime : $concurrentTime")
}
*/

// Output
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// SequentialTime : 3001
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// ConcurrentTime : 1545


class UserRepository {
    suspend fun findUserInfo(id: Int): UserInfo? {
        delay(1500) // Not blocking thread
        return users[id]
    }
}

class TrackingRepository {
    suspend fun findTrackingInfo(id: Int): TrackingInfo? {
        delay(1500) // Not blocking thread
        return tracking[id]
    }
}

object CoroutineUtils {
    private val defaultScope = CoroutineScope(Dispatchers.IO + CoroutineName("General Purpose")) // Default scope
    suspend fun <T> runAsync( block: suspend () -> T) = defaultScope.async { block() } // Receive a suspend function and executes it in our custom scope
}

// For debugging purposes
/*
object CoroutineUtils {
    private val defaultScope = CoroutineScope(Dispatchers.IO + CoroutineName("General Purpose")) // Default scope
    suspend fun <T> runAsync(coroutineScope: CoroutineScope = defaultScope, block: suspend () -> T) = coroutineScope.async { // Receive a suspend function and executes it in our custom scope
        println("${this.coroutineContext[CoroutineName.Key]} is executing in ${Thread.currentThread().name}") // Print current coroutine name and thread name
        block() }
}
 */

class ProfileService(private val userRepository: UserRepository, private val trackingRepository: TrackingRepository) {

    suspend fun getProfile(id: Int): Profile {
        val userInfo = CoroutineUtils.runAsync { userRepository.findUserInfo(id) }
        val trackingInfo = CoroutineUtils.runAsync { trackingRepository.findTrackingInfo(id) }
        return Profile(userinfo = userInfo.await(), trackingInfo = trackingInfo.await())
    }
}

// For debugging purposes
/*
class ProfileService(private val userRepository: UserRepository, private val trackingRepository: TrackingRepository) {

    private val scope = CoroutineScope(Dispatchers.IO + CoroutineName("Service")) // this is optional, but you can create you own scope

    suspend fun getProfile(id: Int): Profile {
        val userInfo = CoroutineUtils.runAsync(scope) { userRepository.findUserInfo(id) }
        val trackingInfo = CoroutineUtils.runAsync { trackingRepository.findTrackingInfo(id) }
        return Profile(userinfo = userInfo.await(), trackingInfo = trackingInfo.await())
    }
}
 */


suspend fun main() {
    val sequentialTime = measureTimeMillis {
        SequentialApproach().getProfile(1).apply { println(this) }
    }
    println("SequentialTime : $sequentialTime")

    val concurrentTime = measureTimeMillis {
        ConcurrentApproach().getProfile(1).apply { println(this) }
    }

    println("ConcurrentTime : $concurrentTime")

    val structuredTime = measureTimeMillis {
        // Init instances
        val profileService = ProfileService(UserRepository(), TrackingRepository())
        // Call the service
        profileService.getProfile(id = 1).also { println(it) }
    }
    println("StructuredTime : $structuredTime")
}


// Output
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// SequentialTime : 3001
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// ConcurrentTime : 1542
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// StructuredTime : 1525

// Output with debugging
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// SequentialTime : 3001
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// ConcurrentTime : 1550
// CoroutineName(Service) is executing in DefaultDispatcher-worker-1
// CoroutineName(General Purpose) is executing in DefaultDispatcher-worker-3
// Profile(userinfo=UserInfo(name=Luis, lastName=Foo), trackingInfo=TrackingInfo(webSites=[vercel.com, devlach.com]))
// StructuredTime : 1509