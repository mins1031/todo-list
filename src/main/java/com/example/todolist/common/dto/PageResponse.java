package com.example.todolist.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PageResponse {
    private int totalPageSize;
    private int currentPage;

    public static PageResponse of(int totalPages, int currentPage) {
        return new PageResponse(totalPages, currentPage);
    }
}
