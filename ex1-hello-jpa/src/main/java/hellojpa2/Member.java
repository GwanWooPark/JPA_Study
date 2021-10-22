package hellojpa2;
import javax.persistence.*;
import java.util.Date;

@Entity
/*@TableGenerator(
        name = "member_seq_generator",
        table = "my_sequences",
        pkColumnValue = "member_seq", allocationSize = 1)*/
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
public class Member {

    // 직접 할당
    @Id
    // 자동생성
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
