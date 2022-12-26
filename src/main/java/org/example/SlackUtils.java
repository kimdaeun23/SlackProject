package org.example;

import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.Message;
import com.slack.api.model.ModelConfigurator;
import com.slack.api.model.block.ContextBlockElement;
import com.slack.api.model.block.ImageBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.BlockElements;
import com.slack.api.model.block.element.ImageElement;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.Blocks.section;
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

    //메시지 탬플릿 전송
    public static void SendKit(SlackMessage slackMessage) throws IOException, SlackApiException {
        String message;
        List<LayoutBlock> layoutBlocks = new ArrayList<>();

        //텍스트
        if (!slackMessage.getUrl().equals("")){
            message=slackMessage.getText()+"\n*<"+slackMessage.getUrl()+">*";
            layoutBlocks.add(section(section -> section.text(markdownText(message))));
        } else {
            layoutBlocks.add(section(section -> section.text(markdownText(slackMessage.getText()))));
        }

        //버튼1
        if(!slackMessage.getBtn1_name().equals("")&&slackMessage.getBtn2_name().equals("")) {
            layoutBlocks.add(divider());
            layoutBlocks.add(
                    actions(actions -> actions
                            .elements(asElements(
                                    button(b -> b.text(plainText(pt -> pt.emoji(true).text(slackMessage.getBtn1_name())))
                                            .value("v1")
                                            .style("primary")
                                            .url(slackMessage.getBtn1_url())
                                    )

                            ))
                    )
            );
        }

        //버튼1,2
        if(!slackMessage.getBtn1_name().equals("")&&!slackMessage.getBtn2_name().equals("")) {
            layoutBlocks.add(divider());
            layoutBlocks.add(
                    actions(actions -> actions
                            .elements(asElements(
                                    button(b -> b.text(plainText(pt -> pt.emoji(true).text(slackMessage.getBtn1_name())))
                                            .value("v1")
                                            .style("primary")
                                            .url(slackMessage.getBtn1_url())
                                    ),
                                    button(b -> b.text(plainText(pt -> pt.emoji(true).text(slackMessage.getBtn2_name())))
                                            .value("v2")
                                            .style("danger")
                                            .url(slackMessage.getBtn2_url())
                                    )

                            ))
                    )
            );
        }

        if (!slackMessage.getImage_url().equals("")){
            //이미지
            layoutBlocks.add(ImageBlock.builder().imageUrl(slackMessage.getImage_url()).altText(slackMessage.getImage_alt()).build());
        }

        //Slack전송
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
