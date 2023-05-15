package com.shop.shopHive.service.serviceImpl;

import com.shop.shopHive.models.AppUser;
import com.shop.shopHive.repository.AppUserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import static java.util.Collections.emptyList;


@Service
@Slf4j
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> result = appUserRepository.findByEmail(email);

        if (result.isEmpty() || !result.get().getIsVerified()) {
            throw new UsernameNotFoundException("User does not exist or is not verified");
        }
        AppUser appUser = result.get();

        return new User(appUser.getEmail(), appUser.getPassword(), emptyList());

    }
}
