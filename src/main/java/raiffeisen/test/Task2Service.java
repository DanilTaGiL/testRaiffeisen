package raiffeisen.test;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 'Count of elements at list' service.
 */
@Service
public class Task2Service {

    public void run(List<String> elements) {
        List<String> formattedList = formatList(elements);
        HashMap<String, Integer> map = new HashMap<>();

        formattedList.forEach(s -> {
            if (map.containsKey(s))
                map.replace(s, map.get(s) + 1);
            else map.put(s, 1);
        });

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(String.format("%s : %s", entry.getKey(), entry.getValue())));
    }

    private List<String> formatList(List<String> elements) {
        return elements.stream()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.toList());
    }
}