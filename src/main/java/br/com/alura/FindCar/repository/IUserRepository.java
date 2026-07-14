package br.com.alura.FindCar.repository;


import br.com.alura.FindCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    ///Busca de Dados pelo JPA utilizando o Email como Atributo de comparação para Busca
    Optional<UserDetails> findByEmail(String email);
}
