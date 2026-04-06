package com.klef.fsad.sdp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.fsad.sdp.entity.CertificateDetails;
import com.klef.fsad.sdp.service.CertificateService;

@RestController
@RequestMapping("cert")
@CrossOrigin("*")
public class CertificateController {
	
	@Autowired
	public CertificateService certificateservice;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addCertificate(@RequestBody CertificateDetails cert)
	{
	    try
	    {
	        String output = certificateservice.addCertificate(cert);
	        return ResponseEntity.status(201).body(output);
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Internal Server Error");
	    }
	}
	@GetMapping("/viewcertsbyuser/{userid}")
	public ResponseEntity<?> viewCertificates(@PathVariable int userid)
	{
	    try
	    {
	        List<CertificateDetails> list = certificateservice.viewCertificatesByUser(userid);

	        if(list != null && list.size() > 0)
	        {
	            return ResponseEntity.ok(list);
	        }
	        else
	        {
	            return ResponseEntity.noContent().build();
	        }
	    }
	    catch(Exception e)
	    {
	        return ResponseEntity.status(500).body("Error Fetching Certificates");
	    }
	}

}
