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
        return this.startAt.isBefore(this.endAt);
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
}
