package lotto.view;

import java.util.Arrays;
import java.util.List;

public class InputView extends WoowaCourseConsoleView {
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;
    public static final int LOTTO_TICKET_PRICE = 1000;

    private static final String MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_WINNING_NUMBERS = "당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    private static int purchaseAmount;
    private static List<Integer> winningNumber;
    private static int bonusNumber;

    public static int inputPurchaseAmount() {
        retry(() -> {
            println(MESSAGE_PURCHASE_AMOUNT);
            purchaseAmount = Integer.parseInt(input());
            validateAmount(purchaseAmount);
        });

        return purchaseAmount;
    }

    public static List<Integer> inputWinningNumbers() {
        retry(() -> {
                    println(MESSAGE_WINNING_NUMBERS);
                    winningNumber = Arrays.stream(input().split(","))
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .toList();

                    validateNumbers(winningNumber);
                }
        );

        return winningNumber;
    }

    public static int inputBonusNumber() {
        retry(() -> {
            println(MESSAGE_BONUS_NUMBER);
            bonusNumber = Integer.parseInt(input());

            validateBonusNumber(winningNumber, bonusNumber);
        });

        return bonusNumber;
    }

    public static void validateNumbers(List<Integer> numbers) {
        numbers.forEach(InputView::validateNumberRange);

        if (numbers.size() != LOTTO_NUMBER_COUNT || numbers.stream().distinct().count() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 중복되지 않은 %d개여야 합니다.".formatted(numbers.size()));
        }
    }

    private static void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        validateNumberRange(bonusNumber);

        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private static void validateNumberRange(int number) {
        if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
            throw new IllegalArgumentException("가 번호는 %d~%d에 해당 해야 합니다.".formatted(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX));
        }
    }

    private static void validateAmount(int amount) {
        if (amount % LOTTO_TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 %,d원 단위여야 합니다.".formatted(LOTTO_TICKET_PRICE));
        }
    }
}
