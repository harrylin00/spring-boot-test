package com.example.demo.service;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO getQuestion(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        Integer offset = (paginationDTO.getPage() - 1) * size;
        List<Question> questionList = questionMapper.getQuestion(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question ques : questionList) {
            User user = userMapper.findById(ques.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(ques, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTO(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO getQuestionByUserId(User user, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(user.getId());
        paginationDTO.setPagination(totalCount, page, size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        Integer offset = (paginationDTO.getPage() - 1) * size;
        if(offset >= 0) {
            List<Question> questionList = questionMapper.getQuestionByUser(user.getId(), offset, size);
            for(Question ques : questionList) {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(ques, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
            paginationDTO.setQuestionDTO(questionDTOList);
        }
        return paginationDTO;
    }

    public QuestionDTO getQuestionByQuestionId(Integer questionId) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.getQuestionByQuestionId(questionId);
        if(question == null)
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.create(question);
        } else {
            question.setGmtModify(System.currentTimeMillis());
            boolean flag = questionMapper.update(question);
            if(!flag)
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
    }

    public void increaseView(Integer questionId) {
        questionMapper.increaseViewByQuestionId(questionId);
    }
}
