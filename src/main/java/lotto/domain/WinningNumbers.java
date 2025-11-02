package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {
    private final static int LOTTO_MAX_NUMBER_COUNT = 6;

    private final Set<Integer> numbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        validate(numbers, bonusNumber);
        this.numbers = Collections.unmodifiableSet(new HashSet<>(numbers));
        this.bonusNumber = bonusNumber;
    }

    public static void validate(List<Integer> numbers, int bonusNumber) {
        validateNumbers(numbers);
        validateBonusNumber(numbers, bonusNumber);
    }

    public static void validateNumbers(List<Integer> numbers) {
        if (numbers.size() != LOTTO_MAX_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 중복되지 않은 %d개여야 합니다.".formatted(numbers.size()));
        }
        if (numbers.stream().anyMatch(n -> n < 1 || n > 45)) {
            throw new IllegalArgumentException("번호는 1~45 사이여야 합니다.");
        }
    }

    public static void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("보너스 번호는 1~45 사이여야 합니다.");
        }
    }


    public Set<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}