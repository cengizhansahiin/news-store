package tr.com.id3.news.newsstore.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tr.com.id3.news.newsstore.model.Article;
import tr.com.id3.news.newsstore.model.ArticleDTO;
import tr.com.id3.news.newsstore.repository.ArticleRepository;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@Service
@EnableJpaRepositories("tr.com.id3.news.newsstore.repository")
public class ReadQueue {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private ArticleRepository repository;

    @JmsListener(destination = "News")
    public void readQueue(final Message jsonMessage) throws JMSException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        TextMessage msg = (TextMessage) jsonMessage;
        ArticleDTO articleDTO = mapper.readValue(msg.getText(), ArticleDTO.class);
        repository.save(dtoConvert(articleDTO));
    }
    public Article dtoConvert(ArticleDTO articleDTO){
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
