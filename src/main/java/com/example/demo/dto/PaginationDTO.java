package com.example.demo.dto;

import com.example.demo.mapper.QuestionMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTO;
    private Integer page;
    private Integer totalPage;
    private boolean showNext;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showEnd;
    List<Integer> pages;

    @Autowired
    QuestionMapper questionMapper;
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if(totalCount.equals(0))
            this.setDefaultPagination();
        else {
            pages = new ArrayList<>();
            totalPage = (totalCount % size == 0) ? totalCount / size : totalCount / size + 1;

            if(page < 1)   this.page = 1;
            else if(page > totalPage)   this.page = totalPage;
            else    this.page = page;

            for(int i = 1; i <= 3; i++){
                if(this.page - i >= 1)
                    this.pages.add(this.page - i);
                if(this.page + i <= totalPage)
                    this.pages.add(this.page + i);
            }
            this.pages.add(this.page);
            Collections.sort(this.pages);

            this.showNext = !(this.page == totalPage);
            this.showPrevious = !(this.page == 1);
            this.showFirst = !this.pages.contains(1);
            this.showEnd = !pages.contains(this.totalPage);
        }
    }

    private void setDefaultPagination() {
        this.pages = new ArrayList<>();
        this.page = 0;
        this.totalPage = 0;
        this.showNext = false;
        this.showPrevious = false;
        this.showFirst = false;
        this.showEnd = false;
    }
}
