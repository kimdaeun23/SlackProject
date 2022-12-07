package org.example;

public class Main {
    public static void main(String[] args) {
//        SlackMessage slackMessage = SlackMessage.builder()
//                .channel("the-channel-name")
//                .username("user1")
//                .text("just testing")
//                .icon_emoji(":twice:")
//                .build();
        SlackUtils.send("just testing");
    }
}