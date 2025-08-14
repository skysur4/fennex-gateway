package pro.fennex.gateway.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public abstract class BaseV1Controller extends BaseController{
    public static String getUnionName(String nickName){
        String postfix = nickName.replaceAll("[^◆♧]", "");

        String union = "";
        if("◆".equals(postfix)){
            union = "senior";
        } else if("♧".equals(postfix)){
            union = "junior";
        } else {
            union = "expert";
        }

        return union;
    }
}