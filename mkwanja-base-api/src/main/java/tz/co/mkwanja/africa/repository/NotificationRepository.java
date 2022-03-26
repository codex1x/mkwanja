package tz.co.mkwanja.africa.repository;

import tz.co.mkwanja.africa.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author Benjamini Buganzi
 * @Date 28/01/2022.
 */
public interface NotificationRepository extends MongoRepository<Notification, String> {

}