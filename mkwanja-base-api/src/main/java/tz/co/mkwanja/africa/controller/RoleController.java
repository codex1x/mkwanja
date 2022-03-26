package tz.co.mkwanja.africa.controller;

import tz.co.mkwanja.africa.models.Role;
import tz.co.mkwanja.africa.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tz.co.mkwanja.africa.repository.RoleRepository;
import tz.co.mkwanja.africa.service.RoleService;

import javax.validation.Valid;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT_ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Role role) {
        if (roleRepository.existsByName(role.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(101, "Error: Role with similar name Found", "Tayari kuna jukumu lenye jina sawa na hilo",""));
        }

        return roleService.save(role);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENT_ADMIN')")
    public ResponseEntity<?> delete(@Valid @RequestPart("id") String id) {
        return roleService.deleteById(id);
    }

    @RequestMapping("/list")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('CLIENT_ADMIN')")
    public ResponseEntity<?> list() {
        return roleService.findAll();
    }

}

