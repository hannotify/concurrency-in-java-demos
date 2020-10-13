import java.util.stream.IntStream;

public class StarvationDemo {
    public static void main(String[] args) {
        var textFileWriter = new TextFileWriter();

        IntStream.rangeClosed(1, 10)
                .forEach(i -> new Thread(textFileWriter::write).start());
    }
}
