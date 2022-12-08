package org.example;

public class Main {
    public static void main(String[] args) {

        SlackMessage slackMessage=new SlackMessage();
        slackMessage.setUsername("username");
        slackMessage.setText("testing");
        slackMessage.setIcon_emoji(":wave:");

        SlackUtils.SlackSend(slackMessage);

    }
}