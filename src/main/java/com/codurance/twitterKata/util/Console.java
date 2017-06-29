package com.codurance.twitterKata.util;

import com.codurance.twitterKata.valueObject.InputLine;

import java.util.Scanner;

public class Console {

    private Scanner console = new Scanner(System.in);

    public InputLine readLine() {
        String nextLine = console.nextLine();
        InputLine inputLine = new InputLine(nextLine);

        if (inputLine.input().equals("exit")) {
            printLine("Bye!");

            System.exit(0);
        }

        return inputLine;
    }

    public void printLine(String line) {

        System.out.println(line);
    }
}
