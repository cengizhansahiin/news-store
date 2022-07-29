package tr.com.id3.news.newsstore.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tr.com.id3.news.newsstore.model.Article;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.DataInput;
import java.io.IOException;

@Service
public class ReadQueue {
    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = "News")
    public void readQueue(final Message jsonMessage) throws JMSException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        TextMessage msg = (TextMessage) jsonMessage;
        Article article = mapper.readValue(msg.getText(), Article.class);
        System.out.println(article);
    }
}
