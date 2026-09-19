/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.voiceclone.controller;

import cn.zhuatech.voiceclone.service.VoiceCloneService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/voiceclone")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*"})
public class VoiceCloneController {
    private final VoiceCloneService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public VoiceCloneController(VoiceCloneService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/analyze") public VoiceCloneService.Result analyze(@Valid @RequestBody VoiceCloneService.Request request) { return service.analyze(request); }
}

