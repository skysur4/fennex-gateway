package pro.fennex.gateway.handler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

public class InheritedRequestMappingHandler extends RequestMappingHandlerMapping {
  @Override
  protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
    RequestMappingInfo requestMappingInfo = super.getMappingForMethod(method, handlerType);

    // 요청 URL 정보가 없을 경우 null을 반환합니다.
    if (requestMappingInfo == null) {
      return null;
    }

    List<String> superclassUrlPatterns = new ArrayList<>();
    Class<?> superclass = handlerType.getSuperclass();
    // Method의 상위 클래스를 모두 검색하면서
    // 클래스에 @RequestMapping 값이 존재할 경우 Pattern을 추가합니다.
    for (; superclass != Object.class; superclass = superclass.getSuperclass()) {
      if (superclass.isAnnotationPresent(RequestMapping.class)) {
        superclassUrlPatterns.add(0, superclass.getAnnotation(RequestMapping.class).value()[0]);
      }
    }

    if (superclassUrlPatterns.isEmpty()) {
        // 상위 클래스에 @RequestMapping이 없는 경우
        // 기존 RequestMappingInfo를 그대로 반환합니다.
    	return requestMappingInfo;

    } else {
        // 상위 클래스에 @RequestMapping이 존재했다면
        // Pattern을 기준으로 RequestMappingInfo를 생성하고, 기존 RequestMapping 정보와 결합합니다.
    	RequestMappingInfo superclassRequestMappingInfo = RequestMappingInfo.paths(String.join("", superclassUrlPatterns)).build();

    	return superclassRequestMappingInfo.combine(requestMappingInfo);
    }
  }
}