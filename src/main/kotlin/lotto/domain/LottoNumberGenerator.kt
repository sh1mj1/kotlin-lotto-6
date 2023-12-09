package lotto.domain

import camp.nextstep.edu.missionutils.Randoms
import lotto.domain.data.Lotto

class LottoNumberGenerator {
    fun generated(): Lotto {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6).sorted()
        return Lotto(numbers)
    }
}