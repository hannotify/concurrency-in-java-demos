package sync;

import com.infosupport.concurjava.scopedvalues.SynchronizedSimpleDateFormat;
import jdk.incubator.concurrent.ScopedValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScopedValueSimpleDateFormat implements SynchronizedSimpleDateFormat {
    private final static ScopedValue<SimpleDateFormat> scopedValue = ScopedValue.newInstance();
    private final String pattern;

    public ScopedValueSimpleDateFormat(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Date parse(String text) throws ParseException {
        var simpleDateFormat = new SimpleDateFormat(pattern);

        try {
            return ScopedValue.where(scopedValue, simpleDateFormat)
                        .call(() -> simpleDateFormat.parse(text));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toPattern() {
        return pattern;
    }
}
