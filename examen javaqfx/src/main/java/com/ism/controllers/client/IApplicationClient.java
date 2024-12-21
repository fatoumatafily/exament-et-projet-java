package com.ism.controllers.client;

import com.ism.data.entities;
import com.ism.data.entities.User;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;

public{
    void saisirDette(IArticleService articleService, IClientService clientService,IlistachatService listachatService, IlistachatView listachatView, IDemandeArticleService demandeArticleService, User user);
    void displaylistachat(IlistachatService listachatService, IlistachatView listachatView);
    void subMenulistachat(IlistachatService listachatService, IlistachatView listachatView);
    void relaunchDette(IlistachatService listachatService, IlistachatView listachatView);
}
