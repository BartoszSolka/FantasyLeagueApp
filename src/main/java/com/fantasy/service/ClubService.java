package com.fantasy.service;

import com.fantasy.ClubDto;
import com.fantasy.domain.Club;
import com.fantasy.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public Club getClub(Long clubId) {
        return clubRepository.findOne(clubId);
    }

    public Page<Club> getClubs(Pageable pageable) {
        return clubRepository.findAll(pageable);
    }

    public Club addClub(ClubDto clubDto) {
        Club club = new Club();
        club.setName(clubDto.getName());
        club.setLogo(clubDto.getLogo());

        return clubRepository.save(club);
    }
}
