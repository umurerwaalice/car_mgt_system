package com.car.service;

import com.car.model.Client;
import com.car.model.UserPrincipal;
import com.car.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;

import java.security.Principal;


public class MyUserDetailService
//        implements UserDetailsService
{
//    @Autowired
//    ClientRepository clientRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Client client=clientRepository.findByUsername(username);
//       if(client==null){
//           throw new UsernameNotFoundException("User not found");
//       }
//        return new UserPrincipal(client);
//    }
}
