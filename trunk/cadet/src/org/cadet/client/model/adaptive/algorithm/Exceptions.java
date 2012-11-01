package org.cadet.client.model.adaptive.algorithm;

/**
 *
 * @author Jay Jobanputra
 */
public class Exceptions {
    final static String zero_stepsize = "difference between difficulties is 0";
    final static String zero_questions = "Number of questions in test cannot be 0 or less than 0";
    final static String zero_maxdifficulty = "Maximum difficulty in test cannot be 0 or less than 0";
    final static String negative_mindifficulty = "Minimum difficulty in test cannot be less than 0";
    final static String mindifficulty_equals_maxdifficulty = "Minimum difficulty in test cannot be greater or equal to maximum difficulty";
    final static String difficulty_exceeds_maxdifficulty = "Difficulty exceeds max_difficulty";
    final static String difficulty_lower_mindifficulty = "Difficulty is lower than min_difficulty";
    final static String difficulty_not_in_stepsize = "Difficulty can only be multiple of step size";
}
