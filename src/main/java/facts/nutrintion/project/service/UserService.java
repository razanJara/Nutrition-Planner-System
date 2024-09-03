package facts.nutrintion.project.service;

import facts.nutrintion.project.entity.User;
import facts.nutrintion.project.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements UserDetailsService {    final UserRepository userRepository;

    public UserService(UserRepository memberRepository) {
        this.userRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> member = userRepository.findById(Integer.parseInt(username));
        if (member.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = member.get();
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                user.getAuthorities().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getAuthority()))
                        .collect(Collectors.toList())
        );
    }
}
