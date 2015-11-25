package my.algo.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataUtils {
    private DataUtils() {
    }

    public static <T> List<T> readInpData(String fName, Function<String, T> op, Boolean headLine) throws IOException {
        try (final Stream<String> data = Files.lines(Paths.get(fName))) {
            return readInpData(data, op, headLine);
        }
    }

    public static <T> List<T> readInpData(Stream<String> inpDataStream, Function<String, T> op, Boolean headLine) {
        final int headerLines = headLine ? 1 : 0;
        return inpDataStream.skip(headerLines).map(op::apply).collect(Collectors.toList());
    }
}