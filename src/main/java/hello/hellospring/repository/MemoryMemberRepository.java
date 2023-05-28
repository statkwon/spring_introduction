package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 메모리 기반 저장소이기 때문에 서버 연결이 중단되면 데이터가 전부 소멸됨
//@Repository // 스프링 컨테이너에 MemberRepository 객체를 생성하여 넣어둠
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 실무에서는 동시성 문제로 인해 ConcurrentHashMap 사용
    private static long sequence = 0L; // 같은 이유로 AtomicLong 사용

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store.get(id)를 반환해도 되지만 Null이 반환되는 경우를 고려하여 Optional 객체 사용
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
