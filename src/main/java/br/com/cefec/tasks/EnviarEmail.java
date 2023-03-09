package br.com.cefec.tasks;

import br.com.cefec.client.EmailClient;
import br.com.cefec.dtos.EmailDto;
import br.com.cefec.services.FaturamentoServices;
import br.com.cefec.services.ProdutoServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class EnviarEmail {

    @Autowired
    ProdutoServices produtoServices;

    @Autowired
    EmailClient emailClient;

    @Autowired
    FaturamentoServices faturamentoServices;

    private static final Logger logger = LoggerFactory.getLogger(EmailClient.class);

    @Scheduled(cron = "0 0 0 * * *")
    public void executarTarefaDiaria() {
        Double faturamentoDia = faturamentoServices.getFaturamentoDia(LocalDate.now().minusDays(1));
        try {
            emailClient.sendingEmail(EmailDto.of(faturamentoDia));
        } catch (Exception e) {
            logger.error("Erro: " + e.getMessage());
        }
    }

}