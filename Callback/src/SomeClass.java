import javax.swing.*;

public class SomeClass {

    String replyTo;

//    interface Callback{
//        void callingBack(String s);
//    }

    private Callback callback;

    public void registerCallBack(Callback callback) {
        this.callback = callback;
    }

    void doSomething() {
        int reply = JOptionPane.showConfirmDialog(null, "Вы программист?", "Опрос", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.NO_OPTION) {
            replyTo = "Нет";
        }
        if (reply == JOptionPane.YES_OPTION) {
            replyTo = "Да";
        }

        callback.callingBack(replyTo);
    }
}


//    Проще говоря, механизм обратного вызова относится к вызову функции с другой функцией в качестве аргумента. В таких языках, как C, C++ это делается путем передачи указателей на функции в качестве аргументов, но java не имеет понятия указателей. Обходной путь - это интерфейсы. Мы передаем ссылку на интерфейсы вместо указателей. Ваше понимание будет кристально чистым после понимания кода ниже. Чтобы также показать реальные приложения, представьте себе покупку мыши и коврика для мыши. Цена коврика для мыши фиксирована, но цена мыши зависит от бренда.

//interface mouse
//{
//    double mousePrice();
//}
//class BrandA implements mouse
//{
//    public double mousePrice()          //note that public access modifier is needed as all methods of interface are public are by default and when you override them
//    //you cannot use any access modifier more restrictive
//    {
//        return 100;
//    }
//
//}
//
//class BrandB implements mouse
//{
//    public double mousePrice()
//    {
//        return 200;
//    }
//
//}
//
//class Simple
//{
//    static void total(mouse t)
//    {
//        double mousepad = 20;
//        double mousep = t.mousePrice();
//        System.out.println(mousepad + mousep);
//    }
//    public static void main(String args[])
//    {
//        mouse ob = new BrandA();        //upcasting.
//        total(ob);
//    }
//}