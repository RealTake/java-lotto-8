package lotto.domain;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;   // 당첨에 필요한 당첨번호 수
    private final boolean bonus;    // 당첨에 보너스 번호 필요 여부
    private final int prize;        // 당첨 상금

    LottoRank(int matchCount, boolean bonus, int prize) {
        this.matchCount = matchCount;
        this.bonus = bonus;
        this.prize = prize;
    }

    public static LottoRank valueOf(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(r -> r.matchCount == matchCount && (!r.bonus || bonusMatch == r.bonus))
                .findFirst()
                .orElse(NONE);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonus() {
        return bonus;
    }
}