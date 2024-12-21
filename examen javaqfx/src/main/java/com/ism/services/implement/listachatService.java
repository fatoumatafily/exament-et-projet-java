package com.ism.services.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ism.data.entities.Client;
import com.ism.data.entities.listachat;
import com.ism.data.repository.IlistachatRepository;
import com.ism.services.IlistachatService;

public class listachatService implements IlistachatService {
    private IlistachatRepository listachatRepository;

    public listachatService(IlistachatRepository listachatRepository) {
        this.listachatRepository = listachatRepository;
    }

    @Override
    public listachat add(listachat value) {
        return listachatRepository.insert(value);
    }

    @Override
    public List<listachat> findAll() {
        return listachatRepository.selectAll();
    }

    @Override
    public listachat findBy(listachat listachat) {
        for (listachat dette : findAll()) {
            if (Objects.equals(dette.getId(), listachat.getId())) {
                return dette;
            }
        }
        return null;
    }

    @Override
    public List<listachat> findAllByState(String state) {
        if (state == "ALL") {
            return findAll();
        }
        return listachatRepository.selectAll()
                .stream()
                .filter(d -> d.getEtat().name().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    @Override
    public List<listachat> findAlllistachatsForClient(Client client) {
        return listachatRepository.selectAll()
                .stream()
                .filter(d -> d.getClient() != null && d.getClient().getUser().getId() == client.getUser().getId())
                .collect(Collectors.toList());
    }

    @Override
    public listachat findBy(List<listachat> listachats, listachat listachat) {
        for (listachat dette : listachats) {
            if (Objects.equals(dette.getId(), listachat.getId())) {
                return dette;
            }
        }
        return null;
    }

    @Override
    public int length() {
        return listachatRepository.size();
    }

    @Override
    public void update(List<listachat> listachats, listachat updateDemande) {
        updateDemande.setUpdatedAt(LocalDateTime.now());
        listachatRepository.update(updateDemande);
    }
}
