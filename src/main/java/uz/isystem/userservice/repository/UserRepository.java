package uz.isystem.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.isystem.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
