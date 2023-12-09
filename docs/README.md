# 핵심 기능

내가 구매한 로또와 당첨 번호, 보너스 번호를 비교해서 당첨금을 구한다.

## 비즈니스 로직

* `Lotto`
    * `fun countMatchingNumber(lotto: Lotto): Int`
        * 당첨 번호와 비교해서 번호 일치 개수를 구한다.
    * 당첨 번호와 비교해서 보너스 번호가 일치하는지 구한다.
        * `fun hasBonusNumber(bonusNumber: BonusNumber): Boolean`

* `PrizeCalculator`
    * 로또 하나의 당첨금을 계산한다
    * `calculatePrize(lotto: Lotto, winningLotto: WinningLotto): Prize`

* `TotalPrizeService`
    * 총 당첨금을 계산한다.
    * `fun calculateTotalPrizes(lotteries: Lotteries, winningLotto: WinningLotto): Map<Prize, Int>`

* `LottoSeller`x
    * 내가 로또(들)를 구매한다.
    * `fun buyLottoTicket(payment: Int): Int`
    * 유효성 검증
        * `payment` 는 양수
        * `payment` 는 1,000 원 단위여야 함

* `LottoNumberGenerator`
    * 랜덤으로 여러 로또를 만들어준다. `generatedLottoes`
    * 랜덤으로 로또 번호를 만들어준다.
    * `generated(): Lotto`

* `ProfitRate`
    * 총 수익률을 계산한다.
    * `fun calculatedProfitRate(totalPrizes: Map<Prize, Int>, payment: Int): Float`

## 데이터

* `Lotto` 로또
    * 생성자: `numbers: List<Int>`
    * 유효성 검증
        * 로또 번호의 개수는 6개.
        * 로또에 중복 숫자 없어야 함.
        * 각 로또 번호는 1~45 사이.

* `BonusNumber` 보너스 번호
    * 생성자 `number: Int`
    * 보너스 번호를 생성할 때 유효성 검증
        * 보너스 번호는 1~45 사이여야 함.

* 내가 구매한 로또들 (`Lotteries`)
    * 생성자: `lottoes: List<Lotto>`

* `WinningLotto` 당첨 로또
    * 생성자: `Lotto`(로또 당첨 번호), `BonusNumber`(보너스 번호)
        * 유효성 검증
            * Lotto 가 BonusNumber 와 같은 번호를 가지고 있지 않아야 함. 
