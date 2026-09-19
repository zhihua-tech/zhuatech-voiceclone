/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.voiceclone.service;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class VoiceCloneService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result analyze(Request request) {
        int score = 100;
        List<Check> checks = new ArrayList<>();
        if (!request.authorized()) { score = 0; checks.add(new Check("授权确认", "BLOCKED", "未确认声音权利人与使用授权")); }
        else checks.add(new Check("授权确认", "PASSED", "已记录授权确认，生产环境应保存授权凭证"));
        if (request.sampleSeconds() < 20) { score -= 28; checks.add(new Check("样本时长", "REVIEW", "建议提供20秒以上连续人声")); }
        else checks.add(new Check("样本时长", "PASSED", "样本时长满足演示阈值"));
        if (request.noiseDb() > -28) { score -= 24; checks.add(new Check("背景噪声", "REVIEW", "噪声偏高，建议重新录制或先降噪")); }
        else checks.add(new Check("背景噪声", "PASSED", "环境噪声处于可用范围"));
        if (request.targetText().length() > 500) { score -= 12; checks.add(new Check("合成文本", "REVIEW", "建议拆分为多个自然语义段落")); }
        else checks.add(new Check("合成文本", "PASSED", "文本长度适合单次预览"));
        score = Math.max(0, Math.min(100, score));
        String status = !request.authorized() ? "BLOCKED" : score >= 80 ? "READY" : score >= 55 ? "PREPROCESS" : "RECORD_AGAIN";
        return new Result(status, score, List.copyOf(checks),
            List.of("静音裁剪与响度归一化", "人声活动检测和噪声评估", "创建授权音色档案", "生成带水印试听任务"),
            Map.of("language", request.language(), "sampleSeconds", request.sampleSeconds(), "text", request.targetText(), "watermark", true),
            "LOCAL_QUALITY_GATE");
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@AssertTrue(message = "必须确认已获得声音权利人授权") boolean authorized,
                          @Min(3) @Max(600) int sampleSeconds,
                          @DecimalMin("-90") @DecimalMax("0") double noiseDb,
                          @NotBlank String language,
                          @NotBlank @Size(max = 2000) String targetText) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Check(String name, String status, String message) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String status, int readinessScore, List<Check> checks, List<String> pipeline,
                         Map<String, Object> providerPayload, String executionMode) {}
}

