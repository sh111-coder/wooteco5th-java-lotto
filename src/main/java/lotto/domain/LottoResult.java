package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private static final int FIRST_RANK_COUNT_KEY = 1;
    private static final int SECOND_RANK_COUNT_KEY = 2;
    private static final int THIRD_RANK_COUNT_KEY = 3;
    private static final int FOURTH_RANK_COUNT_KEY = 4;
    private static final int FIFTH_RANK_COUNT_KEY = 5;

    private final Map<Integer, Integer> lottoRankCountMap = new HashMap<>();
    private int winningAmountSum;

    public LottoResult() {
        initLottoRankCountMap();
    }

    public void generate(List<Lotto> issuedLottos, WinningLotto winningLotto) {
        List<Integer> winningNumbers = winningLotto.getNumbers();
        int bonusNumber = winningLotto.getBonusNumber();
        for (Lotto issueLotto : issuedLottos) {
            LottoRank lottoRank = getLottoRank(winningNumbers, bonusNumber, issueLotto);
            if (lottoRank != LottoRank.NO_RANK) {
                putLottoRankCountMap(lottoRank);
            }
        }
    }

    private void initLottoRankCountMap() {
        lottoRankCountMap.put(FIRST_RANK_COUNT_KEY, 0);
        lottoRankCountMap.put(SECOND_RANK_COUNT_KEY, 0);
        lottoRankCountMap.put(THIRD_RANK_COUNT_KEY, 0);
        lottoRankCountMap.put(FOURTH_RANK_COUNT_KEY, 0);
        lottoRankCountMap.put(FIFTH_RANK_COUNT_KEY, 0);
    }

    private LottoRank getLottoRank(List<Integer> winningNumbers, int bonusNumber, Lotto issueLotto) {
        List<Integer> issuedNumbers = issueLotto.getNumbers();
        long matchNumberCount = issuedNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
        boolean isMatchBonusNumber = issuedNumbers.contains(bonusNumber);
        return LottoRank.getLottoRank(matchNumberCount, isMatchBonusNumber);
    }

    private void putLottoRankCountMap(LottoRank lottoRank) {
        int rank = lottoRank.getRank(lottoRank);
        lottoRankCountMap.put(rank, lottoRankCountMap.get(rank) + 1);
    }

    public Map<Integer, Integer> getLottoRankCountMap() {
        return Collections.unmodifiableMap(lottoRankCountMap);
    }

    public int getWinningAmountSum() {
        return winningAmountSum;
    }
}