package io.vinay.OAuth2.Client.repository;

import io.vinay.OAuth2.Client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

    public Client findByAppName(String appName);

    public boolean existsByAppName(String appName);
}
