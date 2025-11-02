package lotto.domain;

import static lotto.domain.LottoMachine.LOTTO_NUMBER_COUNT;
import static lotto.domain.LottoMachine.LOTTO_NUMBER_MAX;
import static lotto.domain.LottoMachine.LOTTO_NUMBER_MIN;

import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 %d개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }
        if (numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        if (numbers.stream().anyMatch(n -> n < LOTTO_NUMBER_MIN || n > LOTTO_NUMBER_MAX)) {
            throw new IllegalArgumentException("로또 번호는 %d~%d 사이여야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    public int countMatchingNumbers(Set<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}