# Concurrency options in enterprise software

## Java/Jakarta EE

Prior to Java EE 7, there were no specific APIs that allowed enterprise developers to use concurrency utilities in a safely standard manner. The Java EE web and EJB containers instantiate objects using container-managed thread pools. Therefore, using Java SE concurrent APIs to instantiate Thread objects was strongly discouraged. If a developer creates a new (non-managed) Thread object, the container could not guarantee that other Java EE platform services (for example, transactions and security) would be part of this Thread. ([more on this](https://docs.oracle.com/javaee/7/tutorial/concurrency-utilities001.htm))

Starting from Java EE 7, a [ManagedExecutorService](https://docs.oracle.com/javaee/7/api/javax/enterprise/concurrent/ManagedExecutorService.html) is available.

Currently there is no support for a managed ForkJoinPool in Jakarta EE.
It is strongly advised not to mix a Java SE ForkJoinPool with Jakarta EE technologies, due to the fact that Jakarta EE can only manage threads that it created itself.

## Spring

* Comes with an own implementation of `Executor`, like TaskExecutor. ([source](https://dzone.com/articles/spring-and-threads-taskexecutor))
* Asynchronous methods (higher abstraction level). ([source](https://egkatzioura.com/2017/10/25/spring-and-async/))
* Transactional. ([source](https://egkatzioura.com/2017/10/25/spring-and-threads-transactions/))
* ForkJoinPoolFactoryBean. ([source](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/concurrent/ForkJoinPoolFactoryBean.html))

# Project Loom

A [good introduction](https://cr.openjdk.java.net/~rpressler/loom/loom/sol1_part1.html) of Project Loom.