package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm ){
        // // initialize a variable to hold the matching jobs

         ArrayList<Job> matchingJobs;

        //of search type is all and search term is an empty string then return all jobs
        if (searchType.toLowerCase().equals("all") || searchTerm.equals("")) {
            matchingJobs = JobData.findAll();
        } else {
            matchingJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        // IF search type is all and search term is an empty string then return all jobs
        //if search term has a value, use that value to find by column and value on JobData
        //add data to the model
        model.addAttribute("columns", columnChoices);
        //return model to the search view

        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        model.addAttribute("jobs", matchingJobs);

        return "search";

    }


}
