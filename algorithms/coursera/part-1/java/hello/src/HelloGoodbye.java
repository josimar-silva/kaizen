import edu.princeton.cs.algs4.StdOut;

/**
 * Write a program HelloGoodbye.java that takes two names as command-line arguments and prints hello and goodbye messages:
 * With the names for the hello message in the same order as the command-line arguments
 * and with the names for the goodbye message in reverse order.
 */
public class HelloGoodbye {
    public static void main(String[] args) {
        if(args.length != 2) {
            StdOut.println("Please provide exactly two names as command-line arguments.");
            return;
        }

        StdOut.println("Hello " + args[0] + " and " + args[1]);

        StdOut.println("Goodbye " + args[1] + " and " + args[0]);
    }
}