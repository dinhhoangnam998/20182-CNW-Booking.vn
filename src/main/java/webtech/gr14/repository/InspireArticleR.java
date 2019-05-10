package webtech.gr14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.article.InspireArticle;

public interface InspireArticleR extends JpaRepository<InspireArticle, Integer> {

}
