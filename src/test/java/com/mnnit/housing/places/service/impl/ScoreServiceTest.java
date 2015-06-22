package com.mnnit.housing.places.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mnnit.housing.common.BaseTest;
import com.mnnit.housing.places.service.ScoreService;

/**
 * @author manish
 *
 */
public class ScoreServiceTest extends BaseTest {

    @Autowired
    private ScoreService scoreService;

    @Test
    public void testGetScore() {
	System.out.println(scoreService
		.getScore("695 Tasman drive, sunnyvale CA-94089"));
    }

}
