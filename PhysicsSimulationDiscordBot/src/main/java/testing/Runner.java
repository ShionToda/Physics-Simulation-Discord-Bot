package testing;

import java.io.IOException;

public class Runner {

    public static void main (String[] args) {

        System.out.println("Running....");

        AnimatedGIFObject tester = new AnimatedGIFObject();

        try {
            tester.convert();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Process Completed");
    }
}
