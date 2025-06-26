package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {

    @DisplayName("사용자 입력값으로 좌표를 생성한다.")
    @Test
    void of() {
        // given
        int rowIndex = 0;
        int colIndex = 0;

        // when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);

        // then
        assertThat(cellPosition)
                .extracting("rowIndex", "colIndex")
                .containsExactlyInAnyOrder(0, 0);
    }

    @DisplayName("사용자 입력값이 0 미만일 경우, 예외가 발생한다.")
    @Test
    void ofWithNegative() {
        // given
        int rowIndex = -1;
        int colIndex = -1;

        // when // then
        assertThatThrownBy(() -> CellPosition.of(rowIndex, colIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

    @DisplayName("생성된 좌표의 Row 인덱스가 게임 보드 행 크기(rowSize) 이상일 경우, InvalidCellPosition 이다.")
    @Test
    void isRowIndexMoreThanOrEqual() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexMoreThanOrEqual(0);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("생성된 좌표의 Row 인덱스가 게임 보드 열 크기(RowSize) 안에 들어올 경우, InvalidCellPosition 이 아니다.")
    @Test
    void isRowIndexMoreThanOrEqual_withinBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexMoreThanOrEqual(1);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("생성된 좌표의 Col 인덱스가 게임 보드 열 크기(colSize) 이상일 경우, InvalidCellPosition 이다.")
    @Test
    void isColIndexMoreThanOrEqual() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexMoreThanOrEqual(0);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("생성된 좌표의 Col 인덱스가 게임 보드 열 크기(ColSize) 안에 들어올 경우, InvalidCellPosition 이 아니다.")
    @Test
    void isColIndexMoreThanOrEqual_withinBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexMoreThanOrEqual(1);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("계산된 좌표의 Row 인덱스가 게임 보드 행 크기(RowSize) 보다 작을 경우, 유효한 위치다.")
    @Test
    void isRowIndexLessThan() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexLessThan(1);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("계산된 좌표의 Row 인덱스가 게임 보드 행 크기(RowSize) 이상일 경우, 유효하지 않은 위치다.")
    @Test
    void isRowIndexLessThan_outOfBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexLessThan(0);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("계산된 좌표의 Col 인덱스가 게임 보드 열 크기(ColSize) 보다 작을 경우, 유효한 위치다.")
    @Test
    void isColIndexLessThan() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexLessThan(1);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("계산된 좌표의 Col 인덱스가 게임 보드 열 크기(ColSize) 이상일 경우, 유효하지 않은 위치다.")
    @Test
    void isColIndexLessThan_outOfBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexLessThan(0);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("기준 좌표에 상대 좌표를 더한 계산한 결과가 0 이상일 경우, 움직일 수 있는 좌표다.")
    @Test
    void canCalculatePositionBy() {
        // given
        RelativePosition relativePosition = RelativePosition.of(0, 1);
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean canCalculate = cellPosition.canCalculatePositionBy(relativePosition);

        // then
        assertThat(canCalculate).isTrue();
    }

    @DisplayName("기준 좌표에 상대 좌표를 더한 계산한 결과가 0 미만일 경우, 움직일 수 없는 좌표다.")
    @Test
    void canCalculatePositionBy2() {
        // given
        RelativePosition relativePosition = RelativePosition.of(-1, 0);
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean canCalculate = cellPosition.canCalculatePositionBy(relativePosition);

        // then
        assertThat(canCalculate).isFalse();
    }

    @DisplayName("움직일 수 있는 좌표일 경우, 계산된 위치에 새로운 좌표를 생성한다.")
    @Test
    void calculatePositionBy() {
        // given
        RelativePosition relativePosition = RelativePosition.of(1, 0);
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        CellPosition calculatedPosition = cellPosition.calculatePositionBy(relativePosition);

        // then
        assertThat(calculatedPosition)
                .extracting("rowIndex", "colIndex")
                .containsExactlyInAnyOrder(1, 0);
    }

    @DisplayName("움직일 수 없는 좌표일 경우, 예외가 발생한다.")
    @Test
    void calculatePositionBy2() {
        // given
        RelativePosition relativePosition = RelativePosition.of(-1, 0);
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when // then
        assertThatThrownBy(() -> cellPosition.calculatePositionBy(relativePosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 좌표가 아닙니다.");
    }

}
