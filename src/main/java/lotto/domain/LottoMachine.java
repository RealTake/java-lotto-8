package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 로또를 만들어 발급하는 클래스
 */
public class LottoMachine {
    public final static int LOTTO_NUMBER_COUNT = 6;
    public final static int LOTTO_NUMBER_MIN = 1;
    public final static int LOTTO_NUMBER_MAX = 45;
    public final static int LOTTO_TICKET_PRICE = 1000;

    public static List<Lotto> issue(int amount) {
        validateAmount(amount);
        final int ticketCount = amount / LOTTO_TICKET_PRICE;

        return IntStream.range(0, ticketCount)
                .mapToObj(i -> Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT))
                .map(Lotto::new)
                .toList();
    }

    public static void validateAmount(int amount) {
        if (amount % LOTTO_TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 %,d원 단위여야 합니다.".formatted(LOTTO_TICKET_PRICE));
        }
    }
}