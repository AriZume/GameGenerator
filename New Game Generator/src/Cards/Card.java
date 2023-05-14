package Cards;

import Game.Player;
import UserInterface.Response;

public interface Card
{
    Response getCardStatus(Player p, int maxP);
}