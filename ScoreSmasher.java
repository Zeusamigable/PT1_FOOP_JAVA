import java.util.Scanner;
public class ScoreSmasher{

    public static void getMenu(){
        //Prints Menu
        System.out.println("Welcome to ScoreSmasher: Record Your Game");
        System.out.println("==========================================");
        System.out.println("New Round = 1");
        System.out.println("View Scores = 2");
        System.out.println("Record/Edit Scores = 3");
        System.out.println("Score Summary = 4");
        System.out.println("Reset ScoreBoard = 5");
        System.out.println("Exit Withdraw = 6");
        System.out.println("--------------------");
        System.out.print("Whats your pick: ");
    }


    public static void getWhoScores(int teamchoice, String team1, String team2){
        //Prints the Team Name of Who Scored
        if (teamchoice == 1){
            System.out.printf("Team %s scored!%n", team1);
        } else if (teamchoice == 2){
            System.out.printf("Team %s scored!%n", team2);
        } else {
            System.out.println("Invalid Team Choice");
        }
    }

    public static void getScoreSummary(int Team_1_score, int Team_2_score, String team1, String team2){
        //The summary of the Game
        int behind = 0;
        if (Team_1_score > Team_2_score){
            //Team 1 Score is Greater
            behind = Team_1_score - Team_2_score;
        } else if (Team_2_score > Team_1_score){
            //Team 2 Score is Greater
            behind = Team_2_score - Team_1_score;
        }
        System.out.println("");
        System.out.println("Score Summary: ");
        System.out.println("======================");
        System.out.printf("Team %s: %d%n", team1, Team_1_score);
        System.out.printf("Team %s: %d%n", team2, Team_2_score);
        System.out.println("======================");


        if (Team_1_score > Team_2_score){
            System.out.printf("Team %s is leading with %d points%n", team1, Team_1_score);
            System.out.printf("Team %s is behind with %d points%n", team2, behind);
        
        } else if (Team_2_score > Team_1_score){
            System.out.printf("Team %s is leading with %d points%n", team2, Team_2_score);
            System.out.printf("Team %s is behind with %d points%n", team1, behind);
        
        } else {
            System.out.printf("Both teams are tied with %d points !%n", Team_1_score);
        }
        System.out.println("");
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Team name
        String team1 = "";
        String team2 = "";
        int choice = 0;
        int teamchoice;
        boolean proceed = false;
        boolean scoreActivated = false;
        // Constants & Running Totals
        int Team_1_score = 0;
        int Team_2_score = 0;
        int Leading_Team = 0; // Can be used to determine which team is leading and print the team name; in an if else statement, it copies the highest score then compare whos score is equal to the leading team score and prints its team name and score


        getMenu();
        choice = sc.nextInt();
        while (choice <= 0|| choice > 6){
            System.out.println("");
            System.out.println("Invalid Choice, Try Again");
            System.out.println("");
            getMenu();
            choice = sc.nextInt();
        }
        sc.nextLine();

        while (choice != 6){
            switch (choice){
                case 1://New Round
                //Checks whether the scoreboard has been resetted;
                if (scoreActivated){
                    System.out.println("Reset The ScoreBoard First!");
                    break;
                }
                proceed = false;

                //Input TEAM name
                do{
                    System.out.print("Enter Team 1 name: ");
                    team1 = sc.nextLine();
                    System.out.print("Enter Team 2 name: ");
                    team2 = sc.nextLine();
                    if (team1.isEmpty() || team2.isEmpty()){
                        //Teams must ne non-empty
                    System.out.println("Teams must be non-empty");
                    } else if (team1.equals(team2)){
                        //If Both Teams Have The Same Name
                        System.out.println("Teams must not have the same name");
                    }
                } while (team1.isEmpty() || team2.isEmpty() || (team1.equals(team2)));
                
                scoreActivated = true;

                //Instructions For Scoring
                System.out.println("Enter the team's number, then their points.");
                System.out.printf("Team %s: Enter 1      Team %s: Enter 2%n", team1, team2);
                System.out.printf("Which Team won (0 to Stop): ");
                teamchoice = sc.nextInt();
                while (!proceed){
                    if (teamchoice == 1 || teamchoice == 2){
                        //Score Point
                        System.out.print("How many points did they score: ");
                        int points = sc.nextInt();
                        while (points <= 0){
                            System.out.println("Only Positive Number, Try Again");
                            System.out.print("How many points did they score: ");
                            points = sc.nextInt();
                        }
                        getWhoScores(teamchoice, team1, team2);
                        
                        if (teamchoice == 1){
                            Team_1_score += points;
                        } else if (teamchoice == 2){
                            Team_2_score += points;
                        }

                    } else if (teamchoice == 0){
                        //Prints the Summary Of The Game
                        getScoreSummary(Team_1_score, Team_2_score, team1, team2);
                        proceed = true;
                    } else {
                        //Invalid Input
                        System.out.println("Invalid Team Choice, Try Again");
                    }

                    if (!proceed){  
                        //Which Team won the round
                        System.out.printf("Which Team won (0 to Stop): ");
                        teamchoice = sc.nextInt();
                    }
                }
                    break;  

                //Case 2 & Case 3 wala pa

                case 4:
                    //Score Summary
                    if (!scoreActivated){
                        System.out.println("");
                        System.out.println("No teams have been created yet, please create a new round first");
                        System.out.println("");
                        break;
                    }
                    getScoreSummary(Team_1_score, Team_2_score, team1, team2);
                    break;
                

                case 5:
                    //Reset ScoreBoard

                    System.out.print("Do you wish to reset the ScoreBoard (y/n): ");
                    char resetScoreBoard = sc.next().charAt(0);

                    if (resetScoreBoard == 'y'){
                    team1 = "";
                    team2 = "";
                    Team_1_score = 0;
                    Team_2_score = 0;
                    System.out.println("ScoreBoard Reset!");
                    System.out.println("");
                    scoreActivated = false;
                    } else {
                        System.out.println("ScoreBoard Unchanged!");
                        System.out.println("");
                    }
                    break;
                
                default:
                    System.out.println("Invalid Choice, Try Again");
                    break;
                }

                System.out.print("Return to menu (y/n): ");
                char returnMenu = sc.next().charAt(0);
                System.out.println("");
                if (returnMenu == 'n'){
                    break;
                }
                getMenu();
                choice = sc.nextInt();
                sc.nextLine();
            }
        System.out.println("Thank You For Using ScoreSmasher");
        sc.close();
    }
}
