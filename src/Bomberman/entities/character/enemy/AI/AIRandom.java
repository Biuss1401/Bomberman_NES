package Bomberman.entities.character.enemy.AI;

import Bomberman.entities.character.Bomber;
import Bomberman.entities.character.enemy.Enemy;
import Bomberman.entities.character.enemy.Minvo;

public class AIRandom extends AI
{
    @Override
    public int calculateDirection()
    {
        return random.nextInt(4);
    }

}