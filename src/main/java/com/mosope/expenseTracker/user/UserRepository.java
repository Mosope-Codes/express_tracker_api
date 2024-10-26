package com.mosope.expenseTracker.user;

import org.apache.catalina.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<Users, Long> {
    Users save(Users user);
    Users findById(Long id);
    void deleteById(Long id);
    void deleteByEmail(String email);
    Users findByEmail(String email);
}
