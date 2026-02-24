@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String nickname; // 추가: 닉네임 컬럼
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    // 회원가입 시 사용
    public User(String email, String password, UserRole userRole, String nickname) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.nickname = nickname; // 추가
    }

    // AuthUser 변환용
    private User(Long id, String email, UserRole userRole, String nickname) {
        this.id = id;
        this.email = email;
        this.userRole = userRole;
        this.nickname = nickname; // 추가
    }

    public static User fromAuthUser(AuthUser authUser) {
        return new User(
                authUser.getId(),
                authUser.getEmail(),
                authUser.getUserRole(),
                authUser.getNickname() // AuthUser에도 필드 추가 필요
        );
    }
    // ... 기존 메서드
}