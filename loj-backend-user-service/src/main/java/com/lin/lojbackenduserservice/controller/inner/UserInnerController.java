package com.lin.lojbackenduserservice.controller.inner;

import com.lin.lojbackendserviceclient.service.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inner")
@Slf4j
public class UserInnerController implements UserFeignClient {
}
