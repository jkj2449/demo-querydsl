package study.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.awt.event.HierarchyListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

@SpringBootTest
@Transactional
class ApplicationTests {

  @PersistenceContext
  EntityManager entityManager;

  @Test
  void contextLoads() {
    Hello hello = new Hello();
    entityManager.persist(hello);

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QHello qHello = new QHello("H");

    Hello result = queryFactory
        .selectFrom(qHello)
        .fetchOne();

    assertThat(result).isEqualTo(hello);
    assertThat(result.getId()).isEqualTo(hello.getId());
  }

}
