package com.demo.labmanagement.labmanagement;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("lab")
public class LabDto {
    public LabDto() {
    }

    public LabDto(String name, String description, Long categoryId, Long authorId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    @Id
    private Long id;

    private String name;

    private String description;

    private Long categoryId;

    private Long authorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}