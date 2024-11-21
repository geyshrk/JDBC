import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class StreamPain {

    public static void main(String[] args) {

        var a = IntStream.range(0, 10)
                .mapToObj(i -> i)

                .flatMap(i ->
                    LongStream.range(0, i).mapToObj(j -> j)
                )
                .peek(l -> System.out.println(l))
                .collect(Collectors.groupingBy(i-> i, Collectors.counting()));


    }

}
