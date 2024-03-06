package com.lin.lojbackendserviceclient.service;


import com.lin.lojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 判题服务
 * @author L
 */
@FeignClient(name = "loj-backend-judge-service",path = "/api/judge/inner")
public interface JudgeFeignClient {
    @PostMapping("/do")
    QuestionSubmit doJudge(@RequestBody QuestionSubmit questionSubmit);
}
