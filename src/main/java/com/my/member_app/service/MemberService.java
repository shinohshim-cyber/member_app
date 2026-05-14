package com.my.member_app.service;

import com.my.member_app.dto.MemberDto;
import com.my.member_app.entity.Member;
import com.my.member_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    //  의존성 주입 : 필요한 컴포넌트(인스턴스)를 불러오는 작업
    //  1. 첫번째 주입방법
    //@Autowired
    //MemberRepository memberRepository;
    //  2. 생성자 주입방법
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}
    //  3. @RequiredArgsConstruct
    private final MemberRepository memberRepository;

    public List<MemberDto> findAll() {
        //  Repository에서 필요한 정보를 가져온다.
        //  단, Repo는 Entity만 사용한다.
        List<Member> members = memberRepository.findAll();
        //  Entity List -> Dto List로 변환한 후 리턴한다.
        //  깡통 DtoList를 만든다.
        //  1. for each로 수행
          //List<MemberDto> dtoList = new ArrayList<>();
//        for(Member member : members){
//            dtoList.add(MemberDto.toDto(member));
//        }
        //  2. 스트림을 이용해서 처리하기
        return members
                .stream()
                .map(x -> MemberDto.toDto(x))
                .toList();
    }

    public void insert(MemberDto dto) {
        memberRepository.save(MemberDto.toEntity(dto));
    }

    public void delete(Long deleteId) {
        memberRepository.deleteById(deleteId);
    }
}
