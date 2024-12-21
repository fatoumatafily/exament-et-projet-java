package com.ism.core.factory;

import com.ism.data.repository.IArticleRepository;
import com.ism.data.repository.IClientRepository;

import com.ism.data.repository.IlistachatRepository;
import com.ism.data.repository.IDetailRepository;
IDetteRepository;
import com.ism.data.repository.IPaiementRepository;
import com.ism.data.repository.IUserRepository;

public interface IFactoryRepository {
    IArticleRepository getInstanceArticleRepository();
    IClientRepository getInstanceClientRepository();
    IDemandeArticleRepository getInstanceDemandeArticleRepository();
    IlistachatRepository getInstancelistachatRepository();
   
}
