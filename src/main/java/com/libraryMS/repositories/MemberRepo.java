package com.libraryMS.repositories;

import com.libraryMS.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Integer> {
}
