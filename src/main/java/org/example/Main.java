package org.example;

import com.slack.api.methods.SlackApiException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SlackApiException {

        SlackMessage slackMessage=new SlackMessage();
        //사용자토큰
        slackMessage.setToken("xoxb-1376359396183-4544660391396-38Ps6DUMyxny4GiGE4MqKGMO");
        //채널ID
        slackMessage.setChannel("C04FHJY8D63");
        //메시지내용
        slackMessage.setText("버튼을 클릭하세요.");
        //url링크
        slackMessage.setUrl("https://www.naver.com/");
        //버튼1
        slackMessage.setBtn1_name("버튼1");
        slackMessage.setBtn1_url("https://www.naver.com/");
        //버튼2
        slackMessage.setBtn2_name("버튼2");
        slackMessage.setBtn2_url("https://www.google.com/");


        //텍스트 전송
        //SlackUtils.SendText(slackMessage);

        //메시지 탬플릿 전송
        SlackUtils.SendKit(slackMessage);
    }
}