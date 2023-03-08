import commons.MyMessageDigest;
import domain.Member;
import org.apache.commons.dbcp2.BasicDataSource;
import repository.MemberRepository;
import view.LoginViewer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Main {
    private static final LoginViewer loginViewer = new LoginViewer();
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String ID = "board";
    private static final String PW = "board";
    private static final int INITIAL_SIZE = 8;
    private static final BasicDataSource basicDataSource = inItBasicDataSource();
    private static final MyMessageDigest myMessageDigest = inItMyMessageDigest();


    private static final MemberRepository memberRepository = new MemberRepository(basicDataSource);

    public static void main(String[] args) {
        Member member = login();
    }

    private static MyMessageDigest inItMyMessageDigest() {
        try {
            return new MyMessageDigest(MessageDigest.getInstance("SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static BasicDataSource inItBasicDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(ID);
        basicDataSource.setPassword(PW);
        basicDataSource.setInitialSize(INITIAL_SIZE);
        return basicDataSource;
    }


    private static Member login() {
        while (true) {
            try {
                int commend = loginViewer.askCommend();
                if (commend == 1) {
                    String id = loginViewer.askId();
                    String pw = myMessageDigest.encode(loginViewer.askPw());
                    return memberRepository.findByIdAndPw(id, pw);
                } else if (commend == 2) {
                    String id = loginViewer.askId();
                    String pw = myMessageDigest.encode(loginViewer.askPw());
                    String name = loginViewer.askName();
                    String contact = loginViewer.askContact();
                    Member member = new Member(id, pw, name, contact, null);
                    memberRepository.insert(member);
                } else if (commend == 3) {
                    loginViewer.printExit();
                    System.exit(0);
                }
            } catch (Exception e){
                e.printStackTrace();
                loginViewer.printException(e.getMessage());
            }

        }
    }

}

