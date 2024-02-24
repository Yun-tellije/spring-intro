package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 동시성 문제 때문에 실무에서는 concurrent hashmap 사용
    private static long sequence = 0L; // 키 값을 생성해줌, 동시성 문제를 고려해서 long 보다는 Atomic long 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // store의 값이 null이어도 감쌀 수가 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        return  store.values().stream()
                .filter(member -> member.getName().equals(name)) // 받아온 name과 member의 name이 같은지 확인
                .findAny(); // 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
