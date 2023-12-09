package lotto.domain.data

import lotto.domain.Lotto

data class WinningLotto(val lotto: Lotto, val bonusNumber: BonusNumber) {
    init {
        require(!lotto.hasBonusNumber(bonusNumber)) { "당첨 번호와 보너스 번호는 같으면 안 됩니다." }
    }
}