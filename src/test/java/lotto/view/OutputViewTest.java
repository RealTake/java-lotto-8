package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;

class OutputViewTest {

    @Test
    void 출력형식_테스트() {
        Result result = new Result(
                List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))),
                new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7)
        );

        assertThat(result.getStatistics()).containsKeys(LottoRank.FIRST);
    }
}