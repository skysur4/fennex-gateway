package pro.fennex.gateway.repository.user;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pro.fennex.gateway.entity.user.Members;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface MembersRepository extends R2dbcRepository<Members, String> {
    static final String SELECT_QUERY = "select" +
            "    m.nickname" +
            "    , m.union_name" +
            "    , m.team_used1" +
            "    , m.team_used2" +
            "    , m.team_used3" +
            "    , m.updated_at" +
            "    , case" +
            "        when s.level = '1'" +
            "        then s.cnt > 0" +
            "        else false" +
            "      end as simulated_level1" +
            "    , case" +
            "        when s.level = '2'" +
            "        then s.cnt > 0" +
            "        else false" +
            "      end as simulated_level2" +
            "    , case" +
            "        when s.level = '3'" +
            "        then s.cnt > 0" +
            "        else false" +
            "      end as simulated_level3" +
            "  from members m" +
            "  left" +
            "  join (select" +
            "            nickname" +
            "            , level" +
            "            , nullif(count(*), 0) as cnt" +
            "          from score s" +
            "         group by nickname, level" +
            "        ) s" +
            "     on s.nickname = m.nickname";

    @Query(value = SELECT_QUERY +
            " order by m.nickname")
    Flux<Members> findAllMembers();

    @Query(value =
        "  insert into members" +
        "  select distinct false" +
        "         , false" +
        "         , false" +
        "         , current_timestamp" +
        "         , s.nickname" +
        "         , s.union_name" +
        "    from score s" +
        "   where s.nickname not in (select distinct nickname from members)")
    Mono<Void> saveAuto();

    Mono<Void> deleteByNickname(String nickname);

    Mono<Void> deleteAllByUnionName(String union);
}

