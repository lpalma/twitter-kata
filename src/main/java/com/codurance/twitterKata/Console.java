package com.codurance.twitterKata;

import java.util.Scanner;

public class Console {

    private Scanner console = new Scanner(System.in);

    public String readLine() {
        String nextLine = console.nextLine();

        if (nextLine.equals("exit")) {
            printLine("Bye!");

            System.exit(0);
        }

        return nextLine;
    }

    public void printLine(String line) {

        System.out.println(line);
    }
}
