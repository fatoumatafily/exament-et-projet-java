package com.ism.data.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.ism.core.helper.PasswordUtils;
import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.User;
import com.ism.data.enums.Role;
import com.ism.data.repository.IUserRepository;
import java.util.Collections;

public class UserRepository extends Repository<User> implements IUserRepository {
    public UserRepository() {
        super(User.class);
    }

    @Override
public List<User> selectAllActifs(int type) {
    List<User> allUsers = selectAll();  // Récupérer la liste d'utilisateurs
    
    if (allUsers == null) {
        return Collections.emptyList();  // Retourner une liste vide si la liste est null
    }

    return allUsers.stream()
            .filter(user -> user != null && user.isStatus() && user.getRole() != null && user.getRole().ordinal() == type)  // Vérification de nullité
            .collect(Collectors.toList());
}


    @Override
    public List<User> selectAllClients() {
        return selectAll().stream()
                .filter(user -> user.getRole().ordinal() == Role.CLIENT.ordinal())
                .collect(Collectors.toList());
    }

    @Override
public List<User> selectAllUsers(User userConnect) {
    // Filtrer les utilisateurs nulls et ceux qui n'ont pas d'ID avant de continuer
    return selectAll().stream()
            .filter(user -> user != null && user.getId() != null)  // Vérification que l'utilisateur et son ID ne sont pas nulls
            .filter(user -> user.getId() != userConnect.getId() && 
                           (user.getRole() == Role.ADMIN || user.getRole() == Role.BOUTIQUIER))
            .collect(Collectors.toList());
}

    @Override
    public User selectByLogin(String login, String password) {
        return selectAll().stream()
                .filter(user -> user.getLogin().compareTo(login) == 0
                        && PasswordUtils.checkPassword(password, user.getPassword()))
                .findFirst()
                .orElse(null);
    }
}
