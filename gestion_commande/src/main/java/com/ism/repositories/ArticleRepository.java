package com.ism.repositories;

import com.ism.entities.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Article save(Article article) {
        if (article.getId() == null) {
            entityManager.persist(article);
            return article;
        } else {
            return entityManager.merge(article);
        }
    }

    public Article findById(Long id) {
        return entityManager.find(Article.class, id);
    }

    public List<Article> findAll() {
        return entityManager.createQuery("SELECT a FROM Article a", Article.class).getResultList();
    }

    public void delete(Article article) {
        if (entityManager.contains(article)) {
            entityManager.remove(article);
        } else {
            Article attached = entityManager.merge(article);
            entityManager.remove(attached);
        }
    }
}
