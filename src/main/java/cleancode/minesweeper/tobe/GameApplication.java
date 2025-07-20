package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.minesweeper.board.position.LandMinePositionSelector;
import cleancode.minesweeper.tobe.minesweeper.board.position.RandomLandMinePositionSelector;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.VeryBeginner;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig(
                new Advanced(),
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()
        );
        LandMinePositionSelector landMinePositionSelector = new RandomLandMinePositionSelector();

        Minesweeper minesweeper = new Minesweeper(gameConfig, landMinePositionSelector);
        minesweeper.initialize();
        minesweeper.run();
    }
}
