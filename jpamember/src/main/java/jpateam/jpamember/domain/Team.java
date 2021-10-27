package jpateam.jpamember.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    // ArrayList로 초기화
    // mappedBy = 둘중 하나로 외래키를 관리해야하기 때문에 연관관계의 주인이 설정되어야함
    // 그걸 이제 mappedBy로 주인은 사용하지 않고, 주인이 아닌 쪽은 mappedBy로 주인 지정
    // 주인이 아닌쪽은 Rean-Only만 가능
    // 외래키가 있는 곳을 주인으로 정해라.
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
