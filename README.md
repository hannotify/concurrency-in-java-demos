# Concurrency options in enterprise software

## Java/Jakarta EE

Prior to Java EE 7, there were no specific APIs that allowed enterprise developers to use concurrency utilities in a safely standard manner. The Java EE web and EJB containers instantiate objects using container-managed thread pools. Therefore, using Java SE concurrent APIs to instantiate Thread objects was strongly discouraged. If a developer creates a new (non-managed) Thread object, the container could not guarantee that other Java EE platform services (for example, transactions and security) would be part of this Thread. ([more on this](https://docs.oracle.com/javaee/7/tutorial/concurrency-utilities001.htm))

Starting from Java EE 7, a [ManagedExecutorService](https://docs.oracle.com/javaee/7/api/javax/enterprise/concurrent/ManagedExecutorService.html) is available.

## Spring