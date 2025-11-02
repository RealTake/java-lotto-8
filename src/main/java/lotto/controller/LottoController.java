package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Result;
import lotto.domain.WinningNumbers;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        // 로또 구매 금액 입력
        final int purchaseAmount = InputView.inputPurchaseAmount();

        // 구매한 로또 풀력
        final List<Lotto> purchasedLottos = LottoService.issueTickets(purchaseAmount);
        OutputView.printPurchasedLottos(purchasedLottos);

        // 당첨 번호 입력
        final List<Integer> winningNumbersInput = InputView.inputWinningNumbers();
        final int bonusNumberInput = InputView.inputBonusNumber();
        final WinningNumbers winningNumbers = new WinningNumbers(winningNumbersInput, bonusNumberInput);

        // 당첨 결과 출력
        final Result result = new Result(purchasedLottos, winningNumbers);
        OutputView.printResult(result);
    }
}