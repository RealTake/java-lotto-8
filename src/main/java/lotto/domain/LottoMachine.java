package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 로또를 만들어 발급하는 클래스
 */
public class LottoMachine {
    private static final int PRICE_PER_TICKET = 1000;

    public static List<Lotto> issue(int amount) {
        validateAmount(amount);
        final int ticketCount = amount / PRICE_PER_TICKET;

        return IntStream.range(0, ticketCount)
                .mapToObj(i -> Randoms.pickUniqueNumbersInRange(1, 45, 6))
                .map(Lotto::new)
                .toList();
    }

    public static void validateAmount(int amount) {
        if (amount % PRICE_PER_TICKET != 0) {
            throw new IllegalArgumentException("구입 금액은 %,d원 단위여야 합니다.".formatted(PRICE_PER_TICKET));
        }
    }
}