import java.util.ArrayList;
import java.util.List;


public class GhostAI {
    private GhostAI() {
        // Prevent instantiation.
    }

    public static Player.Position getGhostNextPosition(Player pacMan, Player ghost,
        NodeType[][] maze) {
        Node startNode = new Node();
        Node targetNode = new Node();
        startNode.setColumn(ghost.getCurrentColumn());
        startNode.setRow(ghost.getCurrentRow());

        targetNode.setColumn(pacMan.getCurrentColumn());
        targetNode.setRow(pacMan.getCurrentRow());

        Player.Position position = calculate(startNode, targetNode, maze);
        return position;
    }

    private static Player.Position calculate(Node startNode, Node targetNode,
        NodeType[][] maze) {
        List<Node> openNodeList = new ArrayList<Node>();
        List<Node> closeNodeList = new ArrayList<Node>();
        openNodeList.add(startNode);
        while (openNodeList.size() > 0 && !closeNodeList.contains(targetNode)) {
            Node currentNode = getLowestDistanceNode(openNodeList);
            closeNodeList.add(currentNode);
            openNodeList.remove(currentNode);

            List<Node> adjacentNodes = getAdjacentNodes(currentNode.getColumn(),
                currentNode.getRow(), maze);
            for (Node adjacentNode : adjacentNodes) {
                if (!closeNodeList.contains(adjacentNode)) {
                    adjacentNode.setParentColumn(currentNode.getColumn());
                    adjacentNode.setParentRow(currentNode.getRow());
                    if (!openNodeList.contains(adjacentNode)) // Open list
                    {
                        adjacentNode.setDistance(calculateDistance(
                            adjacentNode.getColumn(), adjacentNode.getRow(),
                            targetNode.getColumn(), targetNode.getRow()));
                        openNodeList.add(adjacentNode);
                    }
                }
            }
        }
        return getNextPosition(startNode,
            getNextMove(startNode, targetNode, closeNodeList));
    }

    private static Player.Position getNextPosition(Node startNode, Node nextNode) {
        Player.Position position = null;
        if (nextNode != null) {
            if (startNode.getColumn() > nextNode.getColumn()) {
                position = Player.Position.LEFT;
            } else if (startNode.getColumn() < nextNode.getColumn()) {
                position = Player.Position.RIGHT;
            } else if (startNode.getRow() > nextNode.getRow()) {
                position = Player.Position.UP;
            } else if (startNode.getRow() < nextNode.getRow()) {
                position = Player.Position.DOWN;
            }
        }
        return position;
    }

    private static Node getNextMove(Node startNode, Node targetNode,
        List<Node> closeNodeList) {
        int column = targetNode.getColumn();
        int row = targetNode.getRow();
        Node node = null;
        while ((node = findNode(column, row, closeNodeList)) != null) {
            if (node.getParentColumn() == startNode.getColumn()
                && node.getParentRow() == startNode.getRow()) {
                break;
            } else {
                column = node.getParentColumn();
                row = node.getParentRow();
            }
        }
        return node;
    }

    private static Node findNode(int column, int row, List<Node> closeNodeList) {
        for (Node node : closeNodeList) {
            if (node.getColumn() == column && node.getRow() == row) {
                return node;
            }
        }
        return null;
    }

    private static List<Node> getAdjacentNodes(int column, int row, NodeType[][] maze) {
        List<Node> nodeList = new ArrayList<Node>();
        if (!isBlockedTerrain(column + 1, row, maze)) {
            Node node = new Node();
            node.setColumn(column + 1);
            node.setRow(row);
            nodeList.add(node);
        }

        if (!isBlockedTerrain(column - 1, row, maze)) {
            Node node = new Node();
            node.setColumn(column - 1);
            node.setRow(row);
            nodeList.add(node);
        }

        if (!isBlockedTerrain(column, row + 1, maze)) {
            Node node = new Node();
            node.setColumn(column);
            node.setRow(row + 1);
            nodeList.add(node);
        }

        if (!isBlockedTerrain(column, row - 1, maze)) {
            Node node = new Node();
            node.setColumn(column);
            node.setRow(row - 1);
            nodeList.add(node);
        }
        return nodeList;
    }

    private static boolean isBlockedTerrain(int column, int row, NodeType[][] maze) {
        boolean blocked = false;
        try {
            if (maze[row][column] == NodeType.WALL) {
                blocked = true;
            }
        } catch (Exception ex) {
            // Do nothing.
        }
        return blocked;
    }

    private static int calculateDistance(int srcColumn, int srcRow, int destColumn,
        int destRow) {
        return (Math.abs(srcColumn - destColumn) * 10)
            + (Math.abs(srcRow - destRow) * 10);
    }

    private static Node getLowestDistanceNode(List<Node> openNodeList) {
        Node lowestDistanceNode = null;
        if (openNodeList.size() > 0) {
            lowestDistanceNode = openNodeList.get(0);
            for (int i = 1; i < openNodeList.size(); i++) {
                Node n = openNodeList.get(i);
                if (n.getDistance() < lowestDistanceNode.getDistance()) {
                    lowestDistanceNode = n;
                }
            }
        }
        return lowestDistanceNode;
    }
}
