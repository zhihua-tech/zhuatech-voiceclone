/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.voiceclone.controller;

import cn.zhuatech.voiceclone.service.VoiceCloneService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voiceclone")
@CrossOrigin(originPatterns = {"http://localhost:*", "http://127.0.0.1:*"})
public class VoiceCloneController {
    private final VoiceCloneService service;
    public VoiceCloneController(VoiceCloneService service) { this.service = service; }
    @PostMapping("/analyze") public VoiceCloneService.Result analyze(@Valid @RequestBody VoiceCloneService.Request request) { return service.analyze(request); }
}

