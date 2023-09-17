package vdmcreation.p_260;

public class Example {
    //Question:  Can we start a thread two times in Java?
    //Answer:    No, after starting a thread, it can never be started again.
    // If you does so, an IllegalThreadStateException is thrown.
    // In such case, thread will run once but for second time, it will throw exception.
    public static void main(String[] args) {
        Thread t = new Thread();
        t.start();
        t.start();
        System.out.println("Hello World!");
    }
}
