class ThreadNum extends Thread {
    int num;
    public ThreadNum(int n){
        num = n;
    }
    public void run(){
        for (int i = num; i <= 10; i+=2) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}


public class LearnThread {
    public static void main(String[] args) {
        ThreadNum t1 = new ThreadNum(1);
        ThreadNum t2 = new ThreadNum(2);
        t1.start();
        t2.start();
    }
}
