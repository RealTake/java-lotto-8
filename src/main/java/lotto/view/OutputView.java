package lotto.view;

import static lotto.domain.Rank.FIFTH;
import static lotto.domain.Rank.FIRST;
import static lotto.domain.Rank.FOURTH;
import static lotto.domain.Rank.SECOND;
import static lotto.domain.Rank.THIRD;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.Result;

public class OutputView extends WoowaCourseConsoleView {
    private static final String MESSAGE_OUTPUT_PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다.";
    private static final String MESSAGE_OUTPUT_LOTTO_RESULT = "%d개 일치 (%,d원) - %d개";
    private static final String MESSAGE_OUTPUT_LOTTO_BONUS_RESULT = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개";
    private static final String MESSAGE_OUTPUT_RESULT_TITLE = "당첨 통계\n---";
    private static final String MESSAGE_OUTPUT_PROFIT_RATE = "총 수익률은 %.1f%%입니다.";

    private static final List<Rank> SHOWED_RANKS = List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);

    public static void printPurchasedLottos(List<Lotto> lottos) {
        println(MESSAGE_OUTPUT_PURCHASE_LOTTO_COUNT.formatted(lottos.size()));
        lottos.forEach(System.out::println);
    }

    public static void printResult(Result result) {
        println(MESSAGE_OUTPUT_RESULT_TITLE);

        for (Rank rank : SHOWED_RANKS) {
            int matchCount = rank.getMatchCount();
            int prize = rank.getPrize();
            int matchRankCount = result.getCountByRank(rank);

            if (rank.isBonus()) {
                println(MESSAGE_OUTPUT_LOTTO_BONUS_RESULT.formatted(matchCount, prize, matchRankCount));
            } else {
                println(MESSAGE_OUTPUT_LOTTO_RESULT.formatted(matchCount, prize, matchRankCount));
            }
        }

        println(MESSAGE_OUTPUT_PROFIT_RATE.formatted(result.getProfitRate()));
    }
}