import match.MatchManager;

public class MatchApplication {

    public static void main(String[] args) {
        MatchManager match = new MatchManager("Alpha", "Beta");

        for (int i = 0; i < 6; i++) {
            System.out.println(match.getScore());
            match.scorePoint("Beta", "Alpha");
            match.scorePoint("Beta", "Alpha");
            match.scorePoint("Beta", "Alpha");
            match.scorePoint("Beta", "Alpha");

            System.out.println(match.getScore());
            match.scorePoint("Alpha", "Beta");
            match.scorePoint("Alpha", "Beta");
            match.scorePoint("Alpha", "Beta");
            match.scorePoint("Alpha", "Beta");
        }

        System.out.println(match.getScore());
        for (int i = 0; i < 10; i++) {
            match.scorePoint("Alpha", "Beta");System.out.println(match.getScore());
            match.scorePoint("Beta", "Alpha");System.out.println(match.getScore());
        }
        match.scorePoint("Beta", "Alpha");System.out.println(match.getScore());
        match.scorePoint("Beta", "Alpha");System.out.println(match.getScore());

        System.out.println("Winner: " + match.getMatchWinner("Alpha", "Beta"));
    }
}
