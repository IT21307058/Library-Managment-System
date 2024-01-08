package com.libraryMS.repositories;

import com.libraryMS.entities.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCardRepo extends JpaRepository<MemberCard, Integer> {
}
