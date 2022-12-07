package org.example;

public class Main {
    public static void main(String[] args) {
        SlackMessage slackMessage = new SlackMessage("김다은","hello",":wave:");

        SlackUtils.send(slackMessage);
    }
}