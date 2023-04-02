import com.devlach.proto.Order
import com.devlach.proto.OrderServiceGrpc
import com.devlach.proto.orderRequest
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class OrderClient(val channel: ManagedChannel) {
    private val stub = OrderServiceGrpc.newBlockingStub(channel)

    fun getOrders(totalOrdersToGenerate: Int = 5, totalItemsPerOrder: Int = 10): List<Order> {
        val request = orderRequest {
            totalOrders = totalOrdersToGenerate
            totalItems = totalItemsPerOrder
        }
        val response = stub.getOrders(request)
        return response.ordersList
    }

    fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}


fun main() {
    val channel = ManagedChannelBuilder.forTarget("localhost:50051")
        .usePlaintext()
        .build()
    val client = OrderClient(channel)
    val timeMilliseconds = measureTimeMillis {
        // 2 orders with 10 items each
        /*
        println("Orders result: ${client.getOrders(
            totalOrdersToGenerate = 2,
            totalItemsPerOrder = 5
        )}")
         */
        // print the size of the result of 5 orders with 10 items each
        println("Orders result size: ${client.getOrders(
            totalOrdersToGenerate = 5,
            totalItemsPerOrder = 10
        ).size}")
        // Output:
        // Orders result size: 5
        // Time taken: 101 ms

        // print the size of the result of 10000 orders with 15 items each
        println("Orders result size: ${client.getOrders(
            totalOrdersToGenerate = 10000,
            totalItemsPerOrder = 15
        ).size}")
        // Output:
        // Orders result size: 10000
        // Time taken: 142 ms

    }
    println("Time taken: $timeMilliseconds ms")
    client.close()
}