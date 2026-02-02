package Freaky;

import java.util.Comparator;

// A helper class which helps to deal with certain commands computation
public class Helper {

    // Returns the closest unmarked deadline tasks, maxCount limits number of results
    public static TaskList getClosestDeadlines(TaskList taskList, int maxCount) {
        TaskList result = new TaskList();

        taskList.getTasks().stream()
                .filter(t -> t instanceof Deadline && t.getStatusIcon().equals(" "))
                .map(t -> (Deadline) t)
                .sorted(Comparator.comparing(Deadline::getTime))
                .limit(maxCount)
                .forEach(result::add);

        return result;
    }

    // Returns the closest unmarked event tasks, maxCount limits number of results
    public static TaskList getClosestEvents(TaskList taskList, int maxCount) {
        TaskList result = new TaskList();

        taskList.getTasks().stream()
                .filter(t -> t instanceof Event && t.getStatusIcon().equals(" "))
                .map(t -> (Event) t)
                .sorted(Comparator.comparing(Event::getStartTime))
                .limit(maxCount)
                .forEach(result::add);

        return result;
    }
}

