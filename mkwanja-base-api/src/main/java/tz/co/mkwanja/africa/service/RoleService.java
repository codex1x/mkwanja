package tz.co.mkwanja.africa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tz.co.mkwanja.africa.models.Role;
import tz.co.mkwanja.africa.payload.response.MessageResponse;
import tz.co.mkwanja.africa.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;


    @Transactional
    public ResponseEntity<Object> findAll() {
        List<Role> list = roleRepository.findAll();

        if (list.isEmpty()) {
            return ResponseEntity.status(404).body(new MessageResponse(404, "No roles configured", "Hakuna jukumu lililotunzwa",""));
        }
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<Object> save(Role role) {
        role = roleRepository.save(role);
        if (role.getId() != null) {
            return ResponseEntity.ok(new MessageResponse(200, "Role saved successfully", "Jukumu limeongezwa",role));
        }
        return ResponseEntity.badRequest().body(new MessageResponse(199, "Failed to save role", "Imeshindikana kuongeza Jukumu", ""));
    }

    public ResponseEntity<Object> deleteById(String id) {
     Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.deleteById(id);
            return ResponseEntity.ok(new MessageResponse(200, "Role deleted successfully", "Jukumu limefutwa",""));
        }
        return ResponseEntity.badRequest().body(new MessageResponse(199, "Failed to delete role", "Imeshindikana kufuta Jukumu", ""));
    }

}
