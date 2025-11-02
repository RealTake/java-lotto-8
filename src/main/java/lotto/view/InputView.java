package lotto.view;

import java.util.Arrays;
import java.util.List;
import lotto.domain.LottoMachine;
import lotto.domain.WinningNumbers;

public class InputView extends WoowaCourseConsoleView {
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
            LottoMachine.validateAmount(purchaseAmount);
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

                    WinningNumbers.validate(winningNumber, 0);
                }
        );

        return winningNumber;
    }

    public static int inputBonusNumber() {
        retry(() -> {
            println(MESSAGE_BONUS_NUMBER);
            bonusNumber = Integer.parseInt(input());

            WinningNumbers.validate(winningNumber, 0);
        });

        return bonusNumber;
    }
}
