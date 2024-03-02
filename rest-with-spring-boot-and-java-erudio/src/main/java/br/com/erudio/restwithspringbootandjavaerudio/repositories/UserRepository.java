package br.com.erudio.restwithspringbootandjavaerudio.repositories;

import br.com.erudio.restwithspringbootandjavaerudio.model.PersonModel;
import br.com.erudio.restwithspringbootandjavaerudio.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE u.username =:username")
    UserModel findByUsername(@Param("username") String username);
}
