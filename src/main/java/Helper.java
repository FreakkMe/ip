import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// A helper class which helps to deal with certain commands computation
public class Helper {

    // Returns the closest unmarked deadline tasks, maxCount limits number of results
    public static ArrayList<Task> getClosestDeadlines(List<Task> list, int maxCount) {
        return (ArrayList<Task>) list.stream()
                .filter(t -> t instanceof Deadline && t.getStatusIcon().equals(" "))
                .map(t -> (Deadline) t)
                .sorted(Comparator.comparing(Deadline::getTime))
                .limit(maxCount)
                .map(t -> (Task) t)
                .collect(Collectors.toList());
    }

    // Returns the closest unmarked event tasks, maxCount limits number of results
    public static ArrayList<Task> getClosestEvents(List<Task> list, int maxCount) {
        return (ArrayList<Task>) list.stream()
                .filter(t -> t instanceof Event && t.getStatusIcon().equals(" "))
                .map(t -> (Event) t)
                .sorted(Comparator.comparing(Event::getStartTime))
                .limit(maxCount)
                .map(t -> (Task) t)
                .collect(Collectors.toList());
    }
}

