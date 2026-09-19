/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.voiceclone;

import cn.zhuatech.voiceclone.service.VoiceCloneService;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class VoiceCloneServiceTests {
    private final VoiceCloneService service = new VoiceCloneService();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void approvesAuthorizedCleanSample() {
        var result = service.analyze(new VoiceCloneService.Request(true, 42, -38, "zh-CN", "欢迎使用知华科技企业语音服务。"));
        assertThat(result.status()).isEqualTo("READY");
        assertThat(result.readinessScore()).isEqualTo(100);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnauthorizedSample() {
        var result = service.analyze(new VoiceCloneService.Request(false, 42, -38, "zh-CN", "测试"));
        assertThat(result.status()).isEqualTo("BLOCKED");
    }
}

