package com.smhrd.soolsool.controller;

import com.smhrd.soolsool.service.ReviewService;
import com.smhrd.soolsool.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/Info/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{liq_idx}")
    public ResponseEntity<List<Review>> getReviewsByLiqIdx(@PathVariable("liq_idx") String liq_idx) {
        List<Review> reviews = reviewService.getReviewsByLiqIdx(liq_idx);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/{liq_idx}/write")
    public ResponseEntity<Integer> createReview(@RequestBody Review review) {
    	System.out.println(review.getLiq_idx());
    	System.out.println(review.getMb_id());
    	System.out.println(review.getRv_content());
        int createdReviewId = reviewService.createReview(review);
        return ResponseEntity.ok(createdReviewId);
    }
}
