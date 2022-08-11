package com.sdc.study.security.service.appmapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppMappingRepository extends JpaRepository<AppMapping, String> {
    List<AppMapping> findAllByAppId(String appId);
    List<AppMapping> findAllByRole(String role);
}
