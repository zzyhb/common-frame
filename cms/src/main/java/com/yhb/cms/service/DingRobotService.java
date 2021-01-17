package com.yhb.cms.service;

import java.util.List;

/**
 * @author fusu
 * @since 2020/10/16 1:32 下午
 */
public interface DingRobotService {

    /**
     * 发送钉钉机器人文本消息
     *
     * @param content
     * @param atMobiles
     */
    void sendTextMessage(String content, List<String> atMobiles);

    /**
     * 发送钉钉机器人Markdown消息
     *
     * @param title
     * @param content
     * @param isAtAll
     * @param atMobiles
     *
     * @return
     */
    void sendMarkdownMessage(String title, String content, boolean isAtAll, List<String> atMobiles);

}
