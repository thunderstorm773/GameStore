package softuni.areas.games.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import softuni.areas.games.entities.Game;
import softuni.areas.games.services.GameService;
import softuni.areas.users.entities.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CartController {

    private final GameService gameService;

    private final Gson gson;

    @Autowired
    public CartController(GameService gameService,
                          Gson gson) {
        this.gameService = gameService;
        this.gson = gson;
    }

    @PostMapping("/games/{gameId}/buy")
    public void buyGame(@PathVariable Long gameId,
                        @AuthenticationPrincipal User user) {
        this.gameService.buy(gameId, user);
    }

    @GetMapping("/cart/all")
    public String getAllGamesFromCart(@AuthenticationPrincipal User user) {
        Set<Game> games = user.getGames();
        Set<Long> gamesId = games.stream()
                .map(Game::getId).collect(Collectors.toSet());
        List<String> gamesTitle = this.gameService.getTitlesById(gamesId);
        String gamesTitleJson = this.gson.toJson(gamesTitle);
        return gamesTitleJson;
    }

    @GetMapping("/cart/clear")
    public void clearCart(@AuthenticationPrincipal User user) {
        user.getGames().clear();
    }
}
