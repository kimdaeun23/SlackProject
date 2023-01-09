package org.example;

import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.users.UsersLookupByEmailResponse;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {

        SlackMessage slackMessage=new SlackMessage();
        //사용자토큰(필수)
        slackMessage.setToken("xoxp-1376359396183-4020602894023-4612223807363-b66a1fdbf37dd9184c67657c931bac3d");
        //채널ID(필수)
        //slackMessage.setChannel("C04FHJY8D63");
        //메시지내용(필수)
        slackMessage.setText("엥뭐야된거야?");
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

        UsersLookupByEmailResponse response=SlackUtils.getUser("es-kbsys37@hybecorp.com");
        String a=response.getUser().getId();
        slackMessage.setChannel(a);
        //메시지 탬플릿 전송
        SlackUtils.SendKit(slackMessage);

    }
}