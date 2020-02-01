/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Job;
import entity.MatchSeeker;
import entity.Seeker;
import java.util.ArrayList;

/**
 *
 * @author 41649
 */
public class MatchController {

    public ArrayList<MatchSeeker> matchByJob(String jobId) {
        ArrayList<MatchSeeker> results = null;

        JobController jc = new JobController();

        Job job = jc.findJobById(jobId);

        String relKeyword = job.getRelKeywords();

        String[] relKeywords = relKeyword.split(",");

        results = assessAndRank(relKeywords);

        return results;
    }

    public ArrayList<MatchSeeker> matchByKeywords(String keywords) {
        ArrayList<MatchSeeker> results = null;

        String[] relKeywords = keywords.split(",");

        results = assessAndRank(relKeywords);

        return results;
    }

    private ArrayList<MatchSeeker> assessAndRank(String[] keywords) {
        ArrayList<MatchSeeker> results = new ArrayList<MatchSeeker>();

        SeekerController sc = new SeekerController();

        ArrayList<Seeker> seekers = sc.findAllSeeker();

        for (Seeker seeker : seekers) {
            MatchSeeker ms = new MatchSeeker();
            ms.setSeeker(seeker);
            try {
                String[] skillsets = seeker.getSkillset().split(",");
                int score = assessment(keywords, skillsets);
                ms.setScore(score);

                if (results.size() == 0) {
                    results.add(ms);
                } else {
                    int i = 0;
                    while (i < results.size()) {
                        if (ms.getScore() >= results.get(i).getScore()) {
                            results.add(i, ms);
                            break;
                        }
                        i++;
                    }
                    if (i == results.size()) {
                        results.add(ms);
                    }
                }
            } catch (Exception e) {
                continue;
            }
//            if(seeker.getSkillset().isEmpty())
//                continue;
//            if(seeker.getSkillset() == null)
//                continue;

        }

        return results;
    }

    private int assessment(String[] keywords, String[] skillsets) {
        int satisfyItems = 0;
        int requirements = keywords.length;

        for (String keyword : keywords) {
            for (String skill : skillsets) {
                if (keyword.equalsIgnoreCase(skill)) {
                    satisfyItems++;
                    break;
                }
            }
        }

        int score = ((satisfyItems * 100) / requirements);
        return score;
    }
}
