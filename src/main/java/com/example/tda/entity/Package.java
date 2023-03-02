package com.example.tda.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "package")
public class Package {
    @Column(name = "image")
    private String image;

    @Column(name = "premium")
    private String premium;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        createAt=new Date();
    }

    @PostUpdate
    public void postUpdate() {
        updateAt = new Date();
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updateAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}