package com.riwi.RIWIvirtual.repository;

import com.riwi.RIWIvirtual.entity.RiwiClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassRepository extends JpaRepository<RiwiClass, Long> {

}
