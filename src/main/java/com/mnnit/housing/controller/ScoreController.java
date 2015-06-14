package com.mnnit.housing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mnnit.housing.places.service.ScoreService;

/**
 * @author manish
 *
 */
@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "/score")
    public Long getScore(String address) {
	return scoreService.getScore(address);
    }

}
