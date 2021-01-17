package com.yhb.cms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.request.OapiRobotSendRequest.At;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.yhb.cms.service.DingRobotService;
import com.yhb.cms.util.TimeUtil;
import com.yhb.common.other.NullUtil;

/**
 * @author fusu
 * @since 2020/10/16 1:48 下午
 */
@Service
public class DingRobotServiceImpl implements DingRobotService {

    private final static String DING_ROBOT_URL = "https://oapi.dingtalk.com/robot/send?access_token=c2f3b8809ea9686000a4f243b1ec57548d1e8fef10952b5fb1069b571e0fc73b";

    @Override
    public void sendTextMessage(String content, List<String> atMobiles) {
        DingTalkClient client = new DefaultDingTalkClient(DING_ROBOT_URL);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        request.setText(text);
        At at = new At();
        at.setAtMobiles(atMobiles);
        request.setAt(at);
        try {
            OapiRobotSendResponse response = client.execute(request);
            if (NullUtil.isNull(request) || !response.isSuccess()) {
                throw new RuntimeException(response.getErrmsg());
            }
        } catch (Exception e) {
            throw new RuntimeException(TimeUtil.getCurrentDateTime() + "发起的sendTextMessage失败");
        }
    }

    @Override
    public void sendMarkdownMessage(String title, String content, boolean isAtAll, List<String> atMobiles) {
        DingTalkClient client = new DefaultDingTalkClient(DING_ROBOT_URL);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        At at = new At();
        at.setIsAtAll(isAtAll);
        at.setAtMobiles(atMobiles);
        request.setAt(at);
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(content);
        request.setMarkdown(markdown);
        try {
            OapiRobotSendResponse response = client.execute(request);
            if (NullUtil.isNull(response) || !response.isSuccess()) {
                throw new RuntimeException(response.getErrmsg());
            }
        } catch (Exception e) {
            throw new RuntimeException(TimeUtil.getCurrentDateTime() + "发起的sendMarkdownMessage失败");
        }
    }
}
