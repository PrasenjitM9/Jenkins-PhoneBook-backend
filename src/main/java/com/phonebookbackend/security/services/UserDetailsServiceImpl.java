package com.phonebookbackend.security.services;

import com.phonebookbackend.model.User;
import com.phonebookbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //private final UserRepository userRepository;
    @Autowired
    UserRepository userRepository;

//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    /**
     * @Transactional The persistence context is just a synchronizer object that tracks the state of a
     * limited set of Java objects and makes sure that changes on those objects are eventually persisted back into the database.
     * */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "+ username));

        return UserDetailsImpl.build(user);
    }
}
