package model.guestbook;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GuestBookBean implements Serializable {
    private int num; // 글 번호, 시퀀스
    private String title; // 글 제목
    private String author; // 글 작성자
    private String email; // 글 작성자 전자메일
    private String content; // 글 내용
    private String password; // 글 비밀번호
    private String writeday; // 글 작성일
    private int readnum; // 글 조회수

    public GuestBookBean(int num) {
        this.num = num;
    }

    public GuestBookBean(int num, String title, String author, String email, String content) {
        this.num = num;
        this.title = title;
        this.author = author;
        this.email = email;
        this.content = content;
    }

    public GuestBookBean(String title, String author, String email, String content, String password) {
        this.title = title;
        this.author = author;
        this.email = email;
        this.content = content;
        this.password = password;
    }
}
