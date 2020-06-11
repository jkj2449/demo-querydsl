package study.querydsl.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberTest {
  @PersistenceContext
  EntityManager entityManager;

  @Test
  public void testEntity() {
    Team teamA = new Team("teamA");
    Team teamB = new Team("teamB");
    entityManager.persist(teamA);
    entityManager.persist(teamB);

    Member member1 = new Member("member1", 10, teamA);
    Member member2 = new Member("member2", 20, teamA);

    Member member3 = new Member("member3", 30, teamB);
    Member member4 = new Member("member4", 40, teamB);

    entityManager.persist(member1);
    entityManager.persist(member2);
    entityManager.persist(member3);
    entityManager.persist(member4);

    List<Member> members = entityManager
        .createQuery("select m from Member m", Member.class)
        .getResultList();

    assertThat(members)
        .contains(member1)
        .contains(member2)
        .contains(member3)
        .contains(member4);
  }
}