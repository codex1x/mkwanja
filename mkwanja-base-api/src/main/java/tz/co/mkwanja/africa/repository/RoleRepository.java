package tz.co.mkwanja.africa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tz.co.mkwanja.africa.models.Role;
import tz.co.mkwanja.africa.models.enums.ERole;

import java.util.Optional;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);

    Boolean existsByName(ERole name);
}