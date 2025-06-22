package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberCellTest {

    @DisplayName("사용자가 숫자 셀을 열면, NUMBER 상태의 스냅샷을 반환한다.")
    @Test
    void getSnapshotWithOpenedNumberCell() {
        // given
        NumberCell numberCell = new NumberCell(3);
        numberCell.open();

        // when
        CellSnapshot snapshot = numberCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.NUMBER, 3);
    }

    @DisplayName("사용자가 숫자 셀에 깃발을 꽂으면, FLAG 상태의 스냅샷을 반환한다.")
    @Test
    void getSnapshotWithFlaggedNumberCell() {
        // given
        NumberCell numberCell = new NumberCell(3);
        numberCell.flag();

        // when
        CellSnapshot snapshot = numberCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.FLAG, 0);
    }

    @DisplayName("셀을 열지 않은 경우, 스냅샷의 상태는 UNCHECKED이다.")
    @Test
    void getSnapshotWithUncheckedNumberCell() {
        // given
        NumberCell numberCell = new NumberCell(3);

        // when
        CellSnapshot snapshot = numberCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.UNCHECKED, 0);
    }

}
