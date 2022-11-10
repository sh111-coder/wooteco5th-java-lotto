package lotto.service;

import lotto.LottoNumbersGenerator;
import lotto.LottoWinningRank;
import lotto.model.Lotto;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGameService {

    private static final int A_LOTTO_PRICE = 1000;
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String LOTTO_PURCHASE_AMOUNT_DIVIDE_REST_EXCEPTION_MESSAGE = "로또 구입 금액은 1,000원으로 나누어 떨어져야합니다.";
    private static final String LOTTO_PURCHASE_AMOUNT_NOT_DIGIT_EXCEPTION_MESSAGE = "로또 구입 금액은 숫자여야합니다.";
    private static final String DIGIT_REGEX = "^[0-9]+$";

    private Lotto lotto;
           
    public void generateLotto() {
        this.lotto = new Lotto(LottoNumbersGenerator.generateLottoNumbers());
    }

    public int getLottoIssueCount(String lottoPurchaseAmount) {
        validateLottoIssueCount(lottoPurchaseAmount);
        return Integer.parseInt(lottoPurchaseAmount) / A_LOTTO_PRICE;
    }

    public void validateLottoIssueCount(String lottoPurchaseAmount) {
        if (isLottoPurchaseAmountNotDigit(lottoPurchaseAmount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + LOTTO_PURCHASE_AMOUNT_NOT_DIGIT_EXCEPTION_MESSAGE);
        }
        if (isRestOfLottoPurchaseAmountDivideBy1000NotZero(lottoPurchaseAmount)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PREFIX + LOTTO_PURCHASE_AMOUNT_DIVIDE_REST_EXCEPTION_MESSAGE);
        }
    }

    private boolean isRestOfLottoPurchaseAmountDivideBy1000NotZero(String lottoPurchaseAmount) {
        return Integer.parseInt(lottoPurchaseAmount) % A_LOTTO_PRICE != 0;
    }

    private boolean isLottoPurchaseAmountNotDigit(String lottoPurchaseAmount) {
        return !Pattern.compile(DIGIT_REGEX).matcher(String.valueOf(lottoPurchaseAmount)).matches();
    }

    public long getPurchaseNumbersMatchWinningNumbersCount(List<Integer> purchaseLottoNumbers, List<Integer> lottoWinningNumbers) {
        return lottoWinningNumbers.stream()
                .filter(purchaseLottoNumbers::contains)
                .count();
    }

    public boolean isPurchaseNumbersMatchBonusNumber(List<Integer> purchaseLottoNumbers, int bonusNumber) {
        return purchaseLottoNumbers.contains(bonusNumber);
    }

    public List<String> inputWinningNumberConvertToCollection(String lottoWinningNumber) {
        return Stream.of(lottoWinningNumber.split(","))
                .collect(Collectors.toList());
    }

    public LottoWinningRank decideWinningRank(List<Integer> purchaseLottoNumbers,
                                              List<Integer> lottoWinningNumbers,
                                              int bonusNumber) {
        long purchaseNumbersMatchWinningNumbersCount =
                getPurchaseNumbersMatchWinningNumbersCount(purchaseLottoNumbers, lottoWinningNumbers);
        return LottoWinningRank.decideLottoWinningRank(purchaseNumbersMatchWinningNumbersCount,
                isPurchaseNumbersMatchBonusNumber(purchaseLottoNumbers, bonusNumber));
    }
}
