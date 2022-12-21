package org.example;

import com.slack.api.methods.SlackApiException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SlackApiException {

        SlackMessage slackMessage=new SlackMessage();
        //사용자토큰
        slackMessage.setToken("xoxp-1376359396183-4020602894023-4542296825858-180debeeac5b8c6d5ab6d157ed67c1e3");
        //채널ID
        slackMessage.setChannel("C04FHJY8D63");
        //메시지내용
        slackMessage.setText(":wave: hi");

        //텍스트 전송
        SlackUtils.SendText(slackMessage);
        //메시지 탬플릿(버튼) 전송
        SlackUtils.SendKit(slackMessage);



    }
}