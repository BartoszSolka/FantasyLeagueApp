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
import org.springframework.transaction.annotation.Transactional;

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

    private static List<Pair<String, String>> manchesterCityPlayers = Arrays.asList(
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

    private static List<Pair<String, String>> liverpoolPlayers = Arrays.asList(
            new Pair<>("Mohamed", "Salah"),
            new Pair<>("Sadio", "Mane"),
            new Pair<>("Virgil", "van Djik"),
            new Pair<>("Alex", "Oxlade-Chamberlain"),
            new Pair<>("Roberto", "Firmino"),
            new Pair<>("Andrew", "Robertson"),
            new Pair<>("Adam", "Lallana"),
            new Pair<>("Daniel", "Sturridge"),
            new Pair<>("Emre", "Can"),
            new Pair<>("Jordan", "Henderson"),
            new Pair<>("Alberto Moreno", "Perez"),
            new Pair<>("Dominic", "Solanke"),
            new Pair<>("Dejan", "Lovren"),
            new Pair<>("Joe", "Gomez"),
            new Pair<>("Simon", "Mignolet"),
            new Pair<>("Joel", "Matip"),
            new Pair<>("James", "Milner"),
            new Pair<>("Georginio", "Wijnaldumm"),
            new Pair<>("Loris", "Karius"),
            new Pair<>("Nathaniel", "Clyne"),
            new Pair<>("Danny", "Ings")
    );

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        generate();
    }

    @Transactional
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
        players.addAll(createPlayers(manchesterCityPlayers));
        for (Player player : players) {
            player.setClub(club);
        }
        players = playerRepository.save(players);

        players.clear();
        players.addAll(createPlayers(liverpoolPlayers));
        for (Player player : players) {
            player.setClub(club2);
        }
        players = playerRepository.save(players);

        Goal goal = new Goal();
        goal.setMatch(match);
        goal.setMinuteOfGame(30);
        goal.setScoredBy(players.get(0));
        goal.setAssistedBy(players.get(1));
        goal = goalRepository.save(goal);
        match.getHomeGoals().add(goal);

        Goal goal2 = new Goal();
        goal2.setMatch(match);
        goal2.setMinuteOfGame(89);
        goal2.setScoredBy(players.get(3));
        goal2 = goalRepository.save(goal2);
        match.getVisitorGoals().add(goal2);
        matchRepository.save(match);
    }

    private List<Player> createPlayers(List<Pair<String, String>> names) {
        List<Player> ret = new ArrayList<>();
        names.forEach(name -> {
            Player player1 = new Player();
            player1.setName(name.getKey());
            player1.setSurname(name.getValue());
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
