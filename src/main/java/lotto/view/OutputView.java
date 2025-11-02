package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Result;

public class OutputView extends WoowaCourseConsoleView {
    private static final String MESSAGE_OUTPUT_PURCHASE_LOTTO_COUNT = "%d개를 구매했습니다.";
    private static final String MESSAGE_OUTPUT_RESULT_TITLE = "당첨 통계\n---";
    private static final String MESSAGE_OUTPUT_PROFIT_RATE = "총 수익률은 %.1f%%입니다.";

    public static void printPurchasedLottos(List<Lotto> lottos) {
        println(MESSAGE_OUTPUT_PURCHASE_LOTTO_COUNT.formatted(lottos.size()));
        lottos.forEach(System.out::println);
    }

    public static void printResult(Result result) {
        println(MESSAGE_OUTPUT_RESULT_TITLE);

        println("3개 일치 (5,000원) - %d개".formatted(result.getCountByRank(LottoRank.FIFTH)));
        println("4개 일치 (50,000원) - %d개".formatted(result.getCountByRank(LottoRank.FOURTH)));
        println("5개 일치 (1,500,000원) - %d개".formatted(result.getCountByRank(LottoRank.THIRD)));
        println("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개".formatted(result.getCountByRank(LottoRank.SECOND)));
        println("6개 일치 (2,000,000,000원) - %d개".formatted(result.getCountByRank(LottoRank.FIRST)));

        println(MESSAGE_OUTPUT_PROFIT_RATE.formatted(result.getProfitRate()));
    }
}