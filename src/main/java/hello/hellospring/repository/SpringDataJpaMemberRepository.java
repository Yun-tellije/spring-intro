package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    // AND, OR 같은 거 사용 가능 ex) findByNameAndId(String name, Long id)
    // -> 메서드 이름 만으로 조회하는 기능 제공
    // 페이징 기능 제공
    @Override
    Optional<Member> findByName(String name);
}
