//package com.oprisorraul.demo.repository;
//
//import com.oprisorraul.demo.model.Course;
//import com.oprisorraul.demo.model.Customer;
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.data.repository.query.Param;
//
//public interface CustomerRepository extends JpaRepository<Customer, Integer> {
//    @Modifying
//    @Query("UPDATE Course c SET c.professor.id = :professorId WHERE c.id = :courseId")
//    void updateProfessorIdByCourse(@Param("courseId") Integer courseId, @Param("professorId") Integer professorId);
//}
