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

    /**
     * 语法形式为 () -> {}，其中 () 用来描述参数列表，{} 用来描述方法体，-> 为 lambda运算符 ，读作(goes to)。
     */
    @Test
    public void define() {
        //1.简化参数类型，可以不写参数类型，但是必须所有参数都不写
        NoReturnMultiParam lamdba1 = (a, b) -> {
            System.out.println("简化参数类型");
        };
        lamdba1.method(1, 2);

        //2.简化参数小括号，如果只有一个参数则可以省略参数小括号
        NoReturnOneParam lambda2 = a -> {
            System.out.println("简化参数小括号");
        };
        lambda2.method(1);

        //3.简化方法体大括号，如果方法条只有一条语句，则可以胜率方法体大括号
        NoReturnNoParam lambda3 = () -> System.out.println("简化方法体大括号");
        lambda3.method();

        //4.如果方法体只有一条语句，并且是 return 语句，则可以省略方法体大括号
        ReturnOneParam lambda4 = a -> a + 3;
        System.out.println(lambda4.method(5));

        ReturnMultiParam lambda5 = (a, b) -> a + b;
        System.out.println(lambda5.method(1, 1));
    }

    /**
     * lambda 表达式引用方法
     * 有时候我们不是必须要自己重写某个匿名内部类的方法，我们可以可以利用 lambda表达式的接口快速指向一个已经被实现的方法。
     * 语法 方法归属者::方法名 静态方法的归属者为类名，普通方法归属者为对象
     */
    @Test
    public void link() {
        ReturnOneParam lambda1 = a -> increment(a);
        System.out.println(lambda1.method(3));

        //lambda4 引用了已经实现的 addTwo 方法
        ReturnOneParam lambda4 = new LambdaTest()::increment;
        System.out.println(lambda4.method(2));
    }

    /**
     * 要求
     * 1.参数数量和类型要与接口中定义的一致
     * 2.返回值类型要与接口中定义的一致
     */
    public static int doubleNum(int a) {
        return a * 2;
    }

    public int increment(int a) {
        return a + 1;
    }

    /**
     * 构造方法的引用
     * 一般我们需要声明接口，该接口作为对象的生成器，通过 类名::new 的方式来实例化对象，然后调用方法返回对象。
     */
    @Test
    public void construct(String[] args) {
        ItemCreatorBlankConstruct creator = () -> new Item();
        Item item1 = creator.getItem();

        ItemCreatorBlankConstruct creator2 = Item::new;
        Item item2 = creator2.getItem();

        ItemCreatorParamContruct creator3 = Item::new;
        Item item3 = creator3.getItem(112, "鼠标", 135.99);
    }

    @FunctionalInterface
    interface ItemCreatorBlankConstruct {
        Item getItem();
    }

    @FunctionalInterface
    interface ItemCreatorParamContruct {
        Item getItem(int id, String name, double price);
    }

    class Item {
        Integer id;
        String name;
        Double price;

        public Item() {

        }
        public Item(Integer id, String name, Double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

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

@FunctionalInterface
interface NoReturnMultiParam {
    void method(int a, int b);
}

/**
 * 无参无返回值
 */
@FunctionalInterface
interface NoReturnNoParam {
    void method();
}

/**
 * 一个参数无返回
 */
@FunctionalInterface
interface NoReturnOneParam {
    void method(int a);
}

/**
 * 多个参数有返回值
 */
@FunctionalInterface
interface ReturnMultiParam {
    int method(int a, int b);
}

/*** 无参有返回*/
@FunctionalInterface
interface ReturnNoParam {
    int method();
}

/**
 * 一个参数有返回值
 */
@FunctionalInterface
interface ReturnOneParam {
    int method(int a);
}
