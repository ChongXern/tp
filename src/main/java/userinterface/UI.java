package userinterface;

import java.util.Scanner;

public class UI {
    private final Scanner sc = new Scanner(System.in);

    public UI() {
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String readInput() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return null;

    }

    public void closeScanner() {
        sc.close();
    }
}
