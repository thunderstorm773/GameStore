package softuni.areas.games.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.areas.games.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
