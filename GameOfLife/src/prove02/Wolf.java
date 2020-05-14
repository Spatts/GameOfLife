package prove02;


import java.awt.*;
import java.util.Random;

public class Wolf extends Creature implements Movable, Aggressor, Aware, Spawner {

    Random _rand;
    Direction _preferredDirection;
    Boolean _canSpawn;

    public Wolf() {
        _canSpawn = false;
        _rand = new Random();
        _health = 1;
        _preferredDirection = getRandomDireciton();
    }

    public Boolean isAlive() {
        return _health > 0;
    }

    public Shape getShape() {
        return Shape.Square;
    }

    public Color getColor() {
        return new Color(75, 75, 75);
    }


    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(5);
            _canSpawn = true;

        }
    }

    private Direction getRandomDireciton() {
        switch (_rand.nextInt(4)) {
            case 0:
                return Direction.Right;
            case 1:
                return Direction.Left;
            case 2:
                return Direction.Up;
            case 3:
                return Direction.Down;
            default:
                return Direction.Up;
        }
    }

    public Creature spawnNewCreature() {
        if(!_canSpawn) {
            return null;
        }

        Wolf baby = new Wolf();

        Point newPoint = (Point)_location.clone();
            newPoint.x--;
            baby.setLocation(newPoint);
            _canSpawn = false;

        return baby;
    }


    public void senseNeighbors(Creature above, Creature below, Creature left, Creature right) {

        if(_preferredDirection == Direction.Up)
        {
            if(above instanceof Animal)
                return;
            else if(right instanceof Animal)
                _preferredDirection = Direction.Right;
            else if(below instanceof Animal)
                _preferredDirection = Direction.Down;
            else if (left instanceof Animal)
                _preferredDirection = Direction.Left;
        }
        else if(_preferredDirection == Direction.Right)
        {
            if(right instanceof Animal)
                return;
            else if(below instanceof Animal)
                _preferredDirection = Direction.Down;
            else if(left instanceof Animal)
                _preferredDirection = Direction.Left;
            else if(above instanceof Animal)
                _preferredDirection = Direction.Up;
        }
        else if(_preferredDirection == Direction.Down)
        {
            if(below instanceof Animal)
                return;
            else if(left instanceof Animal)
                _preferredDirection = Direction.Left;
            else if(above instanceof Animal)
                _preferredDirection = Direction.Up;
            else if(right instanceof Animal)
                _preferredDirection = Direction.Right;
        }
        else if(_preferredDirection == Direction.Left)
        {
            if(left instanceof Animal)
                return;
            else if(above instanceof Animal)
                _preferredDirection = Direction.Up;
            else if(right instanceof Animal)
                _preferredDirection = Direction.Right;
            else if(below instanceof Animal)
                _preferredDirection = Direction.Down;
        }
    }


    public void move() {

        // Choose a random direction each time move() is called.
        switch(_preferredDirection) {
            case Right:
                _location.x++;
                break;
            case Left:
                _location.x--;
                break;
            case Down:
                _location.y++;
                break;
            case Up:
                _location.y--;
                break;
            default:
                break;
        }
    }
}