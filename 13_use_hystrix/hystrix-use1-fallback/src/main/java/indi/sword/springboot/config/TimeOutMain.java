package indi.sword.springboot.config;

public class TimeOutMain {
    public static void main(String[] args) {
        TimeoutCommand c = new TimeoutCommand();
        String result = c.execute();
        System.out.println(result);
    }
}
