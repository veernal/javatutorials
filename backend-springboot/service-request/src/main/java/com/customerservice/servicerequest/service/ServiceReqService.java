package com.customerservice.servicerequest.service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.customerservice.servicerequest.client.CustomerClient;
import com.customerservice.servicerequest.entity.Customer;
import com.customerservice.servicerequest.entity.ServiceRequest;
import com.customerservice.servicerequest.exception.ServiceReqException;
import com.customerservice.servicerequest.repository.ServiceReqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceReqService {

    @Autowired
    private ServiceReqRepository reqRepo;

    @Autowired
    private CustomerClient customerClient;

    public ServiceRequest createReqByCustomer(int customerId, ServiceRequest reqdetails) throws ServiceReqException {
        // TODO Auto-generated method stub

        Optional<Customer> customer = customerClient.getCustomerByID(customerId);

        ServiceRequest Reqdetails = new ServiceRequest();
        if (!customer.isEmpty()) {
            if (reqdetails.getServiceType() != null) {
                Reqdetails.setReqName(reqdetails.getReqName());
                Reqdetails.setCustomerName(customer.get().getCustomerName());
                Reqdetails.setCustomerId(customer.get().getId());
                Reqdetails.setReqdescription(reqdetails.getReqdescription());
                Reqdetails.setReqStatus(reqdetails.getReqStatus());
                Reqdetails.setAssignedTo(reqdetails.getAssignedTo());
                Reqdetails.setServiceType(reqdetails.getServiceType());
                Date date = new Date();
                Reqdetails.setDate(date);
                reqRepo.save(Reqdetails);
            }
            else {
                throw new ServiceReqException("give all details");
            }

        }
        else {
            throw new ServiceReqException("invalid customerId ");
        }
        return Reqdetails;
    }

    public Optional<ServiceRequest> findrequestbyid(int reqId) {
        // TODO Auto-generated method stub
        return reqRepo.findById(reqId);
    }

    public List<ServiceRequest> getRequests() {
        // TODO Auto-generated method stub
        return reqRepo.findAll();
    }

    public List<ServiceRequest> getRequestByCustomerId(int id) {
        // TODO Auto-generated method stub
        return reqRepo.findReqByCustId(id);
    }

    public List<ServiceRequest> getRequestByStatus(int id, String status) {
        // TODO Auto-generated method stub
        return reqRepo.findReqByStatus(id,status);
    }

    public ServiceRequest updateReqDetails(int customerId, int reqId, ServiceRequest reqDetails) {
        // TODO Auto-generated method stub

        Optional<ServiceRequest> request=reqRepo.fetchRequest(customerId,reqId);
        if (!request.isEmpty()) {
            if(reqDetails.getAssignedTo() != null){
                request.get().setAssignedTo(reqDetails.getAssignedTo());
            }
            if(reqDetails.getReqdescription()!=null) {
                request.get().setReqdescription(reqDetails.getReqdescription());
            }
            if(reqDetails.getServiceType()!=null) {
                request.get().setServiceType(reqDetails.getServiceType());
            }
            if(reqDetails.getReqName()!=null) {
                request.get().setReqName(reqDetails.getReqName());
            }
            if(reqDetails.getReqStatus()!=null) {
                request.get().setReqStatus(reqDetails.getReqStatus());
            }

        }
        return reqRepo.save(request.get());
    }

    public String deleteReq(int id, int reqId) {

        Optional<ServiceRequest> req=reqRepo.fetchRequest(id, reqId);
        if(!req.isEmpty() && req.get().getReqStatus().equalsIgnoreCase("close")) {
            reqRepo.deleteById(req.get().getReqId());
            return "deleted successfully";
        }else {
            return "request id not found or the request status is open";
        }


    }
}
