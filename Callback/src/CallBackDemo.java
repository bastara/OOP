import java.util.ArrayList;
import java.util.Random;

/**
 * @author mutagen
 */
public class CallBackDemo {

    public static void main(String[] args) {

        Caller caller = new Caller();

        for (int i = 0; i < 10; i++) {
            new Worker(caller).start();
        }
//        итого у нас 9 воркеров отработали и вернули ответ по калбеку в коллекцию
        System.out.println(caller.getStatuses());
    }

    static class Caller implements Callback {

        private ArrayList<Integer> statuses = new ArrayList<>();

        public ArrayList<Integer> getStatuses() {
            return statuses;
        }

        @Override
        public void callMeBack(int status) {
//            тут может быть логика куда вернутся данные, может быть коллекция, я выбрал попроще
            synchronized (statuses) {
                statuses.add(status);
            }
        }
    }

    static interface Callback {
//        подготовим интерфейс по которому нам будут возвращать данные

        void callMeBack(int status);
    }

    static class Worker extends Thread {

        private Callback cb;

        public Worker(Callback cb) {
            this.cb = cb;
        }

        int pleaseDoMeAFavor() {
            return new Random().nextInt();
        }

        @Override
        public void run() {
//            выполним работу 
            int st = pleaseDoMeAFavor();
//            и вернём данные вызывающему по калбек интерфейсу
            cb.callMeBack(st);
        }
    }
}