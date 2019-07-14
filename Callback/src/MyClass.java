public class MyClass implements Callback {
    @Override
    public void callingBack(String s) {

        if (s != null) {
            System.out.println("Ваш ответ: " + s);
        } else {
            System.out.println("Вы не ответили на вопрос!");
        }
    }
}