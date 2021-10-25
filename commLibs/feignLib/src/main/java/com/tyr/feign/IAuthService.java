package com.tyr.feign;

import com.tyr.base.bean.client.OauthClientDetails;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.bean.user.SysUser;
import com.tyr.base.utils.Constants;
import com.tyr.feign.fallbackfactory.IAuthServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Class : IAuthService
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/16 12:28
 * @Version : 1.0
 */
@FeignClient(name = Constants.BUSINESS_SERVICE, fallbackFactory = IAuthServiceFallbackFactory.class, decode404 = true)
public interface IAuthService {

    @GetMapping("/auth/findUserByUsername/{username}")
    ResponseData<SysUser> findUserByUsername(@PathVariable("username") String username);

    @GetMapping("/auth/findClientDetailsById/{clientId}")
    ResponseData<OauthClientDetails> findClientDetailsById(@PathVariable("clientId") String clientId);

    @GetMapping("/auth/findAllClientDetails")
    ResponseData<List<OauthClientDetails>> findAllClientDetails();

    @PostMapping("/auth/addClientDetails")
    ResponseData<Void> addClientDetails(@RequestParam("data") String data);

    @PutMapping("/auth/updateClientDetails")
    ResponseData<Void> updateClientDetails(@RequestParam("data") String data);

    @PutMapping("/auth/updateClientSecret/clientId/{clientId}/secret/{secret}")
    ResponseData<Void> updateClientSecret(@PathVariable("clientId") String clientId, @PathVariable("secret") String secret);

    @DeleteMapping("/auth/deleteClientDetails/{clientId}")
    ResponseData<Void> deleteClientDetails(@PathVariable("clientId") String clientId);

}
