package com.coronacommunity.CoronaCommunity.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    //출력할 alert 메시지정보
    String message = "";
    //메시지 출력후 이동할 경로
    String href = "";

    public Message(String message, String href) {
        this.message = message;
        this.href = href;
    }
}
