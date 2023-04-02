import com.devlach.proto.*
import io.grpc.ServerBuilder
import kotlin.random.Random

class Server(val port: Int) {

    val server = ServerBuilder.forPort(port)
        .addService(OrderService())
        .build()

    fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(Thread {
            println("Received Shutdown Request")
            this.stop()
            println("Successfully stopped the server")
        })
    }

    fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    private class OrderService : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
        override suspend fun getOrders(request: OrderRequest): OrderResponse {
            val ordersResp = mutableListOf<Order>()
            for (i in 1..request.totalOrders) {
                val order = order {
                    id = i
                    customerName = "Customer $i"
                    for (j in 1..request.totalItems) {
                        items += item {
                            name = "Item $j"
                            price = Random.nextDouble(1.0, 100.0)
                        }
                    }
                    totalCost = items.sumOf { it.price }
                }
                ordersResp += order
            }
            return orderResponse {
                orders += ordersResp
            }
        }
    }
}

fun main() {
    val server = Server(System.getenv("PORT")?.toInt() ?: 50051)
    server.start()
    server.blockUntilShutdown()
}