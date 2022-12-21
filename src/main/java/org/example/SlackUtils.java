package org.example;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;
import com.slack.api.model.block.LayoutBlock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.*;

public class SlackUtils {
    private static Slack slack=Slack.getInstance();

    //텍스트 전송
    public static void SendText(SlackMessage slackMessage) throws SlackApiException, IOException {

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

    //메시지 탬플릿(버튼) 전송
    public static void SendKit(SlackMessage slackMessage) throws IOException, SlackApiException {

        List<LayoutBlock> layoutBlocks = new ArrayList<>();
        layoutBlocks.add(section(section -> section.text(markdownText("메시지 탬플릿"))));
        layoutBlocks.add(divider());
        layoutBlocks.add(
                actions(actions -> actions
                        .elements(asElements(
                                button(b -> b.text(plainText(pt -> pt.emoji(true).text("승인")))
                                        .value("v1")
                                        .style("primary")
                                        .actionId("action_approve")
                                ),
                                button(b -> b.text(plainText(pt -> pt.emoji(true).text("거부")))
                                        .value("v2")
                                        .style("danger")
                                        .actionId("action_reject")
                                )

                        ))
                )
        );
        ChatPostMessageResponse response = slack.methods(slackMessage.getToken()).chatPostMessage(req -> req
                .channel(slackMessage.getChannel())
                        .blocks(layoutBlocks));
        if (response.isOk()) {
            Message postedMessage = response.getMessage();
            System.out.println("success: "+postedMessage);
        } else {
            String errorCode = response.getError();
            System.out.println("error: "+errorCode);
        }
    }

}
