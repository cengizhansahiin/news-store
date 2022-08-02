package tr.com.id3.news.newsstore.model;


import lombok.Data;

import javax.persistence.Entity;

@Data
public class ArticleDTO {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}
