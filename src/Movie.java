import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public void changedDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;

    }

    public Movie(String title, Duration runningTime, Money fee, DefaultDiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;

    }

    public Money getFee() {
        return fee;

    }

    public Money calculateMovieFee(Screening screening) {
        if (discountPolicy == null) {
            return fee;

        }
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));

    }

    Movie avatar = new Movie("아바타", Duration.ofMinutes(120), Money.wons(10000),
            new AmountDiscountPolicy(Money.wons(800), new SequenceCondition(1), new SequenceCondition(10),
            new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
            new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))));

    // Movie starWars = new Movie("스타워즈",
    // Duration.ofMinutes(210),
    // Money.wons(10000),
    // new NoneDiscountPolicy());

    // Movie titanic = new Movie("타이타닉",
    // Duration.ofMinutes(180),
    // Money.wons(11000),
    // new PercentDiscountPolicy(0.1,
    // new SequenceCondition(2),
    // new PeriodCondition(DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(16,
    // 59)),
    // new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(13,
    // 59))));
}
