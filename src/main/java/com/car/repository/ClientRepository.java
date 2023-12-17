package com.car.repository;

import com.car.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByUsername(String username);
    Client findByUsernameAndPassword(String username, String password);



    @Query("select clie from Client clie where clie.username= :username and clie.password= :password")
    List<Client> RegisteredClient(
            @Param("username") String username,
            @Param("password") String password
    );
}
