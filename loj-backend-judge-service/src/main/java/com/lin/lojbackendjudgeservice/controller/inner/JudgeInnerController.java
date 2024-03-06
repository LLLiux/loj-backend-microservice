package com.lin.lojbackendjudgeservice.controller.inner;

import com.lin.lojbackendjudgeservice.judge.JudgeService;
import com.lin.lojbackendmodel.model.entity.QuestionSubmit;
import com.lin.lojbackendserviceclient.service.JudgeFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/inner")
@Slf4j
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;

    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestBody QuestionSubmit questionSubmit) {
        return judgeService.doJudge(questionSubmit);
    }

}
