package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // 스프링이 EntityManager를 만들어줌 | JPA를 사용하려면 주입받아야함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // JPA가 insert 쿼리 만들어서 DB에 넣음 + setId
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회할 타입과 pk값 넣어주면 조회됨
        return Optional.ofNullable(member); // 값이 없을 수도 있어서 ofNullable 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // 객체를 대상으로 쿼리를 날림 -> sql로 번역됨
                .getResultList();
    }
}
