package com.sdc.study.security.service.app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App, String> {
    Optional<App> findByAppId(String appId);
}
