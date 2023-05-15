package com.shop.shopHive.event;

import com.shop.shopHive.models.AppUser;
import com.shop.shopHive.models.Customer;
import com.shop.shopHive.repository.AppUserRepository;
import com.shop.shopHive.security.authfacade.AuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler
@Slf4j
public class RestEvent {
    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    AppUserRepository appUserRepository;

    @HandleAfterCreate
    public void handleAfterCreate(Customer customer) {

        String loggedInUserEmail = authenticationFacade.getAuthentication().getName();

        Optional<AppUser> appUser = appUserRepository.findByEmail(loggedInUserEmail);

        System.out.println("here is the user that was fetched --------> : " + appUser.toString());
    }
}
