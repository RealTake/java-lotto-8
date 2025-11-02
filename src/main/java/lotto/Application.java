package lotto;

import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        try {
            final LottoController lottoController = new LottoController();
            lottoController.run();
        } catch (Exception e) {
            System.out.printf("[ERROR] %s%n", e.getMessage());
        }
    }
}