# Java Multithreading

## Motivation for multithreading
 
- **Responsiveness**: Multithreading can allow a program to continue running even if part of it is blocked or is performing a lengthy operation, thereby increasing responsiveness to the user.
- **Performance achieved by concurrency**: Multithreading can allow a program to get more work done at the same time, thereby increasing throughput, and potentially reducing latency.

## What Threads are and what they contain?

- **Thread**: A thread is a single sequential flow of control within a program.
- **Stack**: Each thread has a private JVM stack, created at the same time as thread. A JVM stack stores frames. A stack frame contains the state of one Java method invocation. When a thread invokes a method, the JVM pushes a new frame onto that thread's stack. When the method completes, the JVM pops the frame off the stack and discards it.
- **Instruction Counter**: Address of the next execution instruction for this thread.

## What threads share?

- **Heap**: The heap is shared among all JVM threads. It is the runtime data area from which memory for all class instances and arrays is allocated.
- **Method Area**: The method area is shared among all JVM threads. It stores per-class structures such as the run-time constant pool, field and method data, and the code for methods and constructors, including the special methods used in class and instance initialization and interface initialization.
- **Files**: On some operating systems, one file descriptor may correspond to several file handles, depending on how the file was opened. Under such a scheme, operations on the file descriptor affect all file handles associated with it. Other operating systems require a file descriptor to correspond to a single file handle. In this case, operations on the file descriptor affect only one file handle. In either case, operations on a file (such as seek( ) or read( )) affect all threads that reference the file through a file handle or file descriptor.


## Context Switching

- **Context Switching**: Context switching is the process of storing and restoring the state (context) of a thread so that execution can be resumed from the same point at a later time. Context switches are usually computationally intensive and much slower than other operations.

* Each thread consumes resources in the CPU and memory. So, when we switch from one thread to another,
* we need to **save the state of the current thread and load the saved state of the next thread to be executed**.
* **This process is called context switching**. Context switching is not cheap, and is the price of multitasking.

## Thread Scheduler

- **Thread Scheduler**: The thread scheduler mainly uses preemptive or time slicing scheduling to schedule the threads. Preemptive scheduling is used to schedule the threads according to priority. Time slicing is used to schedule the threads according to time. The thread scheduler mainly uses below given three methods to schedule the threads:
- **Round Robin**: In Round Robin scheduling, each thread is given a fixed time slot in a cyclic way.
- **First Come First Serve**: In First Come First Serve scheduling, as the name suggests, the process which arrives first, gets executed first, or we can say that the process which requests the CPU first, gets the CPU allocated first.
- **Priority Based**: In Priority-Based scheduling, all the threads are given a priority. The thread with the highest priority will be executed first and so on.

```text
Dynamic Priority Scheduling = Static Priority + Bonus (it adjusted by the OS in every epoch, for each thread)
```

## Thread Creation

- [Thread Creation Example](ThreadCreation.java)
- [Exception Handling in Threads](ThreadExceptionHandler.java)

## Thread Termination - Why and When?

- Threads consume resources.
  - Memory and kernel resources.
  - CPU cycles and cache memory.
- If a thread finished its work, but the application is still running, we want to clean up the thread's resources.
- If a thread is misbehaving, we want to stop it.
- By default, the application will not stop as long as at least one thread is still running.

### Thread Termination?

- [Thread Termination Example](ThreadTermination.java)

## Thread Coordination

- [Thread Coordination Example](ThreadCoordination.java)

## Performance Optimization

- **Latency** = T( Time to complete a task) / N (Number of tasks)
-[Latency Example](LatencyOptimization.java)
- **N** Observations:
  - **N**: On a general-purpose computer, the number of tasks that can be performed in parallel is limited by the number of CPUs available.
  - **# threads** == **# cores** is optimal only if all threads are CPU-bound and have no blocking operations.
  - The assumption is nothing else is running that consumes a lot of CPU cycles.
  - **Hyperthreading** is a technology that allows a single CPU core to execute multiple threads in parallel.

- **Throughput** <= N / T. The number of tasks completed per unit of time. 
- [Throughput Example](ThroughHttpServer.java)

## Stack vs Heap

- **Stack**:
  - Local primitive variables.
  - Local references to objects.
  - Method parameters.

- **Heap**:
  - Objects.
  - Static variables.
  - Instance variables.

## Shared Resources

[Thread Shared Resources Example](ThreadSharedInventoryCounter.java)
- **Atomic Operation**: An atomic operation is an operation that is performed as a single unit of execution without the possibility of interference from other operations.
  - Don't have intermediate states.
  - Single Step - *All or nothing*.

### Synchronization

- **Synchronization**: Synchronization is the capability to control the access of multiple threads to any shared resource.
- **Synchronized Block**: A synchronized block in Java is synchronized on some object. All synchronized blocks synchronized on the same object can only have one thread executing inside them at the same time. All other threads attempting to enter the synchronized block are blocked until the thread inside the synchronized block exits the block.
- **Synchronized Method**: When a thread invokes a synchronized method, it automatically acquires the intrinsic lock for that method's object and releases it when the method returns. The lock release occurs even if the return was caused by an uncaught exception.
- **Synchronized Statement**: Unlike synchronized methods, synchronized statements must specify the object that provides the intrinsic lock. In the following example, the addName method needs to synchronize changes to lastName, and the addAddress method needs to synchronize changes to lastAddress. If a thread invokes addName while another thread is executing addAddress, neither thread blocks because each thread acquires only one lock.

```java
public class SynchronizedCounter {
    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}
```

- **Atomic Operations**: Notes
  - All references assignments are atomic.
  - We can get and set references to objects atomically.
  - All assignments to primitive types (except long and double) are atomic. J ava has a solution for long and double types. We can use the AtomicLong and AtomicDouble classes or and Volatile.

## Race Conditions

- Condition when multiple threads are trying to access the same resource and the result of the execution depends on the order in which the threads execute.
- The core of the problem is non-atomic operations performed on the shared resource.

## Date Race

```java
public class SharedClass {
    int x = 0;
    int y = 0;
    
    public void increment() {
        x++; // To guarantee that x will be incremented before y, we need to use synchronized block or method and for Race Condition we can use volatile keyword to avoid it.
        y++;
    }
    
    public void checkForDataRace() {
        if(y > x) {
            throw  new RuntimeException("This should never happen"); // x will be >= y in all cases. But the compiler re-arranges the instructions for better performance, branching prediction, etc.
        }
    }
}
```

## Deadlock
[Deadlock Example](DeadLockIntersection.java)
- **Deadlock**: Deadlock describes a situation where two or more threads are blocked forever, waiting for each other.
We can avoid deadlocks by:
- Enforcing a strict ordering on resource acquisition. 