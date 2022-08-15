package uz.isystem.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.isystem.userservice.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}
