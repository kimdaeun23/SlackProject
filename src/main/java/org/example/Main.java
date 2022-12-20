package org.example;

import com.slack.api.methods.SlackApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws SlackApiException, IOException {
        SlackMessage slackMessage=new SlackMessage();
        slackMessage.setToken("xoxb-1376359396183-4544660391396-RPrF5aijviIE01GHoe1e6veM");
        slackMessage.setChannel("C04ESJJTZ5E");
        slackMessage.setText("Hi");

        SlackUtils.sendSlack(slackMessage);
    }
}