package tr.com.id3.news.newsstore.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tr.com.id3.news.newsstore.mapper.ArticleMapper;
import tr.com.id3.news.common.model.ArticleDTO;
import tr.com.id3.news.newsstore.repository.ArticleRepository;

import javax.jms.JMSException;
import java.io.IOException;

@Service
@Slf4j
@EnableJpaRepositories("tr.com.id3.news.newsstore.repository")
public class ListenerService {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ArticleMapper articleMapper;

    @JmsListener(destination = "News")
    public void readQueue(ArticleDTO articleDTO) throws JMSException, IOException {
        log.info("ActiveMQ News queue listener start.");
        try {
            repository.save(articleMapper.dtoToArticle(articleDTO));
            log.info(new ResponseEntity<>("Saved to database.",HttpStatus.OK).toString());
        }
        catch (Exception e){
            log.error(e.toString());
        }

        log.info("ActiveMQ News queue listener end.");
    }
}
