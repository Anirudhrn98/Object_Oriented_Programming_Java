public class MusicLP implements Comparable<MusicLP> {
    int year;
    String artist;
    String title;
    float length;
    int tracks;

    /**
     * MusicLP class constructor
     */
    public MusicLP(int year, String artist, String title, float length, int tracks) {
        this.year = year;
        this.artist = artist;
        this.title = title;
        this.length = length;
        this.tracks = tracks;
    }

    /**
     *
     * @param o                the parameter with which the comparison is to be done
     * @return 1,0, or -1      based on the result of the comparison
     */
    @Override
    public int compareTo(MusicLP o) {
        if (artist.equals(o.artist) && title.equals(o.title)
                && year == o.year && length == o.length
                && tracks == o.tracks)
            return 0;
        else if (artist.compareTo(o.artist)>0)
            return 1;
        else
            return -1;
    }

    /**
     *  Uses the toString method to give the output in a string value
     * @return the output statement of the tree
     */
    @Override
    public String toString() {
        return "MusicLP{" +
                "year=" + year +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}