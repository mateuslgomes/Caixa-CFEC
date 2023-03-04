package br.com.cefec.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String msg) {
        this.text = msg;
    }

    public static EmailDto of(String text) {
        return EmailDto.builder()
                .ownerRef("mateuslogo08@gmail.com")
                .emailFrom("mateuslogo08@gmail.com")
                .subject("Faturamento")
                .emailTo("mateuslogo08@gmail.com")
                .text(text)
                .build();
    }

}
