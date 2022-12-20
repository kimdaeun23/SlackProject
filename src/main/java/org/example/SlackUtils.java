package org.example;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;

import java.io.IOException;

public class SlackUtils {

    public static void sendSlack(SlackMessage slackMessage) throws SlackApiException, IOException {
        Slack slack = Slack.getInstance();

        ChatPostMessageResponse response = slack.methods(slackMessage.getToken()).chatPostMessage(req -> req
                .channel(slackMessage.getChannel())
                .text(String.valueOf(slackMessage.getText())));
        if (response.isOk()) {
            Message postedMessage = response.getMessage();
            System.out.println("success: "+postedMessage);
        } else {
            String errorCode = response.getError();
            System.out.println("error: "+errorCode);
        }
    }
}
