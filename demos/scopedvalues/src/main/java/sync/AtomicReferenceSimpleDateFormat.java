package sync;

import com.infosupport.concurjava.scopedvalues.SynchronizedSimpleDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceSimpleDateFormat implements SynchronizedSimpleDateFormat {
    private final AtomicReference<SimpleDateFormat> simpleDateFormat;

    public AtomicReferenceSimpleDateFormat(String pattern) {
        simpleDateFormat = new AtomicReference<>(new SimpleDateFormat(pattern));
    }

    @Override
    public Date parse(String text) throws ParseException {
        return simpleDateFormat.get().parse(text);
    }

    @Override
    public String toPattern() {
        return simpleDateFormat.get().toPattern();
    }
}
