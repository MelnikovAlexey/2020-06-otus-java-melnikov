package org.otus.education.hw04.bench;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Benchmark {
    private final int cycleCount;
    private final int oldBatchSize;

    public Benchmark(int cycleCount, int oldBatchSize) {
        this.cycleCount = cycleCount;
        this.oldBatchSize = oldBatchSize;
    }

   public void run() throws InterruptedException {
       final ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
       threadMxBean.setThreadCpuTimeEnabled(true);
       final long threadId = Thread.currentThread().getId();


       List<Long> cpuEvaluationTimes = new ArrayList<>(cycleCount);
       List<Long> evaluationTimes = new ArrayList<>(cycleCount);
       List<BigInteger> storageBigIntegers = new ArrayList<>();

       final long startOOM = System.currentTimeMillis();

       for (int i = 0; i < cycleCount; i++) {
           try {
               //запись времени старта одной задачи
               final long cpuTimeStart = threadMxBean.getThreadCpuTime(threadId);
               final long timeStart = System.currentTimeMillis();

               //эмитация каких то вычислений
               BigInteger resultCalculation = new BigInteger(1200, 9, new Random());

               //сохраняем время выполнения задачи

               cpuEvaluationTimes.add(threadMxBean.getThreadCpuTime(threadId) - cpuTimeStart);
               evaluationTimes.add(System.currentTimeMillis() - timeStart);

               //добавляем работы GC
               for (int addBatchIndex = 0; addBatchIndex < oldBatchSize; addBatchIndex++) {
                   storageBigIntegers.add(resultCalculation);
               }

           } catch (OutOfMemoryError outOfMemoryError) {
               //перехватываем OOM записываем время до выпадения для статистики
               //и освобождаем место (понятно что в реальном приложении  - это вилами по воде:))
               System.out.println("TIME BEFORE OOM: " + (System.currentTimeMillis() - startOOM));
               storageBigIntegers.clear();
           }
       }

       System.out.println(storageBigIntegers.hashCode());

       System.out.println("AVG CPU Time:  " + TimeUnit.NANOSECONDS.toMillis((long) cpuEvaluationTimes
               .stream()
               .mapToLong(a -> a)
               .average().getAsDouble()));

       System.out.println("AVG Time:  " + evaluationTimes
               .stream()
               .mapToLong(a -> a)
               .average().getAsDouble());
   }

}
