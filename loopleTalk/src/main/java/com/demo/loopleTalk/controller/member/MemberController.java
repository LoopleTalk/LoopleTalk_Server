package com.demo.loopleTalk.controller.member;

import com.demo.loopleTalk.domain.member.Member;
import com.demo.loopleTalk.dto.member.AddMemberRequest;
import com.demo.loopleTalk.dto.member.MemberResponse;
import com.demo.loopleTalk.dto.member.MembersResponse;
import com.demo.loopleTalk.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody AddMemberRequest addMemberRequest) {
        Member member = memberService.create(addMemberRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(member);
    }

    @GetMapping("/{email}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable(name = "email") String email) {
        Member member = memberService.find(email);
        return ResponseEntity.ok()
                .body(new MemberResponse(member));
    }

    @GetMapping
    public ResponseEntity<List<MembersResponse>> findAllMember() {
        List<MembersResponse> members = memberService.findAll()
                .stream()
                .map(MembersResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(members);
    }
}
