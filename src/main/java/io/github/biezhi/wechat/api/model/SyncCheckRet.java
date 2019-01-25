package io.github.biezhi.wechat.api.model;

import io.github.biezhi.wechat.api.enums.RetCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 心跳检查返回
 *
 * @author biezhi
 * @date 2018/1/20
 */
@Data
@AllArgsConstructor
public class SyncCheckRet {

    private RetCode retCode;
    /**
     * 0 正常
     * 2 新的消息
     * 7 进入/离开聊天界面
     */
    private int     selector;

}
