package ex5;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Period {

    // 기간 Period
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Period() {
    }

    public boolean isWork() {
        return startAt.isBefore(endAt);
    }
}
