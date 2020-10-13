import java.util.Collections;
import java.util.List;

public class SynchronizedList {
    public static void main(String[] args) {
        List<String> cities = List.of("Antwerpen", "Rotterdam", "Utrecht");

        var synchronizedCities = Collections.synchronizedList(cities);
    }
}

