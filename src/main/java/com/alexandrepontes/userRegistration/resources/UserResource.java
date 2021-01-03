package com.alexandrepontes.userRegistration.resources;

import com.alexandrepontes.userRegistration.models.User;
import com.alexandrepontes.userRegistration.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
@Api(value="API REST Users")
@CrossOrigin(origins="*")
public class UserResource {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/users/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Retorna um usuário em particular por meio do seu ID.")
    public ResponseEntity<User> find(@PathVariable Long id) {
        User user = userService.find(id);
        return ResponseEntity.ok().body(user);
    }

    @RequestMapping(method=RequestMethod.POST, value = "/users")
    @ApiOperation(value = "Insere um usuário na base de dados.")
    public ResponseEntity<Void> insert(@Valid @RequestBody User u){
        User user = new User();
        user = userService.insert(u);
        URI uri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(user.getId())
                        .toUri();

            return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    @ApiOperation(value = "Atualiza dados de um usuário por meio do seu ID.")
    public ResponseEntity<Void> update(@Valid @RequestBody User u, @PathVariable Long id){
                 u.setId(userService.find(id).getId());
                 userService.update(u);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/users/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Exclui dados de um usuário por meio do seu ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ApiOperation(value = "Retorna todos os usuários cadastrados na base de dados.")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        List<User> totalUsers = users.stream()
                                        .map(obj -> new User(obj.getId(), obj.getName(), obj.getCPF(), obj.getEmail(), obj.getBirthDay()))
                                        .collect(Collectors.toList());
        return ResponseEntity.ok().body(totalUsers);
    }
}
