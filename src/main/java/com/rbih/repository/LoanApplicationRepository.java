package com.rbih.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbih.domain.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {

}
