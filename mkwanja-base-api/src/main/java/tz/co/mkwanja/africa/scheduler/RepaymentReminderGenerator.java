package tz.co.mkwanja.africa.scheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author Benjamini Buganzi
 * @Date 26/03/2022.
 */
@Component
public class RepaymentReminderGenerator {


    @Scheduled(cron = "0 0/3 00 * * ?")
    public void update() {
        System.out.println("Starting Repayment reminder");
        //Send notifications
        System.out.println("Finishing Repayment reminder");
    }
}
