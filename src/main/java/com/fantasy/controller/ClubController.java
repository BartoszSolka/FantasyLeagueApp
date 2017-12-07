package com.fantasy.controller;

import com.fantasy.domain.Player;
import com.fantasy.dto.ClubDto;
import com.fantasy.domain.Club;
import com.fantasy.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public Page<Club> getClubs(@PageableDefault Pageable pageable) {
        return clubService.getClubs(pageable);
    }

    @GetMapping("/{id}")
    public Club getClub(@PathVariable("id") Long clubId) {
        return clubService.getClub(clubId);
    }

    @PostMapping
    public Club addClub(@RequestBody ClubDto clubDto) {
        return clubService.addClub(clubDto);
    }

    @PutMapping("/{id}")
    public Club editClub(@PathVariable("id")Club club, @RequestBody ClubDto clubDto) {
        return clubService.editClub(club, clubDto);
    }
}
