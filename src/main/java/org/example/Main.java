package org.example;

public class Main {
    public static void main(String[] args) {

        SlackMessage slackMessage=new SlackMessage();
        slackMessage.setUsername("김다은");
        slackMessage.setText("hello");
        slackMessage.setIcon_emoji(":wave:");

        SlackUtils.SlackSend(slackMessage);
    }
}