package com.covid.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.covid.rest.Repositories.ConditionRepository;
import com.covid.rest.entity.Conditions;
import com.covid.rest.model.LoginModel;
import com.telstra.ApiClient;
import com.telstra.ApiException;
import com.telstra.Configuration;
import com.telstra.auth.OAuth;
import com.telstra.messaging.AuthenticationApi;
import com.telstra.messaging.MessageSentResponseSms;
import com.telstra.messaging.MessagingApi;
import com.telstra.messaging.OAuthResponse;
import com.telstra.messaging.ProvisionNumberRequest;
import com.telstra.messaging.ProvisionNumberResponse;
import com.telstra.messaging.ProvisioningApi;
import com.telstra.messaging.SendSMSRequest;
@Component
public class ConditionsServiceImpl implements ConditionsService{
	@Autowired
	ConditionRepository repo;
	@Override
	public Conditions addCondition(Conditions condition) {
		Conditions cond = repo.save(condition);
		sendMessage(cond);
		return cond;
	}

	@Override
	public List<Conditions> getConditions(LoginModel model) {
		return repo.findByMobile(model.getMobile());
	}
	
	private void sendMessage(Conditions condition){
		ApiClient defaultClient = Configuration.getDefaultApiClient();
	    defaultClient.setBasePath("https://tapi.telstra.com/v2");
	    
	 // Configure OAuth2 access token for authorization
	    OAuth auth = (OAuth) defaultClient.getAuthentication("auth");
	    AuthenticationApi authenticationApi = new AuthenticationApi(defaultClient);
	    String clientId = "3GCUegj2VxHxXmVwvofP77LJBAIEaiA5";
	    String clientSecret = "QlDbEzCf5U7IwJWP";
	    String grantType = "client_credentials";
	    String scope = "NSMS";
	    
	    try {
	        OAuthResponse oAuthResponse = authenticationApi.authToken(clientId, clientSecret, grantType, scope);
	        auth.setAccessToken(oAuthResponse.getAccessToken());
	      } catch (ApiException e) {
	        System.err.println("Exception when calling AuthenticationApi#authToken");
	        System.err.println("Status code: " + e.getCode());
	        System.err.println("Reason: " + e.getResponseBody());
	        System.err.println("Response headers: " + e.getResponseHeaders());
	        e.printStackTrace();
	      }
	    
	 // Configure phone number subscription
	    ProvisioningApi provisioningApiInstance = new ProvisioningApi(defaultClient);
	    try {
	      ProvisionNumberRequest provisionNumberRequest = new ProvisionNumberRequest();
	      ProvisionNumberResponse result = provisioningApiInstance.createSubscription(provisionNumberRequest);
	      System.out.println(result);
	    } catch (ApiException e) {
	      System.err.println("Exception when calling ProvisioningApi#createSubscription");
	      System.err.println("Status code: " + e.getCode());
	      System.err.println("Reason: " + e.getResponseBody());
	      System.err.println("Response headers: " + e.getResponseHeaders());
	      e.printStackTrace();
	    }
	    
	 // Send SMS
	    MessagingApi msgingApiInstance = new MessagingApi(defaultClient);
	    try {
	      SendSMSRequest sendSmsRequest = new SendSMSRequest();
	      sendSmsRequest.to("+91"+condition.getMobile());
	      sendSmsRequest.body("BeSafe- Your health status has been changed to "+condition.getStatus()+ " by authorised personnel");
	      MessageSentResponseSms result = msgingApiInstance.sendSMS(sendSmsRequest);
	      System.out.println(result);
	    } catch (ApiException e) {
	      System.err.println("Exception when calling MessagingApi#sendSMS");
	      System.err.println("Status code: " + e.getCode());
	      System.err.println("Reason: " + e.getResponseBody());
	      System.err.println("Response headers: " + e.getResponseHeaders());
	      e.printStackTrace();
	    }
	}
	
}
