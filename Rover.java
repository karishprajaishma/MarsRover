import java.util.ArrayList;
import java.util.List;

// Define a Command interface
interface Command {
    void execute(Rover rover);
}

// Concrete command classes
class MoveCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.move();
    }
}

class TurnLeftCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnLeft();
    }
}

class TurnRightCommand implements Command {
    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}

// Define the Rover class
class Rover {
    private int x;
    private int y;
    private char direction;

    public Rover(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move() {
        
        // (Update x and y coordinates accordingly)
    }

    public void turnLeft() {
    
    }

    public void turnRight() {
      
    }

    public void executeCommands(List<Command> commands) {
        for (Command command : commands) {
            command.execute(this);
        }
    }

    public String getStatusReport() {
        return "Rover is at (" + x + ", " + y + ") facing " + direction + ". No Obstacles detected.";
    }


	public int getX() {
		
		return 0;
	}

	public int getY() {
		
		return 0;
	}



	public String getDirection() {
		
		return null;
	}

	
}

// Define the ObstacleGrid class
class ObstacleGrid {
    private int[][] grid;
    private List<Obstacle> obstacles;

    public ObstacleGrid(int gridSize, List<Obstacle> obstacles) {
        this.grid = new int[gridSize][gridSize];
        this.obstacles = obstacles;
        // Initialize the grid with obstacles at specified positions
        for (Obstacle obstacle : obstacles) {
            grid[obstacle.getX()][obstacle.getY()] = 1;
        }
    }

    public boolean hasObstacle(int x, int y) {
        // Check if there is an obstacle at the specified coordinates
        return grid[x][y] == 1;
    }
}

// Define the Obstacle class
class Obstacle {
    private int x;
    private int y;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class MarsRover {
    public static void main(String[] args) {
        // Initialize the Rover
        Rover rover = new Rover(0, 0, 'N');

        // Define the grid and obstacles
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(2, 2));
        obstacles.add(new Obstacle(3, 5));
        ObstacleGrid grid = new ObstacleGrid(10, obstacles);

        // Define the list of commands
        List<Command> commands = new ArrayList<>();
        commands.add(new MoveCommand());
        commands.add(new MoveCommand());
        commands.add(new TurnRightCommand());
        commands.add(new MoveCommand());
        commands.add(new TurnLeftCommand());
        commands.add(new MoveCommand());

        // Execute commands and check for obstacles
        boolean hasObstacle = false;
        for (Command command : commands) {
            if (grid.hasObstacle(rover.getX(), rover.getY())) {
                hasObstacle = true;
                break;
            }
            command.execute(rover);
        }

        // Generate status report
        if (!hasObstacle) {
            System.out.println("Final Position: (" + rover.getX() + ", " + rover.getY() + ", " + rover.getDirection() + ")");
            System.out.println("Status Report: " + rover.getStatusReport());
        } else {
            System.out.println("Rover detected an obstacle and cannot move.");
        }
    }
}
