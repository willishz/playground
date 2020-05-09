package org.willishz.playground.grammar;

import org.junit.Test;
import org.willishz.playground.vo.User;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * lambda表达式测试
 */
public class LambdaTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        List<Integer> integers = Arrays.asList(4, 5, 6, 1, 2, 3, 7, 8, 8, 9, 10);
        List<Integer> evens = integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList()); //过滤出偶数列表 [4,6,8,8,10]<br>
        List<Integer> sortIntegers = integers.stream().sorted().limit(5).collect(Collectors.toList());//排序并且提取出前5个元素 [1,2,3,4,5]
        List<Integer> squareList = integers.stream().parallel().map(i -> i * i).collect(Collectors.toList());//转成平方列表
        squareList.stream().forEach(n -> System.out.println(n));
        int sum = integers.stream().mapToInt(Integer::intValue).sum();//求和
        Set<Integer> integersSet = integers.stream().collect(Collectors.toSet());//转成其它数据结构比如set
        Map<Boolean, List<Integer>> listMap = integers.stream().collect(Collectors.groupingBy(i -> i % 2 == 0)); //根据奇偶性分组
        List<Integer> list = integers.stream().filter(i -> i % 2 == 0).map(i -> i * i).distinct().collect(Collectors.toList());//复合操作

        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action"); // 由值创建流
        try {
            Stream<String> lines = Files.lines(Paths.get("src/main/resources", "application.yml"), Charset.defaultCharset()); // 由文件创建流
            lines.forEach(n -> System.out.println(n));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Stream.iterate(0, n -> n + 2).forEach(System.out::println); // 无限Stream
        Thread t1 = new Thread(() -> System.out.println("use lambda"));
        Thread t2 = new Thread(() -> System.out.println("use lambda"));

        GreetingService greetService = (message) -> System.out.println("Hello " + message); // 用括号
        greetService.sayMessage("Google");

        List<User> users = Arrays.asList(
                new User("aaa@qq.com", "aaa", "123", "哎哎哎", new Date()),
                new User("bbb@qq.com", "bbb", "123", "不不不", new Date()),
                new User("ccc@qq.com", "ccc", "123", "擦擦擦", new Date()),
                new User("ddd@qq.com", "ddd", "123", "大大大", new Date())
        );
        users = filterUser(users, (user) -> {
            if ("aaa@qq.com".equalsIgnoreCase(user.getEmail())) {
                return true;
            }
            return false;
        });
        users.forEach(u -> System.out.println(u));

        long[] arrayOfLong = new long[20000];

        Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();
        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    /**
     * 筛选
     *
     * @param rawUserList
     * @param predicate
     * @return
     */
    private static List<User> filterUser(List<User> rawUserList, IUserPredicate predicate) {
        List<User> result = new ArrayList<>();
        if (rawUserList == null || rawUserList.isEmpty()) {
            return result;
        }
        for (User user : rawUserList) {
            if (predicate.test(user)) {
                result.add(user);
            }
        }
        return result;
    }

    /**
     * Java8简洁的for循环
     * 不同于 for，range 不会强迫我们初始化某个可变变量。
     * 迭代会自动执行，所以我们不需要像循环索引一样定义增量。
     */
    public static void loopInLambda() {
        IntStream.range(0, 5).forEach(i -> {
            System.out.print(i);
            System.out.println();
        });
        IntStream.rangeClosed(100, 105).forEach(i -> {
            System.out.print(i);
            System.out.println();
        });
    }
}

interface IUserPredicate {
    boolean test(User user);
}

@FunctionalInterface
interface GreetingService {
    void sayMessage(String message);
}

