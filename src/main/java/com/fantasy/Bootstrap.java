package com.fantasy;

import com.fantasy.domain.*;
import com.fantasy.enums.Position;
import com.fantasy.enums.Role;
import com.fantasy.repository.*;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationRunner {

    private final ClubRepository clubRepository;
    private final GameweekRepository gameweekRepository;
    private final GoalRepository goalRepository;
    private final MatchRepository matchRepository;
    private final PhotoRepository photoRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private static List<Club> clubs = Arrays.asList(
            new Club("Legia Warszawa"),
            new Club("Wisła Kraków"),
            new Club("Polonia Warszawa"),
            new Club("Real Madryt"),
            new Club("Manchester City"),
            new Club("FC Barcelona"),
            new Club("Real Madryt")
    );

    private static List<Pair<String, String>> playersNames = Arrays.asList(
            new Pair<>("Ederson", null),
            new Pair<>("Claudio", "Bravo"),
            new Pair<>("John", "Stones"),
            new Pair<>("Nicolás", "Otamendi"),
            new Pair<>("Vincent", "Kompany"),
            new Pair<>("Eliaquim", "Mangala"),
            new Pair<>("Benjamin", "Mendy"),
            new Pair<>("Kyle", "Walker"),
            new Pair<>("Danilo", null),
            new Pair<>("Fernandinho", null),
            new Pair<>("Ilkay", "Gündogan"),
            new Pair<>("Fabian", "Delph"),
            new Pair<>("Yaya", "Touré"),
            new Pair<>("Kevin", "De Bruyne"),
            new Pair<>("David", "Silva"),
            new Pair<>("Oleksandr", "Zinchenko"),
            new Pair<>("Leroy", "Sané"),
            new Pair<>("Raheem", "Sterling"),
            new Pair<>("Bernardo", "Silva"),
            new Pair<>("Sergio", "Agüero"),
            new Pair<>("Gabriel", "Jesus")
    );

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        generate();
    }

    private void generate() {
        User admin = new User();
        admin.setRole(Role.ADMIN);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        userRepository.save(admin);

        User user = new User();
        user.setRole(Role.USER);
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        userRepository.save(user);

        Club club = new Club();
        club.setName("Manchester City");
        club = clubRepository.save(club);

        Club club2 = new Club();
        club2.setName("Liverpool");
        club2 = clubRepository.save(club2);

        Match match = new Match();
        match.setHome(club);
        match.setVisitor(club2);

        Gameweek gameweek = new Gameweek();
        gameweek.setName("Kolejka 1");
        gameweek.setCurrent(true);
        gameweek.getMatches().add(match);
        gameweekRepository.save(gameweek);

        List<Player> players = new ArrayList<>();
        players.addAll(createPlayers());
        for (Player player : players) {
            player.setClub(club);
        }
        playerRepository.save(players);
    }

    private List<Player> createPlayers() {
        List<Player> ret = new ArrayList<>();
        playersNames.forEach(player -> {
            Player player1 = new Player();
            player1.setName(player.getKey());
            player1.setSurname(player.getValue());
            player1.setPosition(randomPosition());
            player1.setPrice(new Random().nextInt(1000000));
            player1.setShirtNumber(new Random().nextInt(99));
            ret.add(player1);
        });
        return ret;
    }

    private Position randomPosition() {
        List<Position> positions = Arrays.asList(Position.FORWARD, Position.MIDFIELDER, Position.DEFENDER, Position.GOALKEEPER);
        Collections.shuffle(positions);
        return positions.get(0);
    }
}
