package com.log.logger.logger.repository;

import com.log.logger.logger.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataEntity, String> {
}
