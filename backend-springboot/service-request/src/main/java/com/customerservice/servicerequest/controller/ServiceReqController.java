package com.customerservice.servicerequest.controller;

import java.util.List;
import java.util.Optional;

import com.customerservice.servicerequest.entity.ServiceRequest;
import com.customerservice.servicerequest.exception.ServiceReqException;
import com.customerservice.servicerequest.service.ServiceReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/serviceRequest")
public class ServiceReqController {

    @Autowired
    private ServiceReqService requestService;

    @PostMapping("/{customerId}/createRequest")
    public ServiceRequest createRequest(@PathVariable int customerId, @RequestBody ServiceRequest reqdetails) throws ServiceReqException {
        ServiceRequest request = requestService.createReqByCustomer(customerId, reqdetails);
        return request;

    }
    @GetMapping("/findRequestByID/{reqId}")
    public Optional<ServiceRequest> findrequests(@PathVariable int reqId) {
        Optional<ServiceRequest> request=requestService.findrequestbyid(reqId);
        return request;
    }
    @GetMapping("/getListOfRequests")
    public List<ServiceRequest> getListOfRequests() {
        return requestService.getRequests();
    }
    @GetMapping("/findReqByCustomerId/{id}")
    public List<ServiceRequest> getrequestbycustomerId(@PathVariable int id) {
        return requestService.getRequestByCustomerId(id);
    }

    @GetMapping("/findReqbyStatus/{id}/{status}")
    public List<ServiceRequest> getRequestByStatus(@PathVariable int id, @PathVariable String status) {
        return requestService.getRequestByStatus(id, status);
    }

    @PutMapping("/{customerId}/updateRequest/{reqId}")
    public ServiceRequest updatereq(@PathVariable int customerId,@PathVariable int reqId,@RequestBody ServiceRequest reqDetails) {
        ServiceRequest req=requestService.updateReqDetails(customerId, reqId, reqDetails);
        return req;
    }

    @DeleteMapping("/deletereq/{id}/{reqId}")
    public String deletereq(@PathVariable int id,@PathVariable int reqId) throws ServiceReqException {
        return requestService.deleteReq(id,reqId);
    }
}
