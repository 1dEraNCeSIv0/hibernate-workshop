package de.andrena.hibernateworkshop.test.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.stream.Stream;

@Component
public class StopWatchControl {

    private StopWatch stopWatch = new StopWatch("Test Profiling");

    void start(String taskName) {
        stopWatch.start(taskName);
    }

    void stop() {
        stopWatch.stop();
    }

    void printTaskExecutionTime() {
        var taskInfo = stopWatch.getLastTaskInfo();
        System.out.printf("Profiling target: %s\nExecution time: %sms\n", taskInfo.getTaskName(), taskInfo.getTimeMillis());
    }

    void handleLastRepetition(int totalRepetitions) {
        if (stopWatch.getTaskCount() == totalRepetitions) {
            printSummary();
            clear();
        }
    }

    private void printSummary() {
        var taskInfo = stopWatch.getTaskInfo();
        double average = Stream.of(taskInfo).mapToLong(StopWatch.TaskInfo::getTimeMillis).average().orElse(0d);
        double median = calculateMedian(taskInfo);
        System.out.printf("""
                                
                Summary
                -------
                # repetitions: %d
                Average execution time: %.0fms
                Median execution time: %.0fms
                                
                """, taskInfo.length, average, median);
    }

    private double calculateMedian(StopWatch.TaskInfo[] taskInfo) {
        var length = taskInfo.length;
        var elementsToSkip = (length % 2 == 0) ? length / 2 - 1 : length / 2;
        var medianElements = (length % 2 == 0) ? 2 : 1;
        //noinspection OptionalGetWithoutIsPresent
        return Stream.of(taskInfo)
                .mapToLong(StopWatch.TaskInfo::getTimeMillis)
                .sorted()
                .skip(elementsToSkip)
                .limit(medianElements)
                .average()
                .getAsDouble();
    }

    private void clear() {
        this.stopWatch = new StopWatch("Test Profiling");
    }

}
