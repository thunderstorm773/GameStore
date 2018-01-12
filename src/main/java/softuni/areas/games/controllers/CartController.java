package softuni.areas.games.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import softuni.areas.games.services.GameService;
import softuni.areas.users.entities.User;

@RestController
@RequestMapping("/games")
public class CartController {

    private final GameService gameService;

    @Autowired
    public CartController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/{gameId}/buy")
    public void buyGame(@PathVariable Long gameId,
                        @AuthenticationPrincipal User user) {
        this.gameService.buy(gameId, user);
    }


}
