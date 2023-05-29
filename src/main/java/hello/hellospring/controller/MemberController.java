package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 컨테이너에 MemberController 객체를 생성하여 넣어둠
// @Component로도 컴포넌트 스캔이 가능 -> @Controller, @Service, @Repository는 @Component를 포함
public class MemberController {

    private final MemberService memberService;

    // 여러 컨트롤러에서 MemberService를 공유할 수 있으므로 매번 새로운 인스턴스를 생성할 필요가 없음 -> 스프링 컨테이너에 MemberService를 등록하여 사용

    // Dependency Injection
    // 1. Field Injection
//    @Autowired private MemberService memberService;

    // 2. Setter Injection
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    // 3. Constructor Injection
    @Autowired // MemberController가 생성될 때 생성자에 @Autowired가 붙어 있으면 스프링 컨테이너 안에 있는 MemberService를 연결해 줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        // 스프링이 input 박스에 입력한 name을 MemberForm의 name에 넣어준다.
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
