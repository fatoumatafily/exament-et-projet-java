package com.ism.core.factory;

import com.ism.controllers.IArticleView;
import com.ism.controllers.IClientView;
import com.ism.controllers.IlistachatView;
import com.ism.controllers.IDetteView;
import com.ism.controllers.IPaiementView;
import com.ism.controllers.IUserView;

public interface IFactoryView {
    IArticleView getInstanceArticleView();
    IClientView getInstanceClientView();
    IlistachatView getInstancelistachatView();
}
