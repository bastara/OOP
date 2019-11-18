//public class Test {


//    хороший пример работы интерфейсов
//    public static void main(String[] args) {
//
//        Printable printable = createPrintable("Foreign Affairs",false);
//        printable.print();
//
//        read(new Book("Java for impatients", "Cay Horstmann"));
//        read(new Journal("Java Dayly News"));
//    }
//
//    static void read(Printable p){
//
//        p.print();
//    }
//
//    static Printable createPrintable(String name, boolean option){
//
//        if(option)
//            return new Book(name, "Undefined");
//        else
//            return new Journal(name);
//    }
//}
//interface Printable{
//
//    void print();
//}
//class Book implements Printable{
//
//    String name;
//    String author;
//
//    Book(String name, String author){
//
//        this.name = name;
//        this.author = author;
//    }
//
//    public void print() {
//
//        System.out.printf("%s (%s) \n", name, author);
//    }
//}
//class Journal implements Printable {
//
//    private String name;
//
//    String getName(){
//        return name;
//    }
//
//    Journal(String name){
//
//        this.name = name;
//    }
//    public void print() {
//        System.out.println(name);
//    }
//}


//    public static void main(String[] args) {
//
//        WaterPipe pipe = new WaterPipe();
//        pipe.printState(1);
//    }
//}
//
//class WaterPipe implements Stateable {
//
//    public void printState(int n) {
//        if (n == OPEN)
//            System.out.println("Water is opened");
//        else if (n == CLOSED)
//            System.out.println("Water is closed");
//        else
//            System.out.println("State is invalid");
//    }
//}
//
//interface Stateable {
//
//    int OPEN = 1;
//    int CLOSED = 0;
//
//    void printState(int n);
//}


//    public static void main(String[] args) {
//
//        Operationable operation;
//        operation = (x, y) -> x + y;
//
//        int result = operation.calculate(10, 20);
//        System.out.println(result); //30
//    }
//}
//
//interface Operationable {
//    int calculate(int x, int y);
//}

//    public static void main(String[] args) {
//        int[] arr = {50, 60, 70, 80, 90, 100, 110, 120};
//        int count = 0;
//        for (int x : arr) {
//            if (x >= 90) continue;
//            x += 10;
//            count++;
//            if (count > 3) break;
//            System.out.print(x);
//        }
//    }


//    public static void main(String[] args) {
//
//
//        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//
//        names.sort(new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });
//
//        System.out.println(names);
//    }
//}


//        IntStream.of(50, 60, 70, 80, 90, 100, 110, 120).filter(x -> x < 90).map(x -> x + 10)
//                .limit(3).forEach(System.out::print);
//
////        List<String> list = Arrays.stream(args)
////                .filter(s -> s.length() <= 2)
////                .collect(Collectors.toList());
////
////
////        IntStream.of(120, 410, 85, 32, 314, 12)
////                .filter(x -> x < 300)
////                .map(x -> x + 11)
////                .limit(3)
////                .forEach(System.out::print);
////
////        IntStream.of(50, 60, 70, 80, 90, 100)
////                .filter(x -> x < 300)
////                .map(x -> x + 11)
////                .limit(3)
////                .forEach(System.out::print);
//
//
//
//    }
//}


//    public static void main(String[] args) {
//
//        UserBuilder userBuilder = User::new;
//        User user = userBuilder.create("Tom");
//        System.out.println(user.getName());
//    }
//}
//
//interface UserBuilder {
//    User create(String name);
//}
//
//class User {
//
//    private String name;
//
//    String getName() {
//        return name;
//    }
//
//    User(String n) {
//        this.name = n;
//    }
//}


//    public static void main(String[] args) {
//
//
//        String[] array1 = {"мама", "мыла", "раму"};
//        String[] array2 = {"я", "очень", "люблю", "java"};
//        String[] array3 = {"мир", "труд", "май"};
//
//        List<String[]> arrays = new ArrayList<>();
//        arrays.add(array1);
//        arrays.add(array2);
//        arrays.add(array3);
//
//        arrays.sort(new Comparator<String[]>() {
//            @Override
//            public int compare(String[] o1, String[] o2) {
//                return o2.length - o1.length;
//            }
//        });
//
//
//        TestSort<String> testSort = ((o1, o2) -> o2.length - o1.length);
//
//        arrays.sort((o1, o2) -> o2.length - o1.length);
//
//
//
//        System.out.println(Arrays.toString(arrays.get(0)));
//        System.out.println(Arrays.toString(arrays.get(1)));
//        System.out.println(Arrays.toString(arrays.get(2)));
//
//
//    }
//}
//
//interface TestSort<T> {
//    T asdf(Comparator<? super T> c);


