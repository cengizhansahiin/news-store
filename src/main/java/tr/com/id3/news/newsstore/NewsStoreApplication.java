package tr.com.id3.news.newsstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class NewsStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsStoreApplication.class, args);
	}

}
