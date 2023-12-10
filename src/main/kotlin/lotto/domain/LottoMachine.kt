package lotto.domain

import camp.nextstep.edu.missionutils.Randoms
import lotto.domain.data.Lotteries

class LottoMachine {

    fun generateLottoes(lottoTicketCount: Int): Lotteries {
        val lottoes = List(lottoTicketCount) { generateLotto() }
        return Lotteries(lottoes)
    }

    private fun generateLotto(): Lotto {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6).sorted()
        return Lotto(numbers)
    }
}