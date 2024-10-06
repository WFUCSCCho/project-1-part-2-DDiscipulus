# Project 1 @ CSC 201 Fall 2024: Binary Search Tree (Part 2)


## The data
I pulled my data from Kaggle, originally uploaded by Joakim Arvidsson. It contains nutritional facts 
from major fast food chains (carbs, fat, protein, etc.).

You can find the original dataset here: https://www.kaggle.com/datasets/joebeachcapital/fast-food

The reason I picked this data is that I'm really into the gym and calorie tracking. I think a big barrier
for healthy eating is that people feel they need to dramatically change their diets or throw away entire
types of "bad food". That's not really true. When you're armed with specific caloric info you'll often find
even fast food has solid options nutritionally when eaten in moderation.

### Info on Data Edits
I have edited the data slightly by removing several types of variables for various reasons explained below.

Firstly, I removed the variables tracking specific types of fat: saturated and trans fat. 
I also removed variables tracking cholesterol, sugar, and sodium. All of these variables were removed 
for the same core reason: when it comes to nutritional information, I think less is more. 
Often, what stops people from calorie tracking or looking at nutrition facts is that they feel overwhelmed 
by a deluge of numbers and data points. Most of this information is not necessary. 
For instance, while trans fat is uniquely harmful, it is also strictly regulated and rarely used nowadays. 
Keeping track of the trans fat in your food is generally a waste of time. Similarly, tracking individual 
macros like cholesterol, sodium, and sugars isn't useless, but it does provide diminishing returns.

I think focus on the big 4: Carbs, Fats, Protein, and overall Calories is more than enough for most people.

There are few more tweaks done just to make the data easier to work with.
    I removed entries that didn't use all 6 variables I am tracking, and had to remove commas
    inbetweeen words like egg, cheese bacon. The commas are how the scanner knows where to split
    the string up when reading so the extra commas were messing up the program.
## Overview
