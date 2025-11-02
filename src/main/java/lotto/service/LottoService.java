package lotto.service;

import static lotto.domain.LottoRule.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoRule.LOTTO_NUMBER_MAX;
import static lotto.domain.LottoRule.LOTTO_NUMBER_MIN;
import static lotto.domain.LottoRule.LOTTO_TICKET_PRICE;
import static lotto.domain.LottoRule.validateTicketAmount;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.Lotto;

/**
 * 로또를 만들어 발급하는 클래스
 */
public class LottoService {

    public static List<Lotto> issueTickets(int amount) {
        validateTicketAmount(amount);
        final int ticketCount = amount / LOTTO_TICKET_PRICE;

        return IntStream.range(0, ticketCount)
                .mapToObj(i -> Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX, LOTTO_NUMBER_COUNT))
                .map(Lotto::new)
                .toList();
    }
}