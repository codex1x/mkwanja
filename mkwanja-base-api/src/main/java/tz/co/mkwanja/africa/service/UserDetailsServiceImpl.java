package tz.co.mkwanja.africa.service;

import tz.co.mkwanja.africa.models.User;
import tz.co.mkwanja.africa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public String generateOTP() {
        String passphrase = "";
        for (int i = 1; i <= 5; i++) {
            passphrase = passphrase + new Random().nextInt(10);
        }
        return passphrase;
    }

}
