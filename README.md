![Springboot](https://shields.io/badge/v3.4.6-6DB33F?logo=springboot&label=Spring%20Boot&style=social)  
![Springcloud](https://shields.io/badge/2024.0.1-6DB33F?logo=springboot&label=Spring%20Cloud&style=social)  
![Gradle](https://img.shields.io/badge/v8.9-02303A?logo=gradle&label=Gradle&style=social)  
![Postgresql](https://img.shields.io/badge/v17-4169E1?logo=postgresql&label=PostgreSQL&style=social)  

# Fennex Gateway
키클락 연동하여 인증 및 권한 관리

# R2DBC에 관하여
r2dbc에서는 webflux에 대응하는 유일한 라이브러리이나, JPA의 영속성이나 jakarta.persistence.Id를 제대로 지원해주지 않음
그렇다고 org.springframework.data.annotation.Id에 IdClass를 활용한 복합키를 지원하지도 않음
실제로 JPA를 통한 테이블 생성은 JPA가 제대로 설정된 다른 프로젝트로 수행함
Persistable로 인하여 save 시 insert나 update에 대한 결정은 가능함

** 단, select 시  findById는 불가능하며 각 컬럼에 대한 인터페이스를 Repository에 정의해 주어야 함

1. webflux에서 디비를 연결하기 위해서는 r2dbc를 사용할 수 밖에 없음 (JPA는 non-block 방식 X)
2. Entity 설정으로 JPA DDL update는 불가능. 수동 생성하거나 WEB 프로젝트를 사용
3. 가급적이면 Gateway는 앱라우팅 및 인증을 수행하기 위한 원래의 용도로만 쓰자