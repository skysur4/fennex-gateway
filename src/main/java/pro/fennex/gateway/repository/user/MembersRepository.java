package pro.fennex.gateway.repository.user;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pro.fennex.gateway.entity.user.Members;
import reactor.core.publisher.Mono;


@Repository
public interface MembersRepository extends R2dbcRepository<Members, String> {
    Mono<Void> deleteByNickname(String nickname);

    Mono<Void> deleteAllByUnionName(String union);
}

