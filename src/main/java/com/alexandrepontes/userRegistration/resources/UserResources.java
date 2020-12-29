package com.alexandrepontes.userRegistration.resources;

import com.alexandrepontes.userRegistration.models.User;
import com.alexandrepontes.userRegistration.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserResources {
    @Autowired
    private UserRepository userRepository;

    public User find (Integer id) {
        Optional<User> objUser = userRepository.findById(id);
//        return objUser.orElseThrow(() -> new ObjectNotFoundException(
//                "Objeto não encontrado! ID: " +id + ", Tipo: " + User.class.getName()
//        ));
        return null;
    }

    public User update (User user) {
        return null;
    }

    @Transactional
    public User insert(User user) {
        return null;
    }

    public void delete(Integer id){

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
