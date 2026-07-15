package br.com.alura.FindCar.repository;


import br.com.alura.FindCar.model.Alert;
import br.com.alura.FindCar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUser(User user);
}
