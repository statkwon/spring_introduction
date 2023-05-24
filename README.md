# 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술
---
## 프로젝트 환경설정
### Welcome Page 만들기
- Spring Boot supports both static and templated welcome pages. It first looks for an `index.html` file in the configured static content locations. If one is not found, it then looks for an `index` template. If either is found, it is automatically used as the welcome page of the application.

## 스프링 웹 개발 기초
### 정적 컨텐츠
![fig1.png](..%2F..%2FDesktop%2Ffig1.png)
- 스프링 컨테이너 안에 hello-static 관련 컨트롤러가 존재하지 않으면 `resources:static/hello-static.html`을 찾아 화면을 렌더링한다.
### MVC와 템플릿 엔진
![fig2.png](..%2F..%2FDesktop%2Ffig2.png)
- 컨트롤러에서 리턴 값으로 문자(viewName)를 반환하면 `viewResolver`가 `resource:templates/viewName.html`을 찾아 템플릿 엔진 처리를 통해 화면을 렌더링한다.
### API
- `@Responsebody`를 사용하면 `viewResolver`를 사용하지 않고, `HttpMessageConverter`가 동작하여 HTTP의 Body에 문자 내용을 직접 반환한다.
- 기본 문자 처리는 `StringHttpMessageConverter`가, 기본 객체 처리는 `MappingJackson2HttpMessageConverter`가 담당한다.
- 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보를 조합하여 `HttpMessageConverter`가 선택된다.
