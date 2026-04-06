package com.klef.fsad.sdp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.fsad.sdp.entity.CertificateDetails;
import com.klef.fsad.sdp.entity.User;
import com.klef.fsad.sdp.repository.CertificateRepository;
import com.klef.fsad.sdp.repository.UserRepository;

@Service
public class CertificateServiceImpl implements CertificateService{

	@Autowired
	private CertificateRepository certificateRepository;

	@Autowired
	private UserRepository userRepository;
	@Override
	public String addCertificate(CertificateDetails cert) 
	{
	    certificateRepository.save(cert);
	    return "Certificate Added Successfully";
	}
	@Override
	public List<CertificateDetails> viewCertificatesByUser(int userid) 
	{
	    User user = userRepository.findById(userid).orElse(null);

	    if(user != null)
	    {
	        return certificateRepository.findByUser(user);
	    }

	    return null;
	}
	@Override
	public String updateCertificateByName(CertificateDetails cert) {
		 CertificateDetails c = certificateRepository.findByCertName(cert.getCertName());

		    if(c != null)
		    {
		        c.setIssueDate(cert.getIssueDate());
		        c.setExpiryDate(cert.getExpiryDate());
		        c.setCertificateUrl(cert.getCertificateUrl());

		        certificateRepository.save(c);
		        return "Certificate Updated Successfully";
		    }

		    return "Certificate Not Found";
	}
	@Override
	public String deleteByCertNameAndUserId(String certName, int userid) 
	{
	    CertificateDetails cert = certificateRepository.findByCertNameAndUser_Id(certName, userid);

	    if(cert != null)
	    {
	        certificateRepository.delete(cert);
	        return "Certificate Deleted Successfully";
	    }

	    return "Certificate Not Found";
	}
	

}
