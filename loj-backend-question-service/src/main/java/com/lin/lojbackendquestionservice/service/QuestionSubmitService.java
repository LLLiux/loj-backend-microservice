package com.lin.lojbackendquestionservice.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.lojbackendmodel.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.lin.lojbackendmodel.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.lin.lojbackendmodel.model.entity.QuestionSubmit;
import com.lin.lojbackendmodel.model.entity.User;
import com.lin.lojbackendmodel.model.vo.QuestionSubmitVO;

/**
* @author L
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-11-29 18:04:19
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
