package lotto.domain.data

enum class PrizeCondition(matchingCount: Int, bonusNumberMatch: Boolean, prize: Int) {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    FAILURE(0, false, 0);
}