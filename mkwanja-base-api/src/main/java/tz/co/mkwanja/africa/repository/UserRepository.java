package tz.co.mkwanja.africa.repository;

import tz.co.mkwanja.africa.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @Author Benjamini Buganzi
 * @Date 13/12/2021.
 */

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByPhone(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByPhone(String username);

    Boolean existsByEmail(String email);
}
