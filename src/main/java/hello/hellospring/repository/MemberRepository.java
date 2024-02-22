package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // member가 저장소에 저장됨
    Optional<Member> findById(Long id); // id로 저장소에서 member를 찾음
    Optional<Member> findByName(String name); // name으로 저장소에서 member를 찾음
    List<Member> findAll(); // 저장소에 있는 모든 member 반환
}
