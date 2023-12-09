package lotto.domain

import camp.nextstep.edu.missionutils.Randoms
import lotto.domain.data.Lotteries

class LottoNumberGenerator {

    fun generatedLottoes(lottoTicketCount: Int): Lotteries {
        val lottoes = List(lottoTicketCount) { generated() }
        return Lotteries(lottoes)
    }

    private fun generated(): Lotto {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6).sorted()
        return Lotto(numbers)
    }
}