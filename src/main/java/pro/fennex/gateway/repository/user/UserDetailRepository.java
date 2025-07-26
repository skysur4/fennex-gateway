package pro.fennex.gateway.repository.user;

import org.springframework.stereotype.Repository;

import pro.fennex.gateway.entity.user.UserDetail;
import pro.fennex.gateway.repository.GatewayReactiveCrudRepository;


@Repository
public interface UserDetailRepository extends GatewayReactiveCrudRepository<UserDetail, String> {
}

