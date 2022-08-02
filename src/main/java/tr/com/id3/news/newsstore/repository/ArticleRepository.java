package tr.com.id3.news.newsstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tr.com.id3.news.newsstore.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