//    public static void main(String[] args) {
//
//        Ost ost= (x, y) -> x % y;
//
//        int s = ost.ost(4,2);
//
//        int[] nums = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
//        System.out.println(sum(nums, ExpressionHelper::isEven));
//
//        Expression expr = ExpressionHelper::isPositive;
//        System.out.println(sum(nums, expr));
//    }
//
//    private static int sum(int[] numbers, Expression func) {
//        int result = 0;
//        for (int i : numbers) {
//            if (func.isEqual(i))
//                result += i;
//        }
//        return result;
//    }
//}
//
//// функциональный интерфейс
//interface  Ost {
//    int ost(int x, int y);
//}
//
//// функциональный интерфейс
//interface Expression {
//    boolean isEqual(int n);
//}
//
//// класс, в котором определены методы
//class ExpressionHelper {
//    static boolean isEven(int n) {
//        return n % 2 == 0;
//    }
//
//    static boolean isPositive(int n) {
//        return n > 0;
//    }
//}


//    public static void main(String[] args) {
//        // создаем кота и выводим на экран чтоб убедиться, что он "пустой"
//        Cat myCat = new Cat();
//        System.out.println(myCat);
//
//        // создаем лямбду
//        Settable<Cat> s = (obj, name, age) -> {
//            obj.setName(name);
//            obj.setAge(age);
//        };
//
//        // вызываем метод, в который передаем кота и лямбду
//        changeEntity(myCat, s);
//        // выводим на экран и видим, что состояние кота изменилось (имеет имя и возраст)
//        System.out.println(myCat);
//    }
//
//    private static <T extends WithNameAndAge> void changeEntity(T entity, Settable<T> s) {
//        s.set(entity, "Мурзик", 3);
//    }
//}
//
//interface WithNameAndAge {
//    void setName(String name);
//
//    void setAge(int age);
//}
//
//interface Settable<C extends WithNameAndAge> {
//    void set(C entity, String name, int age);
//}
//
//class Cat implements WithNameAndAge {
//    private String name;
//    private int age;
//
//    @Override
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    @Override
//    public String toString() {
//        return "Cat{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//}

//
//    public static void main(String[] args) {
//        Func func = (a, b, c) -> a * pow(b,c);
//
//        System.out.println(func.check(4, 2, 2));
//
//    }
//
//
//    public
//    interface Func {
//        double check(double a, double b, double c);
//    }
//}


//    public static void main(String[] args) {
//
//        Expression func = (n) -> n % 2 == 0;
//        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//        System.out.println(sum(nums, func)); // 20
//    }
//
//    private static int sum(int[] numbers, Expression func) {
//        int result = 0;
//        for (int i : numbers) {
//            if (func.isEqual(i))
//                result += i;
//        }
//        return result;
//    }
//}
//
//interface Expression {
//    boolean isEqual(int n);
//}


//    public static void main(String[] args) {
//
//        int[] nums = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
//        ExpressionHelper exprHelper = new ExpressionHelper();
//        System.out.println(sum(nums, exprHelper::isEven)); // 0
//    }
//
//    private static int sum(int[] numbers, Expression func) {
//        int result = 0;
//        for (int i : numbers) {
//            if (func.isEqual(i))
//                result += i;
//        }
//        return result;
//    }
//}
//
//interface Expression {
//    boolean isEqual(int n);
//}
//
//class ExpressionHelper {
//
//    boolean isEven(int n) {
//
//        return n % 2 == 0;
//    }
//}


//    public static void main(String[] args) {
//
//        Printable printable = createPrintable("Foreign Affairs", false);
//        printable.print();
//
//        read(new Book("Java for impatients", "Cay Horstmann"));
//        read(new Journal("Java Dayly News"));
//    }
//
//    static void read(Printable p) {
//
//        p.print();
//    }
//
//    static Printable createPrintable(String name, boolean option) {
//
//        if (option)
//            return new Book(name, "Undefined");
//        else
//            return new Journal(name);
//    }
//}
//
//interface Printable {
//
//    void print();
//}
//
//class Book implements Printable {
//
//    String name;
//    String author;
//
//    Book(String name, String author) {
//
//        this.name = name;
//        this.author = author;
//    }
//
//    public void print() {
//
//        System.out.printf("%s (%s) \n", name, author);
//    }
//}
//
//class Journal implements Printable {
//
//    private String name;
//
//    String getName() {
//        return name;
//    }
//
//    Journal(String name) {
//
//        this.name = name;
//    }
//
//    public void print() {
//        System.out.println(name);
//    }
//}

//
//    public static void main(String[] args) {
//
//        Consumer<Integer> printer = x -> System.out.printf("%d долларов \n", x);
//        printer.accept(600); // 600 долларов
//    }
//}


//    public static void main(String[] args) {
//        Runnable task = () -> System.out.println("Hello, World!");
//        Thread thread = new Thread(task);
//        thread.start();
//    }
//}


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < arr.size(); i++) {
            x += arr.get(i).get(i);
        }
        for (int i = 0; i < arr.size(); i++) {
            y += arr.get(arr.size() - i - 1).get(i);
        }
        return Math.abs(x - y);
    }

}

public class Test {
    public static void main(String[] args) throws IOException {

        int n = 3;

        List<List<Integer>> arr = new ArrayList<>();

        List<Integer> inArr = Arrays.asList(11, 2, 4);

        arr.add(inArr);

        inArr = Arrays.asList(4, 5, 6);
        arr.add(inArr);
        inArr = Arrays.asList(10, 8, -12);
        arr.add(inArr);


        int result = Result.diagonalDifference(arr);

        System.out.println(result);

    }
}
