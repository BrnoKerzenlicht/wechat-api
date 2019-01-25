package io.github.biezhi.wechat;

import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.AccountType;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.QRCodeUtils;
import java.io.File;
import lombok.extern.slf4j.Slf4j;

/**
 * 我的小机器人
 * @author biezhi
 * @date 2018/1/19
 */
@Slf4j
public class MyBot extends WeChatBot {

    public MyBot(Config config) {
        super(config);
    }

    /**
     * 绑定群聊信息
     * @param message
     */
    @Bind(msgType = MsgType.ALL, accountType = AccountType.TYPE_GROUP)
    public void handleGroupMsg(WeChatMessage message) {
        log.info("接收到群 [{}] 的消息: {}", message.getName(), message.getText());
    }

    /**
     * 绑定所有消息
     * @param message
     */
    @Bind(msgType = MsgType.ALL, accountType = { AccountType.TYPE_FRIEND, AccountType.TYPE_GROUP, AccountType.TYPE_MP,
            AccountType.TYPE_SPECIAL })
    public void handleMsg(WeChatMessage message) {
        switch (message.getMsgType()) {
            case TEXT:
                log.info("接收到 [{}] 的文本消息: {}", message.getName(), message.getText());
                break;
            case IMAGE:
                log.info("接收到 [{}] 的图片消息: {}", message.getName(), message.getImagePath());
                log.info("图片是否为二维码判断结果:{}", QRCodeUtils.isQRCode(new File(message.getImagePath())));
                break;
            default:
                log.info("接收到 [{}] 的 {} 消息: {}", message.getName(), message.getMsgType(), message.getText());
                break;
        }
    }

    public static void main(String[] args) {
        new MyBot(Config.me().autoLogin(true).showTerminal(true)).start();
    }

}
