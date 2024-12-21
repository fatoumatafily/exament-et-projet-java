package com.ism.controllers.store.implement;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.Scanner;

import com.ism.data.entities.listachat;
import com.ism.data.entities.Detail;
import com.ism.data.entities.Dette;
import com.ism.data.entities.Paiement;
import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.DemandeArticle;
import com.ism.data.entities.User;
import com.ism.data.enums.Etatlistachat;
import com.ism.data.enums.EtatDette;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import com.ism.controllers.IClientView;
import com.ism.controllers.IlistachatView;
import com.ism.controllers.IDetteView;
import com.ism.controllers.IPaiementView;
import com.ism.controllers.IUserView;
import com.ism.controllers.implement.Application;
import com.ism.controllers.store.IApplicationStorekeeper;
import com.ism.core.config.router.Router;
import com.ism.core.factory.IFactory;
import com.ism.core.factory.implement.Factory;
import com.ism.core.helper.Success;
import com.ism.core.helper.Tools;

public class ApplicationStorekeeper extends Application implements IApplicationStorekeeper, Initializable {
    private IFactory factory = Factory.getInstance();
    private IArticleService articleService;
    private IClientService clientService;
    private IClientView clientView;
    private IlistachatService listachatService;
    private IlistachatView listachatView;
   
    // >> Chargement de données du dashboard
    
    public Label numlistachat;
 
    // <<

    private final Scanner scanner = new Scanner(System.in);

    public ApplicationStorekeeper() {
        this.articleService = factory.getFactoryService().getInstanceArticleService();
        this.clientService = factory.getFactoryService().getInstanceClientService();
        this.clientView = factory.getFactoryView().getInstanceClientView();
        this.listachatService = factory.getFactoryService().getInstancelistachatService();
        this.listachatView = factory.getFactoryView().getInstancelistachatView();
        

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.articleService = factory.getFactoryService().getInstanceArticleService();
        this.clientService = factory.getFactoryService().getInstanceClientService();
        this.clientView = factory.getFactoryView().getInstanceClientView();
        this.listachatService = factory.getFactoryService().getInstancelistachatService();
        this.listachatView = factory.getFactoryView().getInstancelistachatView();
        this.detailService = factory.getFactoryService().getInstanceDetailService();
        if (infoConnect != null) {
            infoConnect.setText(Router.userParams);
        }
        loadDash();
    }

    public void loadDash() {
        this.numCustomer.setText("Clients ("+ clientService.getAllActifsWithAccount().size() +")");
        this.numDette.setText("Dettes ("+ detteService.length() +")");
        this.numPaiement.setText("Paiements ("+ paiementService.length() +")");
        this.numlistachat.setText("Demandes Dettes ("+ listachatService.length() +")");
    }

    @FXML
    public void loadGestionClient(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Gestion Client", "/com/ism/views/gestion.store.client.fxml");
        // Mettre à jour les labels une fois les services initialisés
        loadDash(); 
    }

