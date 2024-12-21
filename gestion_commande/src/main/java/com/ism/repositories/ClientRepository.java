package com.ism.repositories;

import com.ism.config.DatabaseConfig;
import com.ism.entities.Client;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientRepository {

    public Client save(Client client) {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        entityManager.getTransaction().begin();
        if (client.getId() == null) {
            entityManager.persist(client);
        } else {
            client = entityManager.merge(client);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return client;
    }

    public Client findById(Long id) {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        Client client = entityManager.find(Client.class, id);
        entityManager.close();
        return client;
    }

    public List<Client> findAll() {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        List<Client> clients = entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        entityManager.close();
        return clients;
    }

    public void delete(Client client) {
        EntityManager entityManager = DatabaseConfig.getEntityManager();
        entityManager.getTransaction().begin();
        if (!entityManager.contains(client)) {
            client = entityManager.merge(client);
        }
        entityManager.remove(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
