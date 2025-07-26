package pro.fennex.gateway.repository.score;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import pro.fennex.gateway.entity.score.Boss;


@Repository
public interface BossRepository extends R2dbcRepository<Boss, String> {
}

