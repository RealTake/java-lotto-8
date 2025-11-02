package lotto.domain;

import static lotto.domain.LottoRule.LOTTO_TICKET_PRICE;

import java.util.EnumMap;
import java.util.List;

public class Result {
    private final EnumMap<LottoRank, Integer> statistics = new EnumMap<>(LottoRank.class);

    private final double profitRate;

    public Result(List<Lotto> lottos, WinningNumbers winningNumbers) {
        for (LottoRank rank : LottoRank.values()) {
            statistics.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatchingNumbers(winningNumbers.getNumbers());
            boolean bonusMatch = lotto.contains(winningNumbers.getBonusNumber());
            LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);
            statistics.put(rank, statistics.get(rank) + 1);
        }

        this.profitRate = calculateProfitRate(lottos.size() * LOTTO_TICKET_PRICE);
    }

    /**
     * 수익률 계산
     */
    private double calculateProfitRate(int totalSpent) {
        long totalPrize = statistics.entrySet()
                .parallelStream()
                .mapToLong(e -> (long) e.getKey().getPrize() * e.getValue())
                .sum();
        return Math.round(((double) totalPrize / totalSpent) * 1000) / 10.0;
    }

    public EnumMap<LottoRank, Integer> getStatistics() {
        return new EnumMap<>(statistics);
    }

    public int getCountByRank(LottoRank rank) {
        return statistics.get(rank);
    }

    public double getProfitRate() {
        return profitRate;
    }
}