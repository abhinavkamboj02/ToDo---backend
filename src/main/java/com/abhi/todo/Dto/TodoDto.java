package com.abhi.todo.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoDto {
    private Integer id;
    private String title;
    private String content;
    private String status;
    private Date addedDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date toDoDate;
}
