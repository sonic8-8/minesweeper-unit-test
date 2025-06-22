package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyCellTest {

    @DisplayName("사용자가 빈 셀을 열면, EMPTY 상태의 스냅샷을 반환한다.")
    @Test
    void getSnapshotWithOpenedEmptyCell() {
        // given
        EmptyCell emptyCell = new EmptyCell();
        emptyCell.open();

        // when
        CellSnapshot snapshot = emptyCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.EMPTY, 0);
    }

    @DisplayName("사용자가 빈 셀에 깃발을 꽂으면, FLAG 상태의 스냅샷을 반환한다.")
    @Test
    void getSnapshotWithFlaggedEmptyCell() {
        // given
        EmptyCell emptyCell = new EmptyCell();
        emptyCell.flag();

        // when
        CellSnapshot snapshot = emptyCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.FLAG, 0);
    }

    @DisplayName("셀을 열지 않은 경우, 스냅샷의 상태는 UNCHECKED이다.")
    @Test
    void getSnapshotWithUncheckedEmptyCell() {
        // given
        EmptyCell emptyCell = new EmptyCell();

        // when
        CellSnapshot snapshot = emptyCell.getSnapshot();

        // then
        assertThat(snapshot)
                .extracting("status", "nearbyLandMineCount")
                .containsExactlyInAnyOrder(CellSnapshotStatus.UNCHECKED, 0);
    }
}
