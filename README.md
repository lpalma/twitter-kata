# Solution for the Twitter Kata

## Build

Building this application requires [gradle](https://gradle.org/).

Once gradle is installed, clone this repository and run the following command within the repository directory:

`$ gradle shadowJar`

## Run

To run the application, simply run the following command:

`$ java -jar build/libs/twitter-kata.jar`

## Commands

**Post:** `<user> -> <message>`

**Read:** `<user>`

**Follow:** `<user> follows <user>`

**Wall:** `<user> wall`

**Exit:** `exit`

## Example

```
alice -> hello world!

bob -> good morning everyone.

alice follows bob

alice wall

// output
bob - good morning everyone (just now)
alice - hello world! (2 minutes ago)
```
