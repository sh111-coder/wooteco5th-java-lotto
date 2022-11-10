package lotto;

import java.util.Arrays;
import java.util.List;

public enum LottoWinningRank {
    NO_RANK(0, List.of(1, 2, 3), false, "0"),
    FIRST(1, List.of(6), false, "2,000,000,000"),
    SECOND(2, List.of(5), true, "30,000,000"),
    THIRD(3, List.of(5), false, "1,500,000"),
    FOURTH(4, List.of(4), false, "50,000"),
    FIFTH(5, List.of(3), false, "5,000");

    private final int rank;
    private final List<Integer> purchaseNumbersMatchWinningNumbersCounts;
    private final boolean purchaseNumbersMatchBonusNumber;
    private final String winningMoney;

    LottoWinningRank(int rank, List<Integer> purchaseNumbersMatchWinningNumbersCount,
                     boolean purchaseNumbersMatchBonusNumber, String winningMoney) {
        this.rank = rank;
        this.purchaseNumbersMatchWinningNumbersCounts = purchaseNumbersMatchWinningNumbersCount;
        this.purchaseNumbersMatchBonusNumber = purchaseNumbersMatchBonusNumber;
        this.winningMoney = winningMoney;
    }

    public int getRank() {
        return rank;
    }

    public List<Integer> getPurchaseNumbersMatchWinningNumbersCounts() {
        return purchaseNumbersMatchWinningNumbersCounts;
    }

    public boolean isPurchaseNumbersMatchBonusNumber() {
        return purchaseNumbersMatchBonusNumber;
    }

    public String getWinningMoney() {
        return winningMoney;
    }
}
