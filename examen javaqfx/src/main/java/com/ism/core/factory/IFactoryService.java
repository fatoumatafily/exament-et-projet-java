package com.ism.core.factory;

import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDemandeArticleService;
import com.ism.services.IlistachatService;

public interface IFactoryService {
    IArticleService getInstanceArticleService();
    IClientService getInstanceClientService();
    IDemandeArticleService getInstanceDemandeArticleService();
    IlistachatService getInstancelistachatService();
   
}
