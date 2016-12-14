package my.algo.base;

import net.jodah.typetools.TypeResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DataUtils {
    private DataUtils() {
    }

    public static <T> Stream<String> readToStream(String fName) throws IOException {
        return Files.lines(Paths.get(fName));
    }

    public static <T> Stream<T> readToStream(String fName, Function<String, T> op, Boolean headLine) throws IOException {
        final int headerLines = headLine ? 1 : 0;
        return readToStream(fName).skip(headerLines).map(op::apply);
    }

    public static <T> List<T> readToList(String fName, Function<String, T> op, Boolean headLine) throws IOException {
        return readToStream(fName, op, headLine).collect(toList());
    }

    public static <T> T[] readToArray(String fName, Function<String, T> op, Boolean headLine) throws IOException {
        List<T> ts = readToList(fName, op, headLine);
//        op.getClass().getTypeParameters().getClass()
//        T[] arr = (T[]) java.lang.reflect.Array.newInstance(token , ts.size());
        Class<?>[] classTokens = TypeResolver.resolveRawArguments(Function.class, op.getClass());
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(classTokens[1] , ts.size());
        return ts.toArray(arr);
    }

}