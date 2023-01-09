package org.example;

import com.slack.api.RequestConfigurator;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.users.UsersLookupByEmailRequest;
import com.slack.api.methods.request.users.profile.UsersProfileGetRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.methods.response.reactions.ReactionsListResponse;
import com.slack.api.methods.response.users.UsersLookupByEmailResponse;
import com.slack.api.model.Message;
import com.slack.api.model.block.ImageBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.util.http.SlackHttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.*;

public class SlackUtils {
    private static Slack slack=Slack.getInstance();

    public static UsersLookupByEmailResponse getUser(String email) throws Exception
    {

        UsersLookupByEmailRequest.UsersLookupByEmailRequestBuilder req = UsersLookupByEmailRequest.builder()
                .token("xoxp-1376359396183-4020602894023-4612223807363-b66a1fdbf37dd9184c67657c931bac3d").email(email);

        CompletableFuture<UsersLookupByEmailResponse> future = slack.methodsAsync().usersLookupByEmail(req.build());

        UsersLookupByEmailResponse response = future.get();

        if (response.isOk()) {
            System.out.println("success: "+response.getUser());
            return response;
        } else {
            String errorCode = response.getError();
            System.out.println("error: "+errorCode);
            return null;
        }
    }

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

        ChatPostMessageResponse response1 = slack.methods(slackMessage.getToken()).chatPostMessage(req -> req
                .channel(slackMessage.getChannel())
                .blocks(layoutBlocks));

        if (response1.isOk()) {
            Message postedMessage = response1.getMessage();
            System.out.println("success: "+postedMessage);
        } else {
            String errorCode = response1.getError();
            System.out.println("error: "+errorCode);
        }
    }

}
