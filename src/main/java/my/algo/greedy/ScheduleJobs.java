package my.algo.greedy;

import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * In this programming problem and the next you'll code up the greedy algorithms
 * from lecture for minimizing the weighted sum of completion times..
 * Download the text file here.
 * This file describes a set of jobs with positive and integral weights and lengths.
 * It has the format
 * [number_of_jobs]
 * [job_1_weight] [job_1_length]
 * [job_2_weight] [job_2_length]
 * ...
 * For example, the third line of the file is "74 59", indicating that the second job
 * has weight 74 and length 59.
 * You should NOT assume that edge weights or lengths are distinct.
 * Question 1:
 * Your task in this problem is to run the greedy algorithm that schedules jobs
 * in decreasing order of the difference (weight - length).
 * Recall from lecture that this algorithm is not always optimal.
 * IMPORTANT: if two jobs have equal difference (weight - length),
 * you should schedule the job with higher weight first.
 * Beware: if you break ties in a different way, you are likely to get the wrong answer.
 * <p>
 * Question 2:
 * For this problem, use the same data set as in the previous problem.
 * Your task now is to run the greedy algorit~hm that schedules jobs (optimally)
 * in decreasing order of the ratio (weight/length).
 * In this algorithm, it does not matter how you break ties.
 */


public class ScheduleJobs {

    public enum Algo {


        RATIO_WEIGHT_LENGTH("Ratio weight/length", ratioWghtLenRankFnc()),
        DIFF_WEIGHT_LENGTH("Difference weight-length", diffWhtLenRankFnc());


        private static Function<Job, Double> ratioWghtLenRankFnc() {
            return job -> {
                final long length = job.getLength();
                return (length == 0) ? Double.POSITIVE_INFINITY :
                        job.getWeight() / (double) length;
            };
        }

        private static Function<Job, Double> diffWhtLenRankFnc() {
            return job -> (double) (job.getWeight() - job.getLength());
        }

        private static final BinaryOperator<Job> cumJobFnc =
                (job1, job2) -> new Job(
                        job1.getWeight() + (job2.getWeight() * (job1.getLength() + job2.getLength())),
                        job1.getLength() + job2.getLength());

        private static Comparator<? super Job> jobComparator(Function<Job, Double> rankJobFnc) {
            return (job1, job2) -> compareJobsRanked(job1, job2, rankJobFnc);
        }

        private static int compareJobsRanked(Job job1, Job job2, Function<Job, Double> rankFnc) {
            double job1Rank = rankFnc.apply(job1);
            double job2Rank = rankFnc.apply(job2);
            final int compareJobRanks = Double.compare(job2Rank, job1Rank);
            return compareJobRanks != 0 ? compareJobRanks :
                    Double.compare(job2.getWeight(), job1.getWeight());
        }

        private String name;
        private final Comparator<? super Job> jobComparator;

        Algo(String name, Function<Job, Double> rankJobFnc) {
            this.name = name;
            jobComparator = jobComparator(rankJobFnc);
        }

        public String getName() {
            return name;
        }

        public long compute(List<Job> jobs) {
            return jobs.stream().sorted(jobComparator).
                    reduce(new Job(0, 0), cumJobFnc).getWeight();
        }

    }
}



