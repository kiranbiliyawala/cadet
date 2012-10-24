package org.cadet.client.model.algorithm;

import java.security.InvalidAlgorithmParameterException;

/**
 * Class to for calculating ability of testee in Adaptive test by using method of optimization
 * @author Jay Jobanputra
 */
public class Adaptive_Ability_Optimization {

    private Double ability;
    private Double difficulty;
    private Double max_difficulty;
    private Double min_difficulty;
    private Double number_of_difficulties;
    private Integer number_of_questions;
    private Double difference_between_difficulties;
    private long time_start;
    private long time_end;
    private long max_time;

    /**
     *
     * Constructor for Class Adaptive_Ability_Optimization
     *
     * @param min_difficulty minimum difficulty of test
     * @param max_difficulty maximum difficulty of test
     * @param initial_difficulty Starting difficulty of test
     * @param number_of_questions total number of questions in the test
     * @param difference_between_difficulties size of step between two
     * consecutive difficulties
     * @throws InvalidAlgorithmParameterException exception thrown when initial
     * parameters for algorithm are invalid
     */
    public Adaptive_Ability_Optimization(Double min_difficulty, Double max_difficulty, Double initial_difficulty, Integer number_of_questions, Double difference_between_difficulties) throws InvalidAlgorithmParameterException {
        if (initial_difficulty < min_difficulty) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_lower_mindifficulty);
        }
        if (initial_difficulty > max_difficulty) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_exceeds_maxdifficulty);
        }
        if (initial_difficulty % difference_between_difficulties != 0 || initial_difficulty % difference_between_difficulties != 0.0) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_not_in_stepsize);
        }
        if (difference_between_difficulties == 0 || difference_between_difficulties == 0.0) {
            throw new InvalidAlgorithmParameterException(Exceptions.zero_stepsize);
        }
        if (number_of_questions <= 0) {
            throw new InvalidAlgorithmParameterException(Exceptions.zero_questions);
        }
        if (max_difficulty <= 0) {
            throw new InvalidAlgorithmParameterException(Exceptions.zero_maxdifficulty);
        }
        if (min_difficulty < 0) {
            throw new InvalidAlgorithmParameterException(Exceptions.negative_mindifficulty);
        }
        if (min_difficulty >= max_difficulty) {
            throw new InvalidAlgorithmParameterException(Exceptions.mindifficulty_equals_maxdifficulty);
        }

        this.difficulty = initial_difficulty;
        this.ability = initial_difficulty;
        this.difference_between_difficulties = difference_between_difficulties;
        this.number_of_difficulties = (difference_between_difficulties + (max_difficulty - min_difficulty)) / difference_between_difficulties;
        this.number_of_questions = number_of_questions;
        this.min_difficulty = min_difficulty;
        this.max_difficulty = max_difficulty;

    }

    /**
     * Function to start timer for test;
     */
    public void start_test() {
        time_start = System.nanoTime();
        time_end = System.nanoTime();
        max_time = time_end - time_start;
    }

    /**
     * Function to obtain ability of testee
     *
     * @return current ability of testee
     */
    public Double getAbility() {
        return ability;
    }

    /**
     * Function to obtain ability of testee considering time
     *
     * @return current ability of testee processed with time parameter
     */
    public Double getTimedAbility() {
        return (((ability * Math.exp(difficulty)) * Math.pow(10.0, 9.0)) / (max_time + 1.0));
    }

    /**
     * Function to obtain difficulty level of test
     *
     * @return current difficulty level of test
     */
    public Double getDifficulty() {
        return difficulty;
    }

    /**
     * Increases difficulty of question by 1 step
     *
     * @return increased difficulty by 1 step
     * @throws InvalidAlgorithmParameterException if resultant difficulty is
     * higher than maximum difficulty
     */
    public Double increase_difficulty() throws InvalidAlgorithmParameterException {
        Double temp = difficulty + difference_between_difficulties;
        if (temp > max_difficulty) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_exceeds_maxdifficulty);
        }
        difficulty = temp;
        return difficulty;
    }

    /**
     * Decreases difficulty of question by 1 step
     *
     * @return decreased difficulty by 1 step
     * @throws InvalidAlgorithmParameterException if resultant difficulty is
     * lower than minimum difficulty
     */
    public Double decrease_difficulty() throws InvalidAlgorithmParameterException {
        Double temp = difficulty - difference_between_difficulties;
        if (temp < min_difficulty) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_lower_mindifficulty);
        }
        difficulty = temp;
        return difficulty;
    }

    /**
     * Sets difficulty to given difficulty
     *
     * @param difficulty difficulty level to set
     * @throws InvalidAlgorithmParameterException
     */
    public void set_difficulty(Double difficulty) throws InvalidAlgorithmParameterException {
        if (difficulty < min_difficulty) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_lower_mindifficulty);
        }
        if (difficulty > max_difficulty) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_exceeds_maxdifficulty);
        }
        if (difficulty % difference_between_difficulties != 0 || difficulty % difference_between_difficulties != 0.0) {
            throw new InvalidAlgorithmParameterException(Exceptions.difficulty_not_in_stepsize);
        }

        this.difficulty = difficulty;
    }

    /**
     *
     * Function to calculate ability,next difficulty & timed ability of testee
     * based on answer
     *
     * @param answer correctness of answer
     * @return the difficulty for next question
     */
    public Double process_answer(boolean answer) {
        time_start = time_end;
        time_end = System.nanoTime();

        if ((time_end - time_start) > max_time) {
            max_time = time_end - time_start;
        }

        int answer_int;
        Double numerator;
        Double denominator;
        if (answer) {
            answer_int = 1;
        } else {
            answer_int = 0;
        }

        numerator = Math.pow(-1.0, (1.0 - answer_int)) * (number_of_difficulties * difference_between_difficulties);

        denominator = number_of_questions * (1.0 + Math.exp(difficulty - ability));

        ability = ability + (numerator / denominator);

        difficulty = (Math.floor(ability / difference_between_difficulties)) * difference_between_difficulties;

        if (difficulty > max_difficulty) {
            difficulty = max_difficulty;
        }
        if (difficulty < min_difficulty) {

            difficulty = min_difficulty;
        }

        return difficulty;
    }

    /**
     * Function to skip a question and its processing
     *
     * @return true if question is skipped
     */
    public boolean skip_question() {
        try {
            time_start = time_end;
            time_end = System.nanoTime();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

//    public static void main(String[] args) {
//        try {
//            Integer ans;
//            Adaptive_Ability_Optimization a = new Adaptive_Ability_Optimization(1.0, 10.0, 3.0, 20, 1.0);
//            a.start_test();
//            while (true) {
//                try {
//                    ans = System.in.read();
//                    if (ans == 10) {
//                    }
//                    if (ans == 48) {
//                        System.out.println(a.process_answer(false));
//                        System.out.println(a.getAbility());
//                    }
//                    if (ans == 49) {
//                        System.out.println(a.process_answer(true));
//                        System.out.println(a.getAbility());
//                    }
//                    if (ans == 50) {
//                        System.out.println(a.getTimedAbility());
//                        System.exit(0);
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(Adaptive_Ability_Optimization.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        } catch (InvalidAlgorithmParameterException ex) {
//            Logger.getLogger(Adaptive_Ability_Optimization.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
