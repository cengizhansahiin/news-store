package tr.com.id3.news.newsstore.mapper;


import org.springframework.stereotype.Component;
import tr.com.id3.news.newsstore.model.Article;
import tr.com.id3.news.common.model.ArticleDTO;

import java.sql.Date;

@Component
public class ArticleMapper {
    public Article dtoToArticle(ArticleDTO articleDTO){
        Article article = new Article();
        article.setAuthor(articleDTO.getAuthor());
        article.setTitle(articleDTO.getTitle());
        article.setDescription(articleDTO.getDescription());
        article.setUrl(articleDTO.getUrl());
        article.setUrlToImage(articleDTO.getUrlToImage());
        article.setPublishedAt(articleDTO.getPublishedAt());
        article.setContent(articleDTO.getContent());
        Date date = new Date(System.currentTimeMillis());
        article.setCreate_date(date);
        article.setUpdate_date(date);
        return article;
    }
}
