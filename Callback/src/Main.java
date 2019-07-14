public class Main {

    public static void main(String[] args) {

        SomeClass someClass = new SomeClass();
        MyClass myClass = new MyClass();

        someClass.registerCallBack(myClass);
        someClass.doSomething();
    }
}