package com.integrador.cms.upload;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
}