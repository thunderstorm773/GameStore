package softuni.areas.games.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import softuni.areas.games.models.view.GameDetailsView;
import softuni.areas.games.models.view.GameInfoView;
import softuni.areas.games.services.GameService;

@Controller
@RequestMapping("games")
public class GameController {
    private final GameService gameService;

    private final Gson gson;

    @Autowired
    public GameController(GameService gameService,
                          Gson gson) {
        this.gameService = gameService;
        this.gson = gson;
    }

    @GetMapping("{id}")
    public String details(Model model, @PathVariable Long id) {
        model.addAttribute("view", "games/details");

        GameDetailsView game = gameService.get(id);

        model.addAttribute("game", game);
        model.addAttribute("title", game.getTitle());

        return "base-layout";
    }

    @GetMapping("/{id}/trailer")
    @ResponseBody
    public String getTrailer(@PathVariable Long id) {
        GameInfoView gameInfoView = this.gameService.getInfoById(id);
        String gameInfoViewJson = this.gson.toJson(gameInfoView);
        return gameInfoViewJson;
    }


}
