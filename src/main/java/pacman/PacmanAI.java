
//import java.util.ArrayList;
import java.util.List;


public class PacmanAI {
    private PacmanAI() {
        // Prevent instantiation.
    }

    public static Player.Position getGhostNextPosition(Player pacMan, List<Player> ghosts,
        NodeType[][] maze) {
        Node startNode = new Node();
        int best_row=pacMan.getCurrentRow(),best_col=pacMan.getCurrentColumn(),mx=1;
        
        for(int i=-1;i<=1;i++){
        	for(int j=-1;j<=1;j++){
        		if((i==0 && j==0)||(Math.abs(i)+Math.abs(j)==2)) continue;
        		
        		int row = pacMan.getCurrentRow()+i, column=pacMan.getCurrentColumn()+j;
        		
        		if(row>0 && column>0 && row<maze.length && column<maze[0].length && maze[row][column] != NodeType.WALL){
        			int dist = 10000000;
        			for (Player ghost : ghosts){
        				if(best_row==ghost.getCurrentRow() && Math.abs(best_col-ghost.getCurrentColumn())<=2){

                    		if(best_row+1<maze.length && maze[best_row+1][best_col]!=NodeType.WALL){
                    			//if(maze[best_row+1][best_col]!=NodeType.DOT && PacManGame.totalScore<)
                    			return Player.Position.DOWN;
                    		}
                    		else if(best_row-1>=0 && maze[best_row-1][best_col]!=NodeType.WALL){
                    			return Player.Position.UP;
                    		}
                    	}
                    	else if(best_col==ghost.getCurrentColumn() && Math.abs(best_row-ghost.getCurrentRow())<=2){
                    		if(best_col+1<maze[0].length && maze[best_row][best_col+1]!=NodeType.WALL){
                    			return Player.Position.RIGHT;
                    		}
                    		else if(best_col-1>=0 && maze[best_row][best_col-1]!=NodeType.WALL){
                    			return Player.Position.LEFT;
                    		}
                    	}
                    	int temp = Math.abs(row-ghost.getCurrentRow())+Math.abs(column-ghost.getCurrentColumn());
                    	if(maze[row][column]==NodeType.DOT){
                    		dist = Math.min(dist,temp);
                    		dist = dist+1;
                    	}
                    	else{
                    		dist = Math.min(dist,temp);
                    	}
                    	if(j==-1 && pacMan.getPosition() == Player.Position.RIGHT){
                    		dist--;
                    	}
                    	else if(j==1 && pacMan.getPosition() == Player.Position.LEFT){
                    		dist--;
                    	}
                    	else if(i==-1 && pacMan.getPosition() == Player.Position.DOWN){
                    		dist--;
                    	}
                    	else if(i==1 && pacMan.getPosition() == Player.Position.UP){
                    		dist--;
                    	}
                    }
            		if(dist>mx){
            			mx = dist;
            			best_row = row;
            			best_col = column;
            		}
        		}
        	}
        }
        
        Node targetNode = new Node();
        startNode.setColumn(pacMan.getCurrentColumn());
        startNode.setRow(pacMan.getCurrentRow());
        
        targetNode.setColumn(best_col);
        targetNode.setRow(best_row);

        Player.Position position = null;
        
        if (startNode.getColumn() > targetNode.getColumn()) {
            position = Player.Position.LEFT;
        } else if (startNode.getColumn() < targetNode.getColumn()) {
            position = Player.Position.RIGHT;
        } else if (startNode.getRow() > targetNode.getRow()) {
            position = Player.Position.UP;
        } else if (startNode.getRow() < targetNode.getRow()) {
            position = Player.Position.DOWN;
        }
        else{
        	position = pacMan.getPosition();
        }
        return position;
    }

 }