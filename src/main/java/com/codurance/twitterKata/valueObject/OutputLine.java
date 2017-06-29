package com.codurance.twitterKata.valueObject;

public class OutputLine {
    private String line;

    public OutputLine(String line) {
        this.line = line;
    }

    public String output() {
        return line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutputLine that = (OutputLine) o;

        return line.equals(that.line);
    }

    @Override
    public int hashCode() {
        return line.hashCode();
    }
}
