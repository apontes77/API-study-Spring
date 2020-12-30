package com.alexandrepontes.userRegistration.resources;

import com.alexandrepontes.userRegistration.models.User;
import com.alexandrepontes.userRegistration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> find(@PathVariable Integer id) {
        User user = userService.find(id);
        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody User u){
        User user = userService.find(u.getId());
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(user.getId())
                        .toUri();

            return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody User u, @PathVariable Integer id){
        User user = userService.find(u.getId());
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        List<User> totalUsers = users.stream()
                                        .map(obj -> new User())
                                        .collect(Collectors.toList());
        return ResponseEntity.ok().body(totalUsers);
    }
}
