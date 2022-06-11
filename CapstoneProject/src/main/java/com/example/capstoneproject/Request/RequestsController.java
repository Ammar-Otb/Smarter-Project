package com.example.capstoneproject.Request;

import com.example.capstoneproject.DTO.API;
import com.example.capstoneproject.session.MySession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequiredArgsConstructor @RequestMapping("api/v1/request/")
public class RequestsController {
    private final RequestService requestService;

    @GetMapping
    public ResponseEntity getRequests(){
        return ResponseEntity.ok(requestService.getRequests());
    }

    @PostMapping("send")
    public ResponseEntity sendRequest(@RequestBody @Valid MyRequest request){
        try {
            requestService.sendRequest(request);
            return ResponseEntity.status(201).body(new API("Successfully Sent Request",201));
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(new API("Incorrect Data Entered", 400));
        }
    }
    @DeleteMapping
    public ResponseEntity deleteAll(){
        requestService.removeAllRequests();
        return ResponseEntity.ok("Deleted All requests");
    }

    @PutMapping("accept/{tutorId}/{requestId}")
    public ResponseEntity acceptRequest(@PathVariable Integer requestId, @PathVariable Integer tutorId, @RequestBody MySession session){
        try {
            requestService.acceptRequest(requestId, tutorId,session);
            return ResponseEntity.status(201).body(new API("Successfully accepted Request",201));

        }
        catch (Exception e){
            return ResponseEntity.status(400).body(new API("Incorrect Data Entered", 400));
        }
    }
}
