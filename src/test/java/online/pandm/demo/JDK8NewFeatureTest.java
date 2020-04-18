package online.pandm.demo;

import online.pandm.demo.study.jdk8NewFeature.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SpringBootTest
public class JDK8NewFeatureTest {

    @Test
    public void test_01() {
        // 入参a 和 实现
//        IFormula formula = a -> a * a;
//        System.out.println(formula.calculate(2));
//        System.out.println(formula.sqrt(2));
        IFormula formula = new IFormula() {
            @Override
            public double calculate(int a) {
                //访问默认接口方法
                sqrt(a);
                return a * a;
            }
        };
    }

    @Test
    public void test_02() {
        //传统方式
        IConverter<String, Integer> converter01 = new IConverter<String, Integer>() {
            @Override
            public Integer convert(String from) {
                return Integer.valueOf(from);
            }
        };
        //稍微简化下，化个妆 & (form)，只有一个参数括号可以不要
        IConverter<String, Integer> converter02 = (from) -> {
            return Integer.valueOf(from);
        };
        //继续简化，因为他的实现只有一行代码，可以更加简短
        IConverter<String, Integer> converter03 = from -> Integer.valueOf(from);
        int converted03 = converter03.convert("11");
        //还能短点，其实这个另类属于下一段的内容了，先放这有个印象
        IConverter<Integer, String> converter04 = String::valueOf;
        String converted04 = converter04.convert(11);
        System.out.println(converted04);

        //[参照物]直接把逻辑放到这调用
        IConverter<String, String> converter05 = s -> String.valueOf(s.charAt(0));
        //引用的方法体里面逻辑可以更多，否则只是一句代码并不能适合所有的情况
        Something something = new Something();
        //放弃接口使用，直接使用本类的静态方法取代接口中的方法
        IConverter<String, String> converter06 = something::startsWith;
        System.out.println(converter05.convert("Java"));
        System.out.println(converter06.convert("Java"));

        IPersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.getFirstName());
    }

    @Test
    public void test_03() {
        ToStringExample toStringExample = new ToStringExample();
        System.out.println(toStringExample.toString());
    }
    @Test
    public void test_04() {
        Person person = new Person("22", "lisi");
        Predicate<Person> predicate = Predicate.isEqual(person);
        Stream.of(
            new Person("21", "zhangsan"),
            new Person("22", "lisi"),
            new Person("23", "wangwu"),
            new Person("24", "wangwu"),
            new Person("22", "lisi"),
            new Person("26", "zhangsan")
        )
        .filter(predicate)
        .forEach(System.out::println);
    }
    public void printFirstName(Person person){
        System.out.println(person.getFirstName());
    }

    /**
     *  内置的函数式接口
     */
    //Predicate 断言是一个可以指定入参类型，并返回 boolean 值的函数式接口。可以被用来组合一个复杂的逻辑判断（and, or, negate）
    @Test
    public void test11() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        Predicate<String> predicate1 = (s) -> s.length() < 10;
        boolean foo0 = predicate.or(predicate1).test("foo"); // true
        boolean foo1 = predicate.negate().test("foo"); // negate否定相当于!true
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
    //Functions 函数式接口的作用是，我们可以为其提供一个原料，他给生产一个最终的产品。通过它提供的默认方法，组合,链行处理(compose, andThen)
    @Test
    public void test12() {
        Function<String, Integer> toInteger = Integer::valueOf; //转Integer
        Function<String, String> backToString = toInteger.andThen(String::valueOf); //转String
        Function<String, String> afterToStartsWith = backToString.andThen(new Something()::startsWith); //截取第一位
        String apply = afterToStartsWith.apply("123");// "123"
        System.out.println(apply);
    }
    //Supplier 与 Function 不同，它不接受入参，直接为我们生产一个指定的结果，有点像生产者模式：
    @Test
    public void test13() {
        Supplier<Person> personSupplier0 = Person::new;
        personSupplier0.get(); // new Person
        Supplier<String> personSupplier1 = Something::test01; //这个test方法是静态的，且无入参
        System.out.println(personSupplier1.get()); // hi

        Supplier<String> personSupplier2 = new Something()::test02;
        System.out.println(personSupplier2.get()); // hi
    }
    //Consumer 对于 Consumer，我们需要提供入参，用来被消费，如下面这段示例代码：
    @Test
    public void test14() {
        Consumer<Person> greeter01 = new Consumer<Person>() {
            @Override
            public void accept(Person p) {
                System.out.println("Hello, " + p.firstName);
            }
        };
        Consumer<Person> greeter02 = (p) -> System.out.println("Hello, " + p.firstName);
        greeter02.accept(new Person("Luke", "Skywalker")); //Hello, Luke
        Consumer<Person> greeter03 = this::accept; // 也可以通过定义类和方法的方式去调用，这样才是实际开发的姿势
        greeter03.accept(new Person("Luke", "Skywalker")); //Hello, Luke
    }
    //Comparator 在 Java 8 之前是使用比较普遍的。Java 8 中除了将其升级成了函数式接口，还为它拓展了一些默认方法
    @Test
    public void test15(){
        Comparator<Person> comparator1 = new Comparator<Person>(){
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }
        };
        Comparator<Person> comparator01 = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Comparator<Person> comparator02 = Comparator.comparing(p -> p.firstName); //等同于上面的方式
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        System.out.println(comparator01.compare(p1, p2)); // > 0
        System.out.println(comparator02.reversed().compare(p1, p2)); // < 0
    }

    @Test
    public void test16(){
        System.out.println("124".compareTo("123467"));
    }


    //stream流操作的map方法做中间转换
    @Test
    public void test19(){

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder()) //等同于(a, b) -> b.compareTo(a)
                .forEach(System.out::println);
    }

    //新特性对map的优化
    @Test
    public void test25() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前， 不用在写if null continue了
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, "val" + i);
        }
        // forEach 可以很方便地对 map 进行遍历操作
        map.forEach((key, value) -> {
            if(key==1){
                System.out.println(value);
            }
        });
    }

    @Test
    public void test30() {
        Map<Integer,String> map = new HashMap<>();
        // merge 方法，会先判断进行合并的 key 是否存在，不存在，则会添加元素
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9); // val9
        // 若 key 的元素存在，则对 value 执行拼接操作
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9); // val9concat
    }

    //localDate的新特性
    @Test
    public void test33(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2)); // false
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween); // -3
        System.out.println(minutesBetween); // -239
    }

    @Test
    public void test34(){
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late); // 23:59:59
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);
        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime); // 13:37
    }

    @Test
    public void test36(){
        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);

