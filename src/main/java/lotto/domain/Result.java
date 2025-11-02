package lotto.domain;

import java.util.EnumMap;
import java.util.List;

public class Result {
    private static final int LOTTO_PRICE = 1_000;
    private final EnumMap<Rank, Integer> statistics = new EnumMap<>(Rank.class);

    private final double profitRate;

    public Result(List<Lotto> lottos, WinningNumbers winningNumbers) {
        for (Rank rank : Rank.values()) {
            statistics.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatchingNumbers(winningNumbers.getNumbers());
            boolean bonusMatch = lotto.contains(winningNumbers.getBonusNumber());
            Rank rank = Rank.valueOf(matchCount, bonusMatch);
            statistics.put(rank, statistics.get(rank) + 1);
        }

        this.profitRate = calculateProfitRate(lottos.size() * LOTTO_PRICE);
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

    public EnumMap<Rank, Integer> getStatistics() {
        return new EnumMap<>(statistics);
    }

    public int getCountByRank(Rank rank) {
        return statistics.get(rank);
    }

    public double getProfitRate() {
        return profitRate;
    }
}