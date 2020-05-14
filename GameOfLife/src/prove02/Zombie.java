package prove02;

import java.awt.*;
import java.util.Random;

public class Zombie extends Creature implements Movable, Aggressor {


    public Zombie() {
        _health = 1;
    }

    public Boolean isAlive() {
        return _health > 0;
    }

    public Shape getShape() {
        return Shape.Square;
    }

    public Color getColor() {
        return new Color(87, 132, 209);
    }

    public void attack(Creature target) {
        if (target instanceof Animal) {
            target.takeDamage(10);
            _health++;

        }
    }


    public void move() {

        _location.x++;

    }
}
