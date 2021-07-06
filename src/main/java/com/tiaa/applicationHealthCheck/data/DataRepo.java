package com.tiaa.applicationHealthCheck.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepo extends JpaRepository<Data, Long> {
    public List<Data> findByApplicationAppId(Long id);

}
