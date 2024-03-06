package com.lin.lojbackendserviceclient.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lin.lojbackendmodel.model.dto.question.QuestionQueryRequest;
import com.lin.lojbackendmodel.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.lin.lojbackendmodel.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.lin.lojbackendmodel.model.entity.Question;
import com.lin.lojbackendmodel.model.entity.QuestionSubmit;
import com.lin.lojbackendmodel.model.entity.User;
import com.lin.lojbackendmodel.model.vo.QuestionSubmitVO;
import com.lin.lojbackendmodel.model.vo.QuestionVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
* @author L
*/
@FeignClient(name = "loj-backend-question-service",path = "/api/question/inner")
public interface QuestionFeignClient {

    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") long questionId);

    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") long questionSubmitId);

    @PostMapping("/question_submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);
}
