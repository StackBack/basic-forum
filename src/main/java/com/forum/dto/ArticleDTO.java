package com.forum.dto;

import com.forum.domain.Color;
import lombok.Data;

import javax.persistence.*;

@Data
public class ArticleDTO {
    private String text;
    private Color color;
}
