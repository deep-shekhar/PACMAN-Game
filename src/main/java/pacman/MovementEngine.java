
import java.util.ArrayList;
import java.util.List;

public class MovementEngine {
    private Player pacMan;
    private NodeType[][] maze;
    private List<Player> ghosts = new ArrayList<Player>();
    
    public MovementEngine(Player pacMan, NodeType[][] maze, List<Player> ghosts) {
        this.maze = maze;
        this.pacMan = pacMan;
        this.ghosts = ghosts;
    }

    public void left(Player player) {
        if (player.getPlayerType() == Player.PlayerType.PACMAN) {
            leftPacMan(player, maze);
        } else if (player.getPlayerType() == Player.PlayerType.GHOST) {
            leftGhost(player, maze);
        }
    }

    private void leftPacMan(Player player, NodeType[][] maze) {
        int column = player.getCurrentColumn();
        int row = player.getCurrentRow();
        if ((column - 1) >= 0) {
            if (maze[row][column - 1] == NodeType.DOT) {
                pacMan.setNumDotsEaten(pacMan.getNumDotsEaten() + 1);
                maze[row][column] = NodeType.BLANK;
                column--;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row][column - 1] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                column--;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row][column - 1] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.BLANK;
                column--;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row][column - 1] == NodeType.GHOST) {
            	column--;
            	maze[row][column] = NodeType.BLANK;
            	if(!(PacManGame.totalScore>=270 && PacManGame.totalScore<=620)){
            		pacMan.setDead(true);
            	}
            } else if (maze[row][column - 1] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(PacmanAI.getGhostNextPosition(pacMan, ghosts, maze));
    }

    private void leftGhost(Player player, NodeType[][] maze) {
        int column = player.getCurrentColumn();
        int row = player.getCurrentRow();
        if ((column - 1) >= 0) {
            if (maze[row][column - 1] == NodeType.DOT) {
                maze[row][column] = NodeType.DOT;
                column--;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row][column - 1] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                column--;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row][column - 1] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.POWER_UP;
                column--;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row][column - 1] == NodeType.PACMAN) {
            	column--;
            	maze[row][column] = NodeType.BLANK;
            	if((PacManGame.totalScore>=270 && PacManGame.totalScore<=620)){
            		player.setCurrentRow(maze.length-1);
            		player.setCurrentColumn(14);
            		player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
            		return;
            	}
            	else{
            		pacMan.setDead(true);
            	}
            } else if (maze[row][column - 1] == NodeType.GHOST) {
            } else if (maze[row][column - 1] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
    }

    public void right(Player player) {
        if (player.getPlayerType() == Player.PlayerType.PACMAN) {
            rightPacMan(player, maze);
        } else if (player.getPlayerType() == Player.PlayerType.GHOST) {
            rightGhost(player, maze);
        }
    }

    private void rightPacMan(Player player, NodeType[][] maze) {
        int column = player.getCurrentColumn();
        int row = player.getCurrentRow();
        if ((column + 1) <= (maze[row].length - 1)) {
            if (maze[row][column + 1] == NodeType.DOT) {
                pacMan.setNumDotsEaten(pacMan.getNumDotsEaten() + 1);
                maze[row][column] = NodeType.BLANK;
                column++;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row][column + 1] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                column++;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row][column + 1] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.BLANK;
                column++;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row][column + 1] == NodeType.GHOST) {
            	column++;
            	maze[row][column] = NodeType.BLANK;
            	if(!(PacManGame.totalScore>=270 && PacManGame.totalScore<=620)){
            		pacMan.setDead(true);
            	}
            } else if (maze[row][column + 1] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(PacmanAI.getGhostNextPosition(pacMan, ghosts, maze));
    }

    private void rightGhost(Player player, NodeType[][] maze) {
        int column = player.getCurrentColumn();
        int row = player.getCurrentRow();
        if ((column + 1) <= (maze[row].length - 1)) {
            if (maze[row][column + 1] == NodeType.DOT) {
                maze[row][column] = NodeType.DOT;
                column++;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row][column + 1] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                column++;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row][column + 1] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.POWER_UP;
                column++;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row][column + 1] == NodeType.PACMAN) {
            	column++;
            	maze[row][column] = NodeType.BLANK;
            	if((PacManGame.totalScore>=270 && PacManGame.totalScore<=620)){
            		player.setCurrentRow(maze.length-1);
            		player.setCurrentColumn(14);
            		player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
            		return;
            	}
            	else{
            		pacMan.setDead(true);
            	}
            } else if (maze[row][column + 1] == NodeType.GHOST) {
            } else if (maze[row][column + 1] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
    }

    public void up(Player player) {
        if (player.getPlayerType() == Player.PlayerType.PACMAN) {
            upPacMan(player, maze);
        } else if (player.getPlayerType() == Player.PlayerType.GHOST) {
            upGhost(player, maze);
        }
    }

    private void upPacMan(Player player, NodeType[][] maze) {
        int row = player.getCurrentRow();
        int column = player.getCurrentColumn();
        if ((row - 1) >= 0) {
            if (maze[row - 1][column] == NodeType.DOT) {
                pacMan.setNumDotsEaten(pacMan.getNumDotsEaten() + 1);
                maze[row][column] = NodeType.BLANK;
                row--;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row - 1][column] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                row--;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row - 1][column] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.BLANK;
                row--;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row - 1][column] == NodeType.GHOST) {
            	row--;
            	maze[row][column] = NodeType.BLANK;
            	if(!(PacManGame.totalScore>=270 && PacManGame.totalScore<=620)){
            		pacMan.setDead(true);
            	}
            } else if (maze[row - 1][column] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(PacmanAI.getGhostNextPosition(pacMan, ghosts, maze));
    }

    private void upGhost(Player player, NodeType[][] maze) {
        int row = player.getCurrentRow();
        int column = player.getCurrentColumn();
        if ((row - 1) >= 0) {
            if (maze[row - 1][column] == NodeType.DOT) {
                maze[row][column] = NodeType.DOT;
                row--;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row - 1][column] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                row--;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row - 1][column] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.POWER_UP;
                row--;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row - 1][column] == NodeType.PACMAN) {
            	row--;
            	maze[row][column] = NodeType.BLANK;
            	if((PacManGame.totalScore>=270 && PacManGame.totalScore<=620)){
            		player.setCurrentRow(maze.length-1);
            		player.setCurrentColumn(14);
            		player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
            		return;
            	}
            	else{
            		pacMan.setDead(true);
            	}
            } else if (maze[row - 1][column] == NodeType.GHOST) {
            } else if (maze[row - 1][column] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
    }

    public void down(Player player) {
        if (player.getPlayerType() == Player.PlayerType.PACMAN) {
            downPacMan(player, maze);
        } else if (player.getPlayerType() == Player.PlayerType.GHOST) {
            downGhost(player, maze);
        }
    }

    private void downPacMan(Player player, NodeType[][] maze) {
        int row = player.getCurrentRow();
        int column = player.getCurrentColumn();
        if ((row + 1) <= (maze.length - 1)) {
            if (maze[row + 1][column] == NodeType.DOT) {
                pacMan.setNumDotsEaten(pacMan.getNumDotsEaten() + 1);
                maze[row][column] = NodeType.BLANK;
                row++;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row + 1][column] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                row++;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row + 1][column] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.BLANK;
                row++;
                maze[row][column] = NodeType.PACMAN;
            } else if (maze[row + 1][column] == NodeType.GHOST) {
            	row++;
                maze[row][column] = NodeType.BLANK;
                if(!(PacManGame.totalScore>=270 && PacManGame.totalScore<=620)){
            		pacMan.setDead(true);
            	}
            } else if (maze[row + 1][column] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(PacmanAI.getGhostNextPosition(pacMan, ghosts, maze));
    }

    private void downGhost(Player player, NodeType[][] maze) {
        int row = player.getCurrentRow();
        int column = player.getCurrentColumn();
        if ((row + 1) <= (maze.length - 1)) {
            if (maze[row + 1][column] == NodeType.DOT) {
                maze[row][column] = NodeType.DOT;
                row++;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row + 1][column] == NodeType.BLANK) {
                maze[row][column] = NodeType.BLANK;
                row++;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row + 1][column] == NodeType.POWER_UP) {
                maze[row][column] = NodeType.POWER_UP;
                row++;
                maze[row][column] = NodeType.GHOST;
            } else if (maze[row + 1][column] == NodeType.PACMAN) {
            	row++;
                maze[row][column] = NodeType.BLANK;
                if(PacManGame.totalScore>=270 && PacManGame.totalScore<=620){
            		player.setCurrentColumn(14);
            		player.setCurrentRow(maze.length-1);
            		player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
            		return;
            	}
            	else{
            		pacMan.setDead(true);
            	}
            } else if (maze[row + 1][column] == NodeType.GHOST) {
            } else if (maze[row + 1][column] == NodeType.WALL) {
            }
        }
        player.setCurrentRow(row);
        player.setCurrentColumn(column);
        player.setPosition(GhostAI.getGhostNextPosition(pacMan, player, maze));
    }
}
