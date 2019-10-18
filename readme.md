<b> this project records what is needed when learning concurrent programming in java.</b>

对于简单的并行任务，你可以通过<b>“线程池 +Future”</b>的方案来解决；
如果任务之间有聚合关系，无论是 AND 聚合还是 OR 聚合，都可以通过 <b>CompletableFuture</b> 来解决；
而批量的并行任务，则可以通过 <b>CompletionService</b> 来解决。

#### content:
* 线程池 
* CyclicBarrier
* CompletionService
* CompletableFuture
