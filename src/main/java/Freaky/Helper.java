package Freaky;

import java.util.Comparator;

/**
 * Utility class for task-related helper functions.
 */
public class Helper {

    /**
     * Returns the closest maxCount deadlines from the task list that are not done.
     *
     * @param tasks TaskList to filter.
     * @param maxCount Max number of deadlines to return (returns <= maxCount deadlines).
     * @return TaskList of closest deadlines.
     */
    public static TaskList getClosestDeadlines(TaskList tasks, int maxCount) {
        TaskList result = new TaskList();

        tasks.getTasks().stream()
             .filter(t -> t instanceof Deadline && t.getStatusIcon().equals(" "))
             .map(t -> (Deadline) t)
             .sorted(Comparator.comparing(Deadline::getTime))
             .limit(maxCount)
             .forEach(result::add);

        return result;
    }

    /**
     * Returns the closest maxCount events from the task list that are not done.
     *
     * @param tasks TaskList to filter.
     * @param maxCount Max number of events to return (returns <= maxCount events).
     * @return TaskList of closest events.
     */
    public static TaskList getClosestEvents(TaskList tasks, int maxCount) {
        TaskList result = new TaskList();

        tasks.getTasks().stream()
             .filter(t -> t instanceof Event && t.getStatusIcon().equals(" "))
             .map(t -> (Event) t)
             .sorted(Comparator.comparing(Event::getStartTime))
             .limit(maxCount)
             .forEach(result::add);

        return result;
    }
}

