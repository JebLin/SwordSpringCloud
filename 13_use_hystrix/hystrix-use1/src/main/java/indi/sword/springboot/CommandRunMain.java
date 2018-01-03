package indi.sword.springboot;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;


public class CommandRunMain {
    public static void main(String[] args) throws InterruptedException {
        RunCommand c1 = new RunCommand("observe method");
        c1.observe();

        RunCommand c2 = new RunCommand("toObservable method");
        Observable observable = c2.toObservable();
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("3.command completed");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String t) {
                System.out.println("2.on Next " + t);
            }
        });
        Thread.sleep(1000);

    }


    static class RunCommand extends HystrixCommand<String> {

        String msg;

        public RunCommand(String msg) {
            super(HystrixCommandGroupKey.Factory.asKey("TestGroup"));
            this.msg = msg;
        }

        @Override
        protected String run() throws Exception {
            System.out.println("1. run ,msg = " + msg);
            return "success";
        }
    }
}
