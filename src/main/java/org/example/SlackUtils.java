package org.example;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import java.io.IOException;

public class SlackUtils {
    private final static String webhookUrl = "https://hooks.slack.com/services/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";

    public static WebhookResponse send(String text) {
        try {
            WebhookResponse response = null;
            Slack slack = Slack.getInstance();
            Payload payload = Payload.builder().text(text).build();
            response = slack.send(webhookUrl, payload);
            return response;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
