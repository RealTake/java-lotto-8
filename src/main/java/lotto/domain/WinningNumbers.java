package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {
    private final Set<Integer> numbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        LottoRule.validate(numbers, bonusNumber);
        this.numbers = Collections.unmodifiableSet(new HashSet<>(numbers));
        this.bonusNumber = bonusNumber;
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}