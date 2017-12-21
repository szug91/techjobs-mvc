package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    // Displays the page with search param
    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
    // Handler to get the search term and search type from the forms
    @RequestMapping(value = "results")
    public String results (Model model, @RequestParam String searchTerm, @RequestParam String searchType){
        // Holds the search values
        ArrayList<HashMap<String,String>> jobs;

        // If the term is all then search for everything
        if (searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);
        }// return the values with the search term
        else{
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
        }

        // return the radio buttons
        model.addAttribute("columns",ListController.columnChoices);
        //pass the jobs from the search
        model.addAttribute("jobs",jobs);

        return "search"; // returns the results page
    }


}