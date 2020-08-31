package org.otus.education.hw04;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.otus.education.hw04.bench.Benchmark;

import javax.management.*;
import java.lang.management.GarbageCollectorMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final Map<String, Long> statistics = new HashMap<>();

        switchOnMonitoring(statistics);


        final long start = System.currentTimeMillis();
        Benchmark benchmark = new Benchmark(2500, 15000);
        benchmark.run();

        System.out.println(statistics.toString());
        System.out.println("ALL RUN TIME: " + (System.currentTimeMillis() - start));
    }

    private static void switchOnMonitoring(Map<String,Long> statistics) {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    if (gcbean.getName().equalsIgnoreCase("PS Scavenge")
                            || gcbean.getName().equalsIgnoreCase("G1 Young Generation")
                            || gcbean.getName().equalsIgnoreCase("Copy")) {
                        statistics.put("YOUNG GC COUNT", gcbean.getCollectionCount());
                        statistics.put("YOUNG GC TIME", gcbean.getCollectionTime());

                    } else if (gcbean.getName().equalsIgnoreCase("PS MarkSweep")
                            || gcbean.getName().equalsIgnoreCase("G1 Old Generation")
                            || gcbean.getName().equalsIgnoreCase("MarkSweepCompact")) {
                        statistics.put("OLD GC COUNT", gcbean.getCollectionCount());
                        statistics.put("OLD GC TIME", gcbean.getCollectionTime());
                    }
                }

            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
