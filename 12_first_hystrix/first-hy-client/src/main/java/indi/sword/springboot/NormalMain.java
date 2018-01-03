package indi.sword.springboot;

public class NormalMain {
    public static void main(String[] args) {
        HelloCommand command = new HelloCommand();
        String result = command.execute();
        System.out.println(result);
    }
}
