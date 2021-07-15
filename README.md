# GeneticAlgo
This algorithm allows us to optimize the best solution for a population by checking the data of water usage.
It checks which individual in population is using least amount of water, assigns them a fitness score, and creates a new randomized population based on the higher fitness scores. This produces more accurate results each time leading to accurate prediction of how much water usage needs to be cutoff by the lower fitness individuals.

Given the data of population's water usage→ GA code creates a genetically random population, based on input population size.
    1. each individual gets assigned random genetic code 
    2. algorithm evaluates each individual's fitness score
    3. selects fittest individuals for crossover with two parents→ creates child
        1. uses a fitness function for naturalSelection()--> euclidean distance
        2. occurs multiple times within population, for mutation
    4. mutates the crossover population randomly (to maintain diversity)
    5. returns best fitness score of population before moving on with new population for optimal solution each time
3. the best data individuals get assigned the best fitness scores
