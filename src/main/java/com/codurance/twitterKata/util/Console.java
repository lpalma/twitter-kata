package com.codurance.twitterKata.util;

import com.codurance.twitterKata.valueObject.InputLine;
import com.codurance.twitterKata.valueObject.OutputLine;

import java.util.Scanner;

public class Console {

    private Scanner console = new Scanner(System.in);

    public InputLine readLine() {
        String nextLine = console.nextLine();
        InputLine inputLine = new InputLine(nextLine);

        if (inputLine.input().equals("exit")) {
            printLine(new OutputLine("Bye!"));

            System.exit(0);
        }

        return inputLine;
    }

    public void printLine(OutputLine outputLine) {

        System.out.println(outputLine.output());
    }
}
