package facts.nutrintion.project.service;

import facts.nutrintion.project.entity.Authority;
import facts.nutrintion.project.entity.User;
import facts.nutrintion.project.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(UserService.class);

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public void registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        logger.info("Encoded password: {}", encodedPassword);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Authority authority = new Authority();
        authority.setAuthority("USER");
        authority.setUsername(user);
        Set<Authority> authorities = Set.of(authority);
        user.setAuthorities(authorities);
        userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
