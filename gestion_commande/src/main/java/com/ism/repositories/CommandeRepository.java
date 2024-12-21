package com.ism.repositories;

import com.ism.config.DatabaseConfig;
import com.ism.entities.Commande;

import javax.persistence.EntityManager;
import java.util.List;

public class CommandeRepository {

    public Commande save(Commande commande) {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        entityManager.getTransaction().begin();
        if (commande.getId() == null) {
            entityManager.persist(commande);
        } else {
            commande = entityManager.merge(commande);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return commande;
    }

    public Commande findById(Long id) {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        Commande commande = entityManager.find(Commande.class, id);
        entityManager.close();
        return commande;
    }

    public List<Commande> findAll() {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        List<Commande> commandes = entityManager.createQuery("SELECT c FROM Commande c", Commande.class).getResultList();
        entityManager.close();
        return commandes;
    }

    public void delete(Commande commande) {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        entityManager.getTransaction().begin();
        if (!entityManager.contains(commande)) {
            commande = entityManager.merge(commande);
        }
        entityManager.remove(commande);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Commande> findByClientId(Long clientId) {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        List<Commande> commandes = entityManager.createQuery(
                "SELECT c FROM Commande c WHERE c.client.id = :clientId", Commande.class)
                .setParameter("clientId", clientId)
                .getResultList();
        entityManager.close();
        return commandes;
    }
}
