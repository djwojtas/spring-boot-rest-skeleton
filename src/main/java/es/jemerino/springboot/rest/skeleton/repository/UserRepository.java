package es.jemerino.springboot.rest.skeleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.jemerino.springboot.rest.skeleton.domain.DomainUser;

public interface UserRepository extends JpaRepository<DomainUser, String> {

}
