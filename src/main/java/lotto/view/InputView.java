package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_LOTTO_PURCHASE_AMOUNT_MESSAGE = "당첨 번호를 입력해 주세요.";

    public String inputLottoPurchaseAmount() {
        System.out.println(INPUT_LOTTO_PURCHASE_AMOUNT_MESSAGE);
        return Console.readLine();
    }
}