    oadDash(); 
    }

    @FXML
    public void loadlistachatEnCour(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Liste achat", "/com/ism/views/listachat.fxml");
        // Mettre à jour les labels une fois les services initialisés
        loadDash(); 
    }

    
   

    @Override
    public void run(User user) {
        Integer choix;
        msgWelcome(user);
        do {
            choix = menu();
            switch (choix) {
                case 1:
                    saisirClient(clientService, clientView, userService, userView);
                    break;
                case 2:
                    displayClient(clientService, clientView);
                    break;
                case 3:
                    searchClientByTel(clientService, clientView);
                    break;
                
            }
        } while (choix != 8);
    }

   

   

    @Override
    
   

    public void saisirachat(IArticleService articleService, IClientService clientService,
            IClientView clientView, IachatService achatService) {
        List<Article> articleAvailable = articleService.findAllAvailable();
        List<Client> clients = clientService.findAll();
        if (articleAvailable.isEmpty()) {
            System.out.println("Aucun article n'a été enregistré.");
            return;
        }
        if (clients.isEmpty()) {
            System.out.println("Aucun client n'a été enregistré.");
            return;
        }

        Client client = clientView.getObject(clientService.findAll());
        Dette achat = new achat();
        Dette testDette;
        dette.setClient(client);
        String choice;
        do {
            displayAvailableArticles(articleAvailable);
            choice = getUserChoice();
            if (!choice.equals("0")) {
                testDette = processArticleChoice(articleService, choice, articleAvailable, );
                if (testDette != null) {
                    dette = testDette;
                }
            }
        } while (!choice.equals("0"));
        System.out.print("Voulez-vous enregistrer un(des) paiement(s) (O/N): ");
        char choicePay = scanner.nextLine().charAt(0);
        if (choicePay == 'O' || choicePay == 'o') {
            Object[] result = getPaiementClient(paiementView,);
            Paiement paiement = (Paiement) result[0];
            dette = (Dette) result[1];
            dette.addPaiement(paiement);
            // Mise à jour du cumul après le paiement
            client.updateCumulMontantDu();
        }
        // Transaction
        

    
    private String getUserChoice() {
        System.out.print("Entrez l'id de l'article(0 pour terminer): ");
        return scanner.nextLine();
    }

    private Dette processArticleChoice(IArticleService articleService, String choice, List<Article> articleAvailable,
            Dette dette) {
        int quantity = getValidQuantity();
        if (quantity <= -1)
            return null;

        Article article = findArticle(choice, articleAvailable);
        if (article == null)
            return null;

        if (!checkStock(article, quantity))
            return null;

        updateArticleStock(articleService, article, quantity);
        return addDemandeArticle(article, quantity, dette);
    }

    private int getValidQuantity() {
        System.out.print("Entrez la quantité: ");
        String qte = scanner.nextLine();

        if (!qte.matches("\\d+")) {
            System.out.println("Erreur, la quantité est incorrecte.");
            return -1;
        }

        return Integer.parseInt(qte);
    }

    private Article findArticle(String id, List<Article> articleAvailable) {
        Article article = new Article();
        if (id.matches("\\d")) {
            article.setId(Long.parseLong(id));
        }
        Article foundArticle = articleService.findBy(article, articleAvailable);

        if (foundArticle == null) {
            System.out.println("Article non trouvé.");
        }

        return foundArticle;
    }

    private boolean checkStock(Article article, int quantity) {
        if (article.getQteStock() < quantity) {
            System.out.println("Quantité saisit supérieur à celui en stock.");
            return false;
        }
        return true;
    }

    private void updateArticleStock(IArticleService articleService, Article article, int quantity) {
        article.setQteStock(article.getQteStock() - quantity);
        articleService.update(article);
    }

    private Dette addDemandeArticle(Article article, int quantity, Dette dette) {
        // Ajouter une dette
        dette.setMontantTotal(dette.getMontantTotal() + (quantity * article.getPrix()));
        dette.setStatus(true);
        dette.setEtat(EtatDette.ENCOURS);
        // Ajouter un détail entre article et une dette
        Detail detail = new Detail();
        detail.setQte(quantity);
        detail.setPrixVente(article.getPrix());
        detail.setArticle(article);
        detail.setDette(dette);
        // Transaction
        dette.addDetail(detail);
        return dette;
    }

    public void saisirPaiement(IPaiementService paiementService, IPaiementView paiementView, IDetteService detteService,
            IDetteView detteView) {
        List<Dette> dettes = detteService.findAll();
        if (dettes.isEmpty()) {
            System.out.println("Aucune dette n'a été enregistré.");
            return;
        }
        Dette dette = detteView.getObject(dettes);
        Object[] result = getPaiementClient(paiementView, dette);
        Paiement paiement = (Paiement) result[0];
        // Update + Add paiement
        dette = (Dette) result[1];
        dette.addPaiement(paiement);
        // Mise à jour du cumul du client après le paiement
        Client client = dette.getClient();
        client.updateCumulMontantDu();
        // Update toutes les entités
        detteService.update(dette);
        paiementService.add(paiement);
        clientService.update(clientService.findAll(), client);
        msgSuccess("Paiement effectué avec succès!");
    }

   

    private void subMenulistachat(IlistachatService listachatService, IlistachatView listachatView) {
        String choice;
        System.out.println(MSG_FILTER);
        System.out.println("1- En cour la demande");
        System.out.println("2- Annuler la demande");
        System.out.print(MSG_CHOICE);
        choice = scanner.nextLine();
        if (choice.equals("1")) {
            List<listachat> listachats = listachatService.findAll().stream()
                    .filter(dette -> dette.getEtat().name().compareTo("ENCOURS") == 0).collect(Collectors.toList());
            if (isEmpty(listachatService.length(), "Aucune demande de dette (En cour) n'a été enregistrée.")) {
                return;
            }
            listachatView.afficher(listachats);

        } else if (choice.equals("2")) {
            List<listachat> listachats = listachatService.findAll().stream()
                    .filter(dette -> dette.getEtat().name().compareTo("ANNULE") == 0).collect(Collectors.toList());
            if (isEmpty(listachatService.length(), "Aucune demande de dette (Annulée) n'a été enregistrée.")) {
                return;
            }
            listachatView.afficher(listachats);
        } else {
            System.out.println("Erreur, choix invalide.");
        }
    }

    private void asklistachat(IArticleService articleService, IDetteService detteService, IlistachatService listachatService,
            listachat listachat, IDetailService detailService, IClientService clientService) {
        System.out.print("Voulez-vous Annuler ou Accepter la demande de dette(O/N): ");
        char ask = scanner.nextLine().charAt(0);
        if (ask == 'O' || ask == 'o') {
            // Créé une dette
            
            dette.setMontantTotal(listachat.getMontantTotal());
            dette.setStatus(true);
            dette.setEtat(EtatDette.ENCOURS);
            dette.setClient(listachat.getClient());
            dette.setlistachat(listachat);
            // Créé une détail
            for (DemandeArticle a : listachat.getDemandeArticles()) {
                // Update Article
                Article article = articleService.findBy(a.getArticle(), articleService.findAllAvailable());
                article.setQteStock(article.getQteStock() - a.getQteArticle());
                articleService.update(article);
                // Création détail
                Detail detail = new Detail();
                detail.setQte(a.getQteArticle());
                detail.setPrixVente(article.getPrix());
                detail.setArticle(article);
                detail.setDette(dette);
                // Ajout détail à la dette
                dette.addDetail(detail);
            }
            detteService.add(dette);
            listachat.setDette(dette);
            listachat.setEtat(Etatlistachat.VALIDE);
            listachatService.update(listachatService.findAll(), listachat);
            // transactionDetail(detailService, dette);
            // Update dette client
            Client client = listachat.getClient();
            client.addDetteClient(dette);
            clientService.update(clientService.findAll(), client);
            msgSuccess("Demande de dette accepter avec succès.");
        } else if (ask == 'n' || ask == 'N') {
            listachat.setEtat(Etatlistachat.ANNULE);
            listachatService.update(listachatService.findAll(), listachat);
            msgSuccess("Demande de dette refuser avec succès.");
        }
        else {
            System.out.println(MSG_ERROR);
        }
    }
}
