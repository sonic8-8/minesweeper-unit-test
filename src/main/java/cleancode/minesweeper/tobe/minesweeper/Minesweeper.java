package cleancode.minesweeper.tobe.minesweeper;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.LandMinePositionSelector;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutputHandler;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

public class Minesweeper implements GameRunnable, GameInitializable {

    private final GameBoard gameBoard;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public Minesweeper(GameConfig gameConfig, LandMinePositionSelector landMinePositionSelector) {
        gameBoard = new GameBoard(gameConfig.getGameLevel(), landMinePositionSelector);
        this.inputHandler = gameConfig.getInputHandler();
        this.outputHandler = gameConfig.getOutputHandler();
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame();
    }

    @Override
    public void run() {
        outputHandler.showGameStartComments();

        while (gameBoard.isInProgress()) {
            try {
                outputHandler.showBoard(gameBoard);

                CellPosition cellPosition = getCellInputFromUser();
                UserAction userActionInput = getUserActionInputFromUser();
                actOnCell(cellPosition, userActionInput);
            } catch (GameException e) {
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.showSimpleMessage("프로그램에 문제가 발생했습니다.");
            }

            outputHandler.showBoard(gameBoard);

            if (gameBoard.isWinStatus()) {
                outputHandler.showGameWinningComment();
                break;
            }
            if (gameBoard.isLoseStatus()) {
                outputHandler.showGameLosingComment();
                break;
            }
        }
    }

    private CellPosition getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다.");
        }

        return cellPosition;
    }

    private UserAction getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }

    private void actOnCell(CellPosition cellPosition, UserAction userActionInput) {
        if (doesUserChooseToPlantFlag(userActionInput)) {
            gameBoard.flagAt(cellPosition);
            return;
        }

        if (doesUserChooseToOpenCell(userActionInput)) {
            gameBoard.openAt(cellPosition);
            return;
        }

        throw new GameException("잘못된 번호를 선택하셨습니다.");
    }

    private boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }

    private boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;
    }
}
