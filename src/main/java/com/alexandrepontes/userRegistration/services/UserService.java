package com.alexandrepontes.userRegistration.services;

import com.alexandrepontes.userRegistration.models.User;
import com.alexandrepontes.userRegistration.repository.UserRepository;
import com.alexandrepontes.userRegistration.services.exceptions.DataIntegrityException;
import com.alexandrepontes.userRegistration.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User find (Integer id) {
        Optional<User> objUser = userRepository.findById(id);
        return objUser.orElseThrow(() -> new ObjectNotFoundException(
                "objeto não encontrado! Id: "+id+", Tipo: " + User.class.getName()
        ));
    }

    public User update (User user) {
        User newUser = find(user.getId());
        updateData(newUser, user);
        return userRepository.save(newUser);
    }

    @Transactional
    public User insert(User user) {
        user.setId(null);
        user = userRepository.save(user);

        return user;
    }

    public void delete(Integer id){
        try {
            userRepository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não foi possível excluir este usuário!");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void updateData(User newUser, User user){
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setCPF(user.getCPF());
        newUser.setBirthDay(user.getBirthDay());
    }

}
