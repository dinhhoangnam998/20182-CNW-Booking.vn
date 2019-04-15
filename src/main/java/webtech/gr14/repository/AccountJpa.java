package webtech.gr14.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import webtech.gr14.model.Account;

public interface AccountJpa  extends JpaRepository<Account, Integer>{

	Account findByUsername(String username);

}
