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