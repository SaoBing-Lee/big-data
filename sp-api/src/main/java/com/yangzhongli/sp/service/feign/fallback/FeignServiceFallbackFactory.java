//package com.yangzhongli.sp.service.feign.fallback;
//
//import com.yangzhongli.sp.service.feign.FeignApiClient;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import feign.hystrix.FallbackFactory;
//
//@Component
//public class FeignServiceFallbackFactory  implements FallbackFactory<FeignApiClient>{
//    private static final Logger LOG = LoggerFactory.getLogger(FeignServiceFallbackFactory.class);
//
//    public FeignApiClient create(Throwable cause) {
//
//        if (cause != null && cause.getMessage() != null && !"".equals(cause.getMessage())) {
//            LOG.error("fallback reason was:" + cause.getMessage(), cause);
//        }
//
//        return new FeignApiClient() {
//
//        };
//    }
//
//}