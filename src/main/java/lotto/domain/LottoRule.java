package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class LottoRule {

    public final static int LOTTO_NUMBER_COUNT = 6;
    public final static int LOTTO_NUMBER_MIN = 1;
    public final static int LOTTO_NUMBER_MAX = 45;
    public final static int LOTTO_TICKET_PRICE = 1000;

    private LottoRule() {
        // 유틸 클래스이므로 인스턴스화 방지
    }

    public static void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateNumbersRange(numbers);
        validateDuplicate(numbers);
    }

    public static void validate(List<Integer> numbers, int bonusNumber) {
        validate(numbers);
        validateNumberRange(bonusNumber);
        validateDuplicateBonusNumber(numbers, bonusNumber);
    }

    public static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 총 %d개여야 합니다.".formatted(LOTTO_NUMBER_COUNT));
        }
    }

    public static void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(LottoRule::validateNumberRange);
    }

    public static void validateNumberRange(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("번호는 %d~%d 범위에 있어야합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
    }

    public static void validateDuplicate(List<Integer> numbers) {
        final Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("번호가 중복되어있습니다.");
        }
    }

    public static void validateDuplicateBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 중복된 번호입니다.");
        }
    }

    public static void validateTicketAmount(int tickAmount) {
        if (tickAmount % LOTTO_TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 %,d원 단위여야 합니다.".formatted(LOTTO_TICKET_PRICE));
        }
    }
}