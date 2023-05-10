import java.text.ParseException;
import java.util.Date;

public interface SynchronizedSimpleDateFormat {
    Date parse(String text) throws ParseException;
    String toPattern();
}