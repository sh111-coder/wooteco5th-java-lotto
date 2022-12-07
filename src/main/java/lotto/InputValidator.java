package lotto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidator {

    private static final String DIGIT_REGEX = "[0-9]+";
    private static final String LOTTO_WINNING_NUMBERS_SPLIT_DELIMITER = ",";
    private static final String LOTTO_WINNING_NUMBERS_REGEX = "([0-9]+,?)+";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_WINNING_NUMBERS_SIZE = 6;

    public void validateLottoPurchaseMoney(String inputLottoPurchaseMoney) {
        if (isInputLottoPurchaseMoneyNotDigit(inputLottoPurchaseMoney)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "로또 구입 금액은 숫자여야합니다.");
        }
        if (cannotDivide1000(inputLottoPurchaseMoney)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "로또 구입 금액은 1000원으로 나누어 떨어져야합니다.");
        }
    }

    private boolean isInputLottoPurchaseMoneyNotDigit(String inputLottoPurchaseMoney) {
        return !Pattern.matches(DIGIT_REGEX, inputLottoPurchaseMoney);
    }

    private boolean cannotDivide1000(String inputLottoPurchaseMoney) {
        int lottoPurchaseMoney = Integer.parseInt(inputLottoPurchaseMoney);
        return lottoPurchaseMoney % 1000 != 0;
    }

    public void validateLottoWinningNumbers(String inputLottoWinningNumbers) {
        if (isNumbersDelimiterNotComma(inputLottoWinningNumbers)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "로또 번호는 쉼표로 구분된 숫자여야합니다.");
        }
        if (isNumbersWrongRange(inputLottoWinningNumbers)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "로또 번호는 1~45 사이의 숫자여야합니다.");
        }
        if (isNumbersSizeNotSix(inputLottoWinningNumbers)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "로또 번호는 6개여야합니다.");
        }
        if (isNumbersDuplicate(inputLottoWinningNumbers)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "당첨 번호 숫자는 중복될 수 없습니다.");
        }
    }

    private boolean isNumbersDelimiterNotComma(String inputLottoWinningNumbers) {
        return !Pattern.compile(LOTTO_WINNING_NUMBERS_REGEX).matcher(inputLottoWinningNumbers).matches();
    }

    private boolean isNumbersWrongRange(String inputLottoWinningNumbers) {
        List<Integer> splitNumbers = splitLottoWinningNumbers(inputLottoWinningNumbers);
        long wrongRangeNumberCount = splitNumbers.stream()
                .filter(number -> number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER)
                .count();
        return wrongRangeNumberCount != 0;
    }

    private boolean isNumbersSizeNotSix(String inputLottoWinningNumbers) {
        List<Integer> splitNumbers = splitLottoWinningNumbers(inputLottoWinningNumbers);
        return splitNumbers.size() != LOTTO_WINNING_NUMBERS_SIZE;
    }

    private boolean isNumbersDuplicate(String inputLottoWinningNumbers) {
        Set<Integer> convertedLottoWinningNumbers = new HashSet<>(splitLottoWinningNumbers(inputLottoWinningNumbers));
        return convertedLottoWinningNumbers.size() != LOTTO_WINNING_NUMBERS_SIZE;
    }

    private List<Integer> splitLottoWinningNumbers(String lottoWinningNumbers) {
        return Arrays.stream(lottoWinningNumbers.split(LOTTO_WINNING_NUMBERS_SPLIT_DELIMITER))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public void validateBonusNumber(String inputBonusNumber) {
        if (isBonusNumberNotDigit(inputBonusNumber)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "보너스 번호는 숫자여야합니다.");
        }
        if (isBonusNumberWrongRange(inputBonusNumber)) {
            throw new IllegalArgumentException(ErrorConstants.ERROR_PREFIX + "보너스 번호는 1~45 사이의 숫자여야합니다.");
        }
    }

    private boolean isBonusNumberNotDigit(String inputBonusNumber) {
        return !Pattern.matches(DIGIT_REGEX, inputBonusNumber);
    }

    private boolean isBonusNumberWrongRange(String inputBonusNumber) {
        int bonusNumber = Integer.parseInt(inputBonusNumber);
        return bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER;
    }
}