package uz.isystem.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.isystem.userservice.model.Application;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByUserId(Integer userId); // Select * from application where userId = :userId

    @Transactional
//    @Query("from Application where userId = :userId and startDate < :date and endDate > :date")
//    List<Application> findAllByUserIdAndStartDateBetweenEndDate(@Param("userId") Integer id, @Param("date") LocalDate date);
    @Query("from Application where userId = :userId and :date between startDate and endDate")
    List<Application> applicationByUserIdAndMonth(@Param("userId") Integer id, @Param("date") LocalDate date);
}
