package com.florinani.ing.repository;

import com.florinani.ing.domain.SavingAccount;
import com.florinani.ing.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingAccountReporsitory  extends CrudRepository<SavingAccount, Long> {

    List<SavingAccount> findByUser(User user);
}
