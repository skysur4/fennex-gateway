package io.cooltime.gateway.repository.score;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import io.cooltime.gateway.entity.score.Boss;


@Repository
public interface BossRepository extends R2dbcRepository<Boss, String> {
}

