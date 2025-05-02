package com.study.week6.practices.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CursorPageDTO<T> {

    private List<T> content;
    private Long nextCursor;

    public CursorPageDTO() {}

    public CursorPageDTO(List<T> content, Long nextCursor) {
        this.content = content;
        this.nextCursor = nextCursor;
    }

}