//        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//        LocalDate xmas = LocalDate.parse("2014-01-02", germanFormatter);
//        System.out.println(xmas); // 2014-12-24

//        LocalDateTime格式化
//        LocalDateTime time=LocalDateTime.now();
//        System.out.println(time);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//        String strDate2 = dtf2.format(time);
        String strDate2 = "12-11-2019";
        System.out.println(strDate2);
        LocalDate time = LocalDate.parse(strDate2,dtf2);
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String strDate3 = dtf3.format(time);
        System.out.println(strDate3);
//        LocalDate转String ，String转LocalDate
//        LocalDate data=LocalDate.now();
//        System.out.print(data);
//        System.out.println(strDate3);
//        strDate3=strDate3+" 04:00:00";
//        LocalDateTime time1=LocalDateTime.parse(strDate3,dtf2);
//        System.out.print(time1);
//        System.out.print(time.isAfter(time1));

        //localdatetime中带有毫秒时存在异常
        String x = "20180225142051591";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
//        LocalDateTime t1 = LocalDateTime.parse(x, dtf);

        DateTimeFormatter dtf1 = new DateTimeFormatterBuilder().appendPattern("yyyyMMddHHmmss")
                .appendValue(ChronoField.MILLI_OF_SECOND,3).toFormatter();

        System.out.println(dtf1.parse(x));

//        Map<Integer/*年级*/, Map<Integer/*班级*/, Long/*人数*/>> integerMap = list.stream().filter(t -> t.getScore() >= 60).collect(Collectors.groupingBy(t -> t.getGrade(), Collectors.groupingBy(t -> t.getClasses(), Collectors.counting())));
//        System.out.println("取出一年级一班及格人数："+integerMap.get(1).get(1));

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.values();

    }

    @Test
    public void test37(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //由于线程非安全，所以往pool中同时放入100个任务并开启10000个线程去执行如下
                //的代码就会报错。100不报错，就改成1000,1000不报错就改成10000.你的机子
                //越牛逼。这个数字就越大。但是肯定会报错
                return sdf.parse("20181012");
            };
        };



        List<Future<Date>> dates = new ArrayList();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for(int i=0;i<10000;i++){
            dates.add(pool.submit(callable));
        }

        dates.forEach((Future<Date> dateFuture) -> {
            try {
                System.out.println(dateFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        pool.shutdown();
    }

    public void accept(Person p) {
        System.out.println("Hello, " + p.firstName);
    }
}
