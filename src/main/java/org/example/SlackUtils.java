package org.example;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import java.io.IOException;

public class SlackUtils {
    private final static String webhookUrl = "https://hooks.slack.com/services/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";

    public static WebhookResponse SlackSend(SlackMessage slackMessage) {
        try {
            WebhookResponse webhookResponse = null;
            Slack slack = Slack.getInstance();

            String message=slackMessage.getUsername()+" : "+slackMessage.getText()+" "+slackMessage.getIcon_emoji();
            Payload payload = Payload.builder().text(message).build();

            webhookResponse = slack.send(webhookUrl, payload);
            return webhookResponse;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
