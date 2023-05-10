package sync;

import com.infosupport.concurjava.scopedvalues.SynchronizedSimpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalSimpleDateFormat implements SynchronizedSimpleDateFormat {
    /**
     * SimpleDateFormat is not thread-safe, so give one to each thread
     */
    private final ThreadLocal<SimpleDateFormat> formatter;

    public ThreadLocalSimpleDateFormat(String pattern) {
        formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
    }

    @Override
    public Date parse(String text) throws ParseException {
        return formatter.get().parse(text);
    }

    @Override
    public String toPattern() {
        return formatter.get().toPattern();
    }
}
