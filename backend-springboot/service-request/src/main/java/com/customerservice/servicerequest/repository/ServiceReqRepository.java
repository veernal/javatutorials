package com.customerservice.servicerequest.repository;

import java.util.List;
import java.util.Optional;

import com.customerservice.servicerequest.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceReqRepository extends JpaRepository<ServiceRequest, Integer>{

    @Query("select D from ServiceRequest D where D.customerId=:id")
    List<ServiceRequest> findReqByCustId(int id);

    @Query("select D from ServiceRequest D where D.customerId=:id and D.reqStatus=:status")
    List<ServiceRequest> findReqByStatus(int id, String status);

    @Query("select D from ServiceRequest D where D.customerId=:customerId and D.reqId=:reqId")
    Optional<ServiceRequest> fetchRequest(int customerId, int reqId);
}
