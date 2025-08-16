package pro.fennex.gateway.repository.user;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pro.fennex.gateway.entity.user.Members;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface MembersRepository extends R2dbcRepository<Members, String> {
    static final String SELECT_QUERY = "select " +
            "    m.nickname " +
            "    , m.union_name " +
            "    , m.team_used1 " +
            "    , m.team_used2 " +
            "    , m.team_used3 " +
            "    , m.updated_at " +
            "    , coalesce(max(s.simulated_level1) > 0, false) as simulated_level1 " +
            "    , coalesce(max(s.simulated_level2) > 0, false) as simulated_level2 " +
            "    , coalesce(max(s.simulated_level3) > 0, false) as simulated_level3 " +
            "  from members m " +
            "  left " +
            "  join (select " +
            "            nickname " +
            "           , case when s.level = '1' then count(s.level) else 0 end as simulated_level1 " +
            "           , case when s.level = '2' then count(s.level) else 0 end as simulated_level2 " +
            "           , case when s.level = '3' then count(s.level) else 0 end as simulated_level3 " +
            "           from score s " +
            "           group by nickname, level " +
            "        ) s " +
            "     on s.nickname = m.nickname" +
            "  group by m.nickname ";

    @Query(value = SELECT_QUERY +
            " order by m.nickname")
    Flux<Members> findAllMembers();

    @Query(value =
        "  insert into members " +
        "  select distinct false " +
        "         , false " +
        "         , false " +
        "         , current_timestamp " +
        "         , s.nickname " +
        "         , s.union_name " +
        "    from score s " +
        "   where s.nickname not in (select distinct nickname from members)")
    Mono<Void> saveAuto();

    Mono<Void> deleteByNickname(String nickname);

    Mono<Void> deleteAllByUnionName(String union);
}

