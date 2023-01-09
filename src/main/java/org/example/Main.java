package org.example;

import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.users.UsersLookupByEmailResponse;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {

        SlackMessage slackMessage=new SlackMessage();
        //사용자토큰(필수)
        slackMessage.setToken("xoxp-1376359396183-4020602894023-4637135032304-e3eca9f14b8449774f7437fbf7a12778");
        //채널ID(필수)
        slackMessage.setChannel("es-kbsys37@hybecorp.com");
        //메시지내용(필수)
        slackMessage.setText("버튼을 클릭하세요.");
        //url링크
        slackMessage.setUrl("https://www.naver.com/");
        //버튼1
        slackMessage.setBtn1_name("버튼1");
        slackMessage.setBtn1_url("https://www.naver.com/");
        //버튼2
        slackMessage.setBtn2_name("버튼2");
        slackMessage.setBtn2_url("https://www.google.com/");
        //이미지
        slackMessage.setImage_url("https://pbs.twimg.com/profile_images/625633822235693056/lNGUneLX_400x400.jpg");
        //이미지Alt
        slackMessage.setImage_alt("test");
        //메시지 탬플릿 전송
        SlackUtils.SendKit(slackMessage);

    }
